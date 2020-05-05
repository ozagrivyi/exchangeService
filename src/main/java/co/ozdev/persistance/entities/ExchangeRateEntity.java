package co.ozdev.persistance.entities;


import co.ozdev.model.OperationType;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "EXCHANGE_RATE")
@Entity
public class ExchangeRateEntity {
    private Long id;
    private BigDecimal rate;
    private ExchangePairEntity exchangePair;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @MapsId
    @JoinColumn(name="id", nullable=false)
    public ExchangePairEntity getExchangePair() {
        return exchangePair;
    }

    public void setExchangePair(ExchangePairEntity exchangePair) {
        this.exchangePair = exchangePair;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
