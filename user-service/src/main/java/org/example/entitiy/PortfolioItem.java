package org.example.entitiy;


import jakarta.persistence.*;
import org.example.common.Ticker;


@Entity
@Table(name ="portfolio_item")
public class PortfolioItem {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue
    @Column(name ="customer_id")
    private Integer id;
    private  Integer userid;
    private org.example.common.Ticker ticker;
    private Integer quantity;
}
