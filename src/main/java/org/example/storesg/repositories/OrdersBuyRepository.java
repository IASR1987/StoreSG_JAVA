package org.example.storesg.repositories;

import org.example.storesg.entities.OrdersBuy;
import org.springframework.data.repository.CrudRepository;

public interface OrdersBuyRepository extends CrudRepository<OrdersBuy, Integer> {

    OrdersBuy findTopByOrderByIdDesc();
}
