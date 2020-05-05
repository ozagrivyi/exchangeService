package co.ozdev.services;

import co.ozdev.dto.ExchangeDto;
import co.ozdev.dto.ExchangeRateDto;
import co.ozdev.dto.mappers.ExchangeRateMapper;
import co.ozdev.exception.ExchangePairNotFoundException;
import co.ozdev.model.Currency;
import co.ozdev.model.OperationType;
import co.ozdev.persistance.entities.CommissionEntity;
import co.ozdev.persistance.entities.ExchangePairEntity;
import co.ozdev.persistance.entities.ExchangeRateEntity;
import co.ozdev.persistance.repositories.CommissionRepository;
import co.ozdev.persistance.repositories.ExchangePairRepository;
import co.ozdev.persistance.repositories.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExchangeService {

    @Autowired
    private ExchangePairRepository exchangePairRepository;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @Autowired
    private CommissionRepository commissionRepository;

    public void setExchangeRate(ExchangeRateDto exchangeRateDto) {
        setExchangeRate(exchangeRateDto, true);
    }

    public void setExchangeRate(ExchangeRateDto exchangeRateDto, boolean addReversed) {
        ExchangePairEntity exchangePairEntity =
                exchangePairRepository.findOneByCurrencyFromAndCurrencyTo(exchangeRateDto.getFrom(), exchangeRateDto.getTo());
        if (exchangePairEntity == null) {
            exchangePairEntity = new ExchangePairEntity();
            exchangePairEntity.setCurrencyFrom(exchangeRateDto.getFrom());
            exchangePairEntity.setCurrencyTo(exchangeRateDto.getTo());
        }
        ExchangeRateEntity exchangeRateEntity = exchangePairEntity.getExchangeRate();
        if (exchangeRateEntity == null) {
            exchangeRateEntity = new ExchangeRateEntity();
        }
        exchangeRateEntity.setExchangePair(exchangePairEntity);
        exchangeRateEntity.setRate(exchangeRateDto.getRate());
        exchangePairEntity.setExchangeRate(exchangeRateEntity);
        exchangeRateRepository.save(exchangeRateEntity);
        exchangePairRepository.save(exchangePairEntity);
        if (addReversed) {
            ExchangeRateDto reversedExchangeRateDto = new ExchangeRateDto();
            reversedExchangeRateDto.setFrom(exchangeRateDto.getTo());
            reversedExchangeRateDto.setTo(exchangeRateDto.getFrom());
            reversedExchangeRateDto.setRate(BigDecimal.ONE.divide(exchangeRateDto.getRate(), MathContext.DECIMAL32));
            setExchangeRate(reversedExchangeRateDto, false);
        }
    }

    public List<ExchangeRateDto> getExchangeRates() {
        List<ExchangePairEntity> exchangePairEntities = exchangePairRepository.findAll();
        List<ExchangeRateDto> result = exchangePairEntities.stream().filter(exchangePair ->
                exchangePair.getExchangeRate() != null).map(entity -> exchangeRateMapper.convertToDto(entity))
                .collect(Collectors.toList());
        return result;
    }

    public ExchangeDto exchange(ExchangeDto exchangeDto) {
        OperationType operationType = exchangeDto.getOperationType();
        ExchangePairEntity exchangePairEntity = exchangePairRepository.findOneByCurrencyFromAndCurrencyTo(exchangeDto.getFrom(), exchangeDto.getTo());
        if (exchangePairEntity == null) {
            throw new ExchangePairNotFoundException(exchangeDto.getFrom(), exchangeDto.getTo());
        }

        ExchangeRateEntity exchangeRate = exchangePairEntity.getExchangeRate();
        BigDecimal rate = exchangeRate == null ? BigDecimal.ONE : exchangeRate.getRate();
        CommissionEntity commission = exchangePairEntity.getCommission();
        BigDecimal commissionPt = commission == null ? BigDecimal.ZERO : commission.getCommissionPt();
        BigDecimal amountFrom, amountTo;
        switch (operationType) {
            case GIVE:
                amountFrom = exchangeDto.getAmountFrom();
                amountFrom = amountFrom.subtract(amountFrom.multiply(commissionPt).divide(BigDecimal.valueOf(100)));
                amountTo = amountFrom.multiply(rate, MathContext.DECIMAL32);
                exchangeDto.setAmountTo(amountTo);
                break;
            case GET:
                amountTo = exchangeDto.getAmountTo();
                amountFrom = amountTo.multiply(BigDecimal.valueOf(100), MathContext.DECIMAL32)
                                .divide(rate.multiply(BigDecimal.valueOf(100)
                                .subtract(commissionPt), MathContext.DECIMAL32), MathContext.DECIMAL32);
                exchangeDto.setAmountFrom(amountFrom);
                break;
        }
        return exchangeDto;
    }

    private BigDecimal includeCommission(BigDecimal amount, BigDecimal commissionPt) {
        return commissionPt != null ?
                amount.subtract((amount.multiply(commissionPt)
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))) : amount;
    }
}
