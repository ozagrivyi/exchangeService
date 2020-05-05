package co.ozdev.persistance.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "COMMISSION")
@Entity
public class CommissionEntity {
    private Long id;
    private ExchangePairEntity exchangePair;
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
    @MapsId
    @JoinColumn(name = "id")
    public ExchangePairEntity getExchangePair() {
        return exchangePair;
    }

    public void setExchangePair(ExchangePairEntity exchangePair) {
        this.exchangePair = exchangePair;
    }

    public BigDecimal getCommissionPt() {
        return commissionPt;
    }

    public void setCommissionPt(BigDecimal commissionPt) {
        this.commissionPt = commissionPt;
    }
}
