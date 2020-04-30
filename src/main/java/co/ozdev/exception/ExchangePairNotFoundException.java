package co.ozdev.exception;

import co.ozdev.model.Currency;

public class ExchangePairNotFoundException extends RuntimeException {
    public ExchangePairNotFoundException(Currency from, Currency to) {
        super("Exchange pair (" + from.name() + ":" + to.name() +") can not be found");
    }
}
