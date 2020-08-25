package net.chrisrichardson.monolithic.customersandorders.domain.orders;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
  List<Order> findByCustomerId(long customerId);
}
