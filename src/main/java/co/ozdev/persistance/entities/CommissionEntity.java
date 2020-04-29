package co.ozdev.persistance.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CommissionEntity {
    private Long id;
    private ExchangeRateEntity exchangeRate;
    private BigDecimal commissionPt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "exchangeRateId", nullable = false)
    public ExchangeRateEntity getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRateEntity exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getCommissionPt() {
        return commissionPt;
    }

    public void setCommissionPt(BigDecimal commissionPt) {
        this.commissionPt = commissionPt;
    }
}
