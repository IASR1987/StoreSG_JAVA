package org.example.storesg.controllers;


import jakarta.transaction.Transactional;
import org.example.storesg.DTO.CartDTO;
import org.example.storesg.entities.Cart;
import org.example.storesg.entities.Products;
import org.example.storesg.entities.User;
import org.example.storesg.repositories.CartRepository;
import org.example.storesg.repositories.ProductRepository;
import org.example.storesg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


// Indica que esta clase es un controlador REST, lo que significa que manejará peticiones HTTP y devolverá respuestas en formato JSON.
@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

/*
    @GetMapping(path="/all")
    public Iterable<Cart> getAllCart() {
        Iterable<Cart> carts = cartRepository.findAll();
        System.out.println(carts);
        return carts;
    }*/

    @GetMapping(path="/all")
    public List<CartDTO> getAllCart() {
        List<Cart> carts = (List<Cart>) cartRepository.findAll();
        List<CartDTO> cartsDTO = new ArrayList<>();

        for(Cart cart : carts){
            Optional<Products> product = productRepository.findById(cart.getIdProduct().getId());
            Optional<User> user = userRepository.findById(cart.getIdUser().getId());

            cartsDTO.add(new CartDTO(cart.getId(), user.get().getId(), product.get().getId(),
                cart.getQuantity(), cart.getName(), cart.getImgSrc(), cart.getPrice()));
        }

        return cartsDTO;
    }

    @PostMapping("/addCart")
    public ResponseEntity<?> addCart(@RequestBody CartDTO cartDTO) {
        // Validar que los IDs no sean nulos
        if (cartDTO.getUserId() == null) {
            return ResponseEntity.badRequest().body("El 'userId' no puede ser nulo.");
        }
        if (cartDTO.getProductId() == null) {
            return ResponseEntity.badRequest().body("El 'productId' no puede ser nulo.");
        }

        // Buscar el usuario y el producto en la base de datos
        Optional<User> user = userRepository.findById(cartDTO.getUserId());
        Optional<Products> product = productRepository.findById(cartDTO.getProductId());

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("El 'idUser' no es válido.");
        }
        if (product.isEmpty()) {
            return ResponseEntity.badRequest().body("El 'idProduct' no es válido.");
        }

        // Buscar si ya existe en el carrito
        Optional<Cart> ProductExist = cartRepository.findByIdUserAndIdProduct(user.get(), product.get());

        if (ProductExist.isPresent()) {
            // Si el producto ya está en el carrito, actualizar cantidad
            Cart cartItem = ProductExist.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartDTO.getQuantity());
            cartRepository.save(cartItem);
            return ResponseEntity.ok(cartItem);
        } else {
            // Si el producto no está en el carrito, crearlo
            Cart newCart = new Cart();
            newCart.setIdUser(user.get());
            newCart.setIdProduct(product.get());
            newCart.setQuantity(cartDTO.getQuantity());
            newCart.setName(product.get().getName());
            newCart.setImgSrc(product.get().getImgSrc());
            newCart.setPrice(product.get().getPrice());

            cartRepository.save(newCart);
            return ResponseEntity.ok(newCart);
        }
    }


    @GetMapping(path="/allCartByUser")
    List<Cart> findAllCartByIdUser(@RequestParam Integer id){
        // Buscar las entidades User por sus ID
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            System.out.println("Usuario con ID: " + id + " no encontrado.");
            return null;
        }

        ArrayList<Cart> listCartByUser = cartRepository.findByIdUser(user.get());

        return listCartByUser;
    }

    //BORRAR EL CARRITO DE UN USUARIO => DESPÚES DE LA COMPRA
    @Transactional
    @DeleteMapping(path="/deleteCartsByIdUser")
    public ResponseEntity<Void> deleteCartsByIdUser(@RequestParam Integer id) {
        cartRepository.removeCartsByIdUser(userRepository.findById(id).get());
        return ResponseEntity.noContent().build();
    }


    //BORRAR PRODUCTO DEL CARRITO
    @Transactional
    @DeleteMapping(path="/deleteCartsById")
    public ResponseEntity<String> deleteCartsByIdProduct(@RequestParam Integer id) {
        cartRepository.removeCartsById(id);
        return ResponseEntity.ok("Se ha borrado el producto del carrito con ID: " + id + ".");
    }

}
