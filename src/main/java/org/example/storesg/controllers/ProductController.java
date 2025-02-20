package org.example.storesg.controllers;

import org.example.storesg.entities.Products;
import org.example.storesg.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping(path="/all")
    public Iterable<Products> getAllProduct() {
        return productRepository.findAll();
    }

    // Mostrar productos por nombre
    @GetMapping("/findByName")
    public List<Products> getProductsByName(@RequestParam String name) {
        return productRepository.findByName(name);
    }

    @GetMapping("/findById")
    public Products getProductById(@RequestParam Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping("/findBySerie")
    public List<Products> getProductsBySerie(@RequestParam String serie) {
        return productRepository.findBySerie(serie);
    }


    @PostMapping("/addProduct")
    public Products addProduct(@RequestBody Products product) {
        return productRepository.save(product);
    }

}
