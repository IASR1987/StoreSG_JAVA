package org.example.storesg.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.storesg.entities.Cart;

import java.math.BigDecimal;

@Getter
@Setter
public class CartDTO {
    private Integer id;          // El ID del carrito
    private Integer userId;      // El ID del usuario
    private Integer productId;   // El ID del producto
    private Integer quantity;    // La cantidad del producto en el carrito
    private String name;  // El nombre del producto
    private String imgSrc;  // La imagen del producto
    private BigDecimal price;  // El precio del producto

    // Constructor para mapear un Cart a CartDTO
    public CartDTO(Cart cart) {
        this.id = cart.getId();
        this.userId = cart.getIdUser().getId();  // Solo tomamos el ID del usuario
        this.productId = cart.getIdProduct().getId();
        this.quantity = cart.getQuantity();
        this.name = cart.getIdProduct().getName();
        this.imgSrc = cart.getIdProduct().getImgSrc();
        this.price = cart.getIdProduct().getPrice();
    }

    // Constructor para crear un CartDTO desde los datos proporcionados
    public CartDTO(Integer userId, Integer productId, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartDTO(Integer id, Integer userId, Integer productId, Integer quantity, String name, String imgSrc, BigDecimal price) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.name = name;
        this.imgSrc = imgSrc;
        this.price = price;
    }

    // Constructor vacío necesario para la deserialización
    public CartDTO() {
        // Constructor vacío necesario para que Spring lo pueda deserializar
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getUserId() {
        return userId;
    }
}
