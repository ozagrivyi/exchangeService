package co.ozdev.dto;

import co.ozdev.model.Currency;
import co.ozdev.validation.CurrencyConstraint;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ExchangeRateDto {
    @CurrencyConstraint
    private Currency from;

    private Currency to;

    @NotNull(message = "'Rate' field can not be null")
    @DecimalMin(value = "0.05", message = "'Rate should be greater than or equal to 1.0'" )
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
