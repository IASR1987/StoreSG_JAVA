package org.example.storesg.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "Order_Details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_D_Details", nullable = false)
    private Integer I_D_Details;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_Product", nullable = false)
    @JsonIgnore
    private Products products;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "i_d_order_buy", referencedColumnName = "Id_Order", nullable = false)
    private OrdersBuy ordersBuy;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "Subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;

    public OrderDetail( OrdersBuy ordersBuy, Products products, Integer quantity, BigDecimal price, BigDecimal subtotal) {
        this.ordersBuy = ordersBuy;
        this.products = products;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public OrderDetail() {

    }


    public Integer getId() {
        return I_D_Details;
    }

    public void setId(Integer I_D_Details) {
        this.I_D_Details = I_D_Details;
    }

    public Products getIdProduct() {
        return products;
    }

    public void setIdProduct(Products products) {
        this.products = products;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setOrdersBuy(OrdersBuy ordersBuy) {
        this.ordersBuy = ordersBuy;
    }

    public OrdersBuy getOrdersBuy() {
        return ordersBuy;
    }
}