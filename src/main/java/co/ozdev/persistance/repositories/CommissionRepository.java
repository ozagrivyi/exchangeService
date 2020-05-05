package co.ozdev.persistance.repositories;

import co.ozdev.persistance.entities.CommissionEntity;
import co.ozdev.persistance.entities.ExchangePairEntity;
import co.ozdev.persistance.entities.ExchangeRateEntity;

import javax.persistence.Table;

@Table(name = "COMMISSION")
public interface CommissionRepository  extends BaseRepository<CommissionEntity, Long> {
}
