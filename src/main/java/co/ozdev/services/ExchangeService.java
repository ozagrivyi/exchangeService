package co.ozdev.services;

import co.ozdev.dto.ExchangeDto;
import co.ozdev.dto.ExchangeRateDto;
import co.ozdev.dto.mappers.ExchangeRateMapper;
import co.ozdev.persistance.entities.CommissionEntity;
import co.ozdev.persistance.entities.ExchangeRateEntity;
import co.ozdev.persistance.repositories.ExchangeRateRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    public ExchangeRateDto setExchangeRate(ExchangeRateDto exchangeRateDto) {
        ExchangeRateEntity exchangeRateEntity = exchangeRateRepository.findOneByCurrencyFromAndCurrencyTo(exchangeRateDto.getFrom(), exchangeRateDto.getTo());
        if (exchangeRateEntity != null) {
            exchangeRateEntity.setRate(exchangeRateDto.getRate());
        } else {
            exchangeRateEntity = exchangeRateMapper.convertToEntity(exchangeRateDto);
        }
        exchangeRateRepository.save(exchangeRateEntity);
        return exchangeRateDto;
    }

    public List<ExchangeRateDto> getExchangeRates() {
        List<ExchangeRateEntity> exchangeRateEntities = exchangeRateRepository.findAll();
        List<ExchangeRateDto> result = exchangeRateEntities.stream().map(entity -> exchangeRateMapper.convertToDto(entity)).collect(Collectors.toList());
        return result;
    }

    public ExchangeDto exchange(ExchangeDto exchangeDto) {
        ExchangeRateEntity exchangeRate = exchangeRateRepository.findOneByCurrencyFromAndCurrencyTo(exchangeDto.getFrom(), exchangeDto.getTo());
        Hibernate.initialize(exchangeRate);
        BigDecimal rate = exchangeRate.getRate();
        CommissionEntity commission = exchangeRate.getCommission();
        BigDecimal commissionPt = commission.getCommissionPt();
        BigDecimal amountFrom, amountTo;
        switch (exchangeDto.getOperationType()) {
            default:
            case GET:
                amountFrom = exchangeDto.getAmountFrom();
                amountTo = includeCommission(amountFrom.divide(rate, 2, RoundingMode.HALF_UP), commissionPt);
                exchangeDto.setAmountTo(amountTo);
                break;
            case GIVE:
                amountTo = exchangeDto.getAmountTo();
                amountFrom = includeCommission(amountTo.divide(rate, 2, RoundingMode.HALF_UP), commissionPt);
                exchangeDto.setAmountFrom(amountFrom);
                break;
        }
        return exchangeDto;
    }

    private BigDecimal includeCommission(BigDecimal amount, BigDecimal commissionPt) {
        return commissionPt != null ? amount.subtract((amount.multiply(commissionPt).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))) : amount;
    }
}
