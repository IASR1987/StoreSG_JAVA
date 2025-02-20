package org.example.storesg.DTO;

import java.math.BigDecimal;

public class OrderDetailsDTO {
    private Integer ordersBuy;  // Aquí agregamos el DTO de OrdersBuy
    private Integer products;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
    private String name;
    private String imgSrc;

    public OrderDetailsDTO(Integer ordersBuy, Integer products, Integer quantity, BigDecimal price, BigDecimal subtotal, String name, String imgSrc) {
        this.ordersBuy = ordersBuy;
        this.products = products;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
        this.name = name;
        this.imgSrc = imgSrc;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getOrdersBuy() {
        return ordersBuy;
    }

    public void setOrdersBuy(Integer ordersBuy) {
        this.ordersBuy = ordersBuy;
    }

    public Integer getProducts() {
        return products;
    }

    public void setProducts(Integer products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }


}

