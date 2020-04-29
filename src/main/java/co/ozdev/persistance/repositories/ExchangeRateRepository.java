package co.ozdev.persistance.repositories;

import co.ozdev.model.Currency;
import co.ozdev.persistance.entities.ExchangeRateEntity;

import javax.persistence.Table;

@Table(name = "EXCHANGE_RATE")
public interface ExchangeRateRepository extends BaseRepository<ExchangeRateEntity, Long> {
    ExchangeRateEntity findOneByCurrencyFromAndCurrencyTo(Currency from, Currency to);
}
