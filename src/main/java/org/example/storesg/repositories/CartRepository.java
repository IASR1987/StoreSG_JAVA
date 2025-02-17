package org.example.storesg.repositories;


import org.example.storesg.entities.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    List<Cart> findByIdProduct_Id(Integer productId);
}


