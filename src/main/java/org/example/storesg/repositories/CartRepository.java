package org.example.storesg.repositories;


import org.example.storesg.entities.Cart;
import org.example.storesg.entities.Products;
import org.example.storesg.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    List<Cart> findByIdProduct_Id(Integer productId);
    ArrayList<Cart> findByIdUser(User user);

    void removeCartsByIdUser(User idUser);

    void removeCartsById(Integer id);

    Optional<Cart> findByIdUserAndIdProduct(User user, Products products);
}


