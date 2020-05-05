package co.ozdev.persistance.entities;

import co.ozdev.model.Currency;

import javax.persistence.*;
import java.util.Set;

@Table(name = "EXCHANGE_PAIR")
@Entity
public class ExchangePairEntity {
    private Long id;
    private Currency currencyFrom;
    private Currency currencyTo;
    private ExchangeRateEntity exchangeRate;
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

    @OneToOne(mappedBy="exchangePair")
    @JoinColumn(name = "excjangeRateId")
    public ExchangeRateEntity getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRateEntity exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @OneToOne
    @JoinColumn(name = "commissionId")
    public CommissionEntity getCommission() {
        return commission;
    }

    public void setCommission(CommissionEntity commission) {
        this.commission = commission;
    }
}
