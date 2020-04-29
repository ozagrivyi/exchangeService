package co.ozdev.persistance.entities;


import co.ozdev.model.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "EXCHANGE_RATE")
@Entity
public class ExchangeRateEntity {
    private Long id;
    private Currency currencyFrom;
    private Currency currencyTo;
    private BigDecimal rate;
    private CommissionEntity commission;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(Currency currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(Currency currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "exchangeRate")
    public CommissionEntity getCommission() {
        return commission;
    }

    public void setCommission(CommissionEntity commission) {
        this.commission = commission;
    }
}
