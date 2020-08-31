package net.chrisrichardson.monolithic.customersandorders.domain.customers.api;

import net.chrisrichardson.monolithic.customersandorders.domain.common.Money;

public interface CustomerService {
  CustomerDto createCustomer(String name, Money creditLimit);

  void reserveCredit(Long customerId, Money orderTotal);
  void unreserveCredit(long customerId, Money orderTotal);
}
