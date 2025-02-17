package org.example.storesg.repositories;

import org.example.storesg.entities.Products;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Products, Integer> {
    public List<Products> findByName(String name);

    List<Products> findBySerie(String serie);
}
