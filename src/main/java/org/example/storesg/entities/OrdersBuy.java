package org.example.storesg.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class OrdersBuy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Order", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_User", nullable = false)
    private User idUser;

    @Column(name = "Date_Order")
    private Instant dateOrder;

    @ColumnDefault("'Pendiente'")
    @Lob
    @Column(name = "State")
    private String state;

    @Lob
    @Column(name = "Payment", nullable = false)
    private String payment;

    @Column(name = "Total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Instant getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Instant dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}