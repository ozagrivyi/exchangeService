package co.ozdev.dto;

import co.ozdev.model.Currency;

import java.math.BigDecimal;

public class CommissionDto {
    private Currency from;
    private Currency to;
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
