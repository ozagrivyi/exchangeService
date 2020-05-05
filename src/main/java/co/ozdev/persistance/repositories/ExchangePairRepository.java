package co.ozdev.persistance.repositories;

import co.ozdev.model.Currency;
import co.ozdev.persistance.entities.ExchangePairEntity;

import javax.persistence.Table;

@Table(name = "EXCHANGE_PAIR")
public interface ExchangePairRepository extends BaseRepository<ExchangePairEntity, Long> {
    ExchangePairEntity findOneByCurrencyFromAndCurrencyTo(Currency currencyFrom, Currency currencyTo);
}
