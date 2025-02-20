package org.example.storesg.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;


@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // O GenerationType.AUTO
    @Column(name = "I_D_Cart", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore  // Esto evita que Jackson intente serializar el User proxy.
    @JoinColumn(name = "I_D_User", nullable = false)
    private User idUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore  // Esto evita que Jackson intente serializar el User proxy.
    @JoinColumn(name = "I_D_Product", nullable = false)
    private Products idProduct;

    @ColumnDefault("1")
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "imgSrc", nullable = false)
    private String imgSrc;

    @Column(name = "price", nullable = false)
    private BigDecimal price;



    public Cart() {
    }

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

    public Products getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Products idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}