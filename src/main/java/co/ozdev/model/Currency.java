package co.ozdev.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Currency {
    EUR("EUR"),
    USD("USD"),
    UAH("UAH"),
    RUB("RUB");

    private String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    @JsonValue
    public String getCurrency() {
        return currency;
    }
}
