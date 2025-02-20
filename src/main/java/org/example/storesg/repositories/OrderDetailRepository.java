package org.example.storesg.repositories;

import org.example.storesg.entities.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
}
