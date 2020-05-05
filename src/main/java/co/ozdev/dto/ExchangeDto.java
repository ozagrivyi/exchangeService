package co.ozdev.dto;

import co.ozdev.model.Currency;
import co.ozdev.model.OperationType;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ExchangeDto {

    @NotNull(message = "'from' can not be empty")
    private Currency from;

    @NotNull(message = "'to' can not be empty")
    private Currency to;

    @NotNull(message = "'amountForm' can not be empty")
    @DecimalMin(value = "0", message = "'amountFrom' should be greater than 0")
    private BigDecimal amountFrom;

    @NotNull(message = "'amountTo' can not be empty")
    @DecimalMin(value = "0", message = "'amountTo' should be greater than 0")
    private BigDecimal amountTo;

    @NotNull(message = "'operationType' can not be empty")
    private OperationType operationType;

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

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(BigDecimal amountFrom) {
        this.amountFrom = amountFrom;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
