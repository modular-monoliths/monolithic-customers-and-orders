package net.chrisrichardson.monolithic.customersandorders.web.orderhistory;

import java.util.List;

public interface OrderViewRepository {
  List<OrderView> findByCustomerId(long customerId);
}
