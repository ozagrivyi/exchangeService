package co.ozdev.persistance.repositories;

import co.ozdev.persistance.entities.ExchangeRateEntity;

import javax.persistence.Table;

@Table(name = "EXCHANGE_RATE")
public interface ExchangeRateRepository extends BaseRepository<ExchangeRateEntity, Long> {
}
