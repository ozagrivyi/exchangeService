package co.ozdev.dto.mappers;

import co.ozdev.dto.CommissionDto;
import co.ozdev.persistance.entities.CommissionEntity;
import co.ozdev.persistance.entities.ExchangePairEntity;
import co.ozdev.persistance.entities.ExchangeRateEntity;
import co.ozdev.persistance.repositories.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommissionMapper {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public CommissionEntity convertToEntity(CommissionDto commissionDto, ExchangePairEntity exchangeRateEntity) {
        CommissionEntity result = new CommissionEntity();
        result.setCommissionPt(commissionDto.getCommissionPt());
        result.setExchangePair(exchangeRateEntity);
        return result;
    }

    public CommissionDto convertToDto(CommissionEntity entity) {
        CommissionDto result = new CommissionDto();
        result.setCommissionPt(entity.getCommissionPt());
        result.setFrom(entity.getExchangePair().getCurrencyFrom());
        result.setTo(entity.getExchangePair().getCurrencyTo());
        return result;
    }

}
