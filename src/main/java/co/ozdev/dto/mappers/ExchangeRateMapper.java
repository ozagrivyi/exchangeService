package co.ozdev.dto.mappers;

import co.ozdev.dto.ExchangeRateDto;
import co.ozdev.persistance.entities.ExchangePairEntity;
import co.ozdev.persistance.entities.ExchangeRateEntity;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateMapper {
    public ExchangeRateDto convertToDto(ExchangePairEntity entity) {
        ExchangeRateDto result = new ExchangeRateDto();
        result.setFrom(entity.getCurrencyFrom());
        result.setTo(entity.getCurrencyTo());
        result.setRate(entity.getExchangeRate().getRate());
        return result;
    }

    public ExchangeRateEntity convertToEntity(ExchangeRateDto exchangeRateDto) {
        ExchangeRateEntity result = new ExchangeRateEntity();
//        result.setCurrencyFrom(exchangeRateDto.getFrom());
//        result.setCurrencyTo(exchangeRateDto.getTo());
//        result.setRate(exchangeRateDto.getRate());
        return result;
    }
}
