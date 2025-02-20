package org.example.storesg.controllers;


import org.example.storesg.DTO.OrdersBuyDTO;
import org.example.storesg.entities.OrdersBuy;
import org.example.storesg.entities.User;
import org.example.storesg.repositories.OrdersBuyRepository;
import org.example.storesg.repositories.ProductRepository;
import org.example.storesg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/OrderBuy")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderBuyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdersBuyRepository ordersBuyRepository;

    @GetMapping(path = "/all")
    public List<OrdersBuyDTO> getAllOrderBuy() {
        List<OrdersBuy> orders = (List<OrdersBuy>) ordersBuyRepository.findAll();
        List<OrdersBuyDTO> ordersDTO = new ArrayList<>();

        for(OrdersBuy order : orders){
            ordersDTO.add(new OrdersBuyDTO(order.getId(), order.getIdUser().getId(), order.getState(), order.getDateOrder(), order.getPayment(), order.getTotal()));
        }

        return ordersDTO;
    }

    @PostMapping("/addOrderBuy")
    public void addOrderBuy(@RequestParam Integer Total) {

        OrdersBuy ordersBuy = new OrdersBuy();
        Optional<User> user = userRepository.findById(1);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        ordersBuy.setIdUser(user.get());

        ordersBuy. setDateOrder(Instant.now());
        ordersBuy.setState("Pendiente");
        ordersBuy.setPayment("Tarjeta");
        ordersBuy.setTotal(BigDecimal.valueOf(Total));
        ordersBuyRepository.save(ordersBuy);
    }
}
