package co.ozdev.dto;

import co.ozdev.model.Currency;
import co.ozdev.validation.CurrencyConstraint;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ExchangeRateDto {
    @NotNull(message = "'from' can not be empty")
    private Currency from;

    @NotNull(message = "'to' can not be empty")
    private Currency to;

    @NotNull(message = "'Rate' can not be empty")
    @DecimalMin(value = "0", message = "'Rate' should be greater than 0'" )
    private BigDecimal rate;

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
