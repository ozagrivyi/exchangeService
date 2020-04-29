package co.ozdev.persistance.repositories;

import co.ozdev.persistance.entities.CommissionEntity;
import co.ozdev.persistance.entities.ExchangeRateEntity;

import javax.persistence.Table;

@Table(name = "Commission")
public interface CommissionRepository  extends BaseRepository<CommissionEntity, Long> {
    CommissionEntity findOneByExchangeRate(ExchangeRateEntity exchangeRateEntity);
}
