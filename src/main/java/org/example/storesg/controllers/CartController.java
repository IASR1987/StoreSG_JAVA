package org.example.storesg.controllers;


import org.example.storesg.DTO.CartDTO;
import org.example.storesg.entities.Cart;
import org.example.storesg.entities.Products;
import org.example.storesg.repositories.CartRepository;
import org.example.storesg.repositories.ProductRepository;
import org.example.storesg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


// Indica que esta clase es un controlador REST, lo que significa que manejará peticiones HTTP y devolverá respuestas en formato JSON.
@RestController

// Define el prefijo para las rutas de este controlador. Todas las rutas dentro de esta clase comenzarán con "/cart".
@RequestMapping("/cart")
public class CartController {

    // Inyección automática del repositorio de `Cart`, para interactuar con la base de datos.
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    @CrossOrigin(origins = "http://localhost:4200")


    @GetMapping(path="/all")
    public Iterable<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    @PostMapping("/addCart")
    public Cart addToCart(@RequestBody CartDTO cartDTO) {

        Cart newCart;
        // Encuentra el producto en el repositorio por ID
        Optional<Products> productOpt = productRepository.findById(cartDTO.getProductId());

        if (productOpt.isPresent()) {
            System.out.println("Producto encontrado");
            // Si el producto se encuentra, usa el valor del Optional
            newCart = new Cart(cartDTO.getUserId(), Optional.of(productOpt.get()), cartDTO.getQuantity());
        } else {
            // Si el producto no se encuentra, crea un carrito con valores por defecto
            newCart = new Cart(cartDTO.getUserId(), 1, cartDTO.getQuantity());
        }

        // Guarda el carrito y devuelve el resultado
        return cartRepository.save(newCart);
    }


}





