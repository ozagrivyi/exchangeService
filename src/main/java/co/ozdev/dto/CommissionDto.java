package co.ozdev.dto;

import co.ozdev.model.Currency;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CommissionDto {
    private Currency from;
    private Currency to;

    @NotNull(message = "'commissionPt' can not be empty")
    @DecimalMin(value = "0", message = "'commissionPt' should be greater than 0'" )
    @DecimalMax(value = "100.0", message = "'commissionPt' should be less than 100'")
    private BigDecimal commissionPt;

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

    public BigDecimal getCommissionPt() {
        return commissionPt;
    }

    public void setCommissionPt(BigDecimal commissionPt) {
        this.commissionPt = commissionPt;
    }
}
