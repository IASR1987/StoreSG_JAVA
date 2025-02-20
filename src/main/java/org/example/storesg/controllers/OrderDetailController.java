package org.example.storesg.controllers;


import jakarta.transaction.Transactional;
import org.example.storesg.DTO.OrderDetailsDTO;
import org.example.storesg.entities.*;
import org.example.storesg.repositories.OrderDetailRepository;
import org.example.storesg.repositories.OrdersBuyRepository;
import org.example.storesg.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/OrderDetail")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderDetailController {

    @Autowired
    private OrdersBuyRepository ordersBuyRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/all")
    public List<OrderDetailsDTO> getAllOrderDetail() {
        List<OrderDetail> orders = (List<OrderDetail>) orderDetailRepository.findAll();
        List<OrderDetailsDTO> ordersDTO = new ArrayList<>();
        System.out.println("orders size"+orders.size());

        for(OrderDetail order : orders){

            Optional<OrdersBuy> ordersBuy = ordersBuyRepository.findById(order.getOrdersBuy().getId());
            Optional<Products> product = productRepository.findById(order.getIdProduct().getId());


            // Imprimir los valores de las variables antes de agregarlos al DTO
            System.out.println("Order ID: " + order.getId());
            System.out.println("OrdersBuy ID: " + ordersBuy.get().getId());
            System.out.println("Product ID: " + product.get().getId());
            System.out.println("Quantity: " + order.getQuantity());
            System.out.println("Price: " + order.getPrice());
            System.out.println("Subtotal: " + order.getSubtotal());

            System.out.println("ordersDTO size"+ordersDTO.size());
            ordersDTO.add(new OrderDetailsDTO( order.getOrdersBuy().getId(), product.get().getId(),
                    order.getQuantity(), order.getPrice(), order.getSubtotal(), product.get().getName(), product.get().getImgSrc()));
        }

        return ordersDTO;
    }

    @Transactional
    @PostMapping("/addOrderDetail")
    String addOrderDetail(@RequestParam Integer productId, @RequestParam Integer quantity, @RequestParam Integer price) {
        OrderDetail orderDetail = new OrderDetail();

        // Verificar si hay órdenes previas
       OrdersBuy lastOrder = ordersBuyRepository.findTopByOrderByIdDesc();
        if (lastOrder == null) {
            System.out.println(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la última orden."));
        }

        // Buscar el producto en la base de datos
        Optional<Products> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            System.out.println(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto no encontrado."));
        }
        orderDetail.setOrdersBuy(lastOrder);
        orderDetail.setIdProduct(product.get());
        orderDetail.setQuantity(quantity);
        orderDetail.setPrice(BigDecimal.valueOf(price));

        //calculamos el subtotal
        BigDecimal subtotal  = BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(quantity));
        orderDetail.setSubtotal(subtotal);

        orderDetailRepository.save(orderDetail);

        return "Producto añadido al carrito";
    }

}