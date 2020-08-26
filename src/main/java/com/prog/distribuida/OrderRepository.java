package com.prog.distribuida;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Orders> {

  List<Orders> customerOrders(Long customerId) {
    return list("customerId", customerId);
  }

}
