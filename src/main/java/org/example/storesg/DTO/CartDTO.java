package org.example.storesg.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.storesg.entities.Cart;

@Getter
@Setter
public class CartDTO {
    private Integer id;          // El ID del carrito
    private Integer userId;      // El ID del usuario
    private Integer productId;   // El ID del producto
    private Integer quantity;    // La cantidad del producto en el carrito

    // Constructor para mapear un Cart a CartDTO
    public CartDTO(Cart cart) {
        this.id = cart.getId();
        this.userId = cart.getIdUser().getId();  // Solo tomamos el ID del usuario
        this.productId = cart.getIdProduct().getId();
        this.quantity = cart.getQuantity();
    }

    // Constructor para crear un CartDTO desde los datos proporcionados
    public CartDTO(Integer userId, Integer productId, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Constructor vacío necesario para la deserialización
    public CartDTO() {
        // Constructor vacío necesario para que Spring lo pueda deserializar
    }
}
