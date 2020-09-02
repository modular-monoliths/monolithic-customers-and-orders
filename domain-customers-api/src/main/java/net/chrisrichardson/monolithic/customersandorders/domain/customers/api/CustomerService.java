package net.chrisrichardson.monolithic.customersandorders.domain.customers.api;

import net.chrisrichardson.monolithic.customersandorders.domain.common.Money;

import java.util.Optional;

public interface CustomerService {
  CustomerDto createCustomer(String name, Money creditLimit);

  void reserveCredit(Long customerId, Money orderTotal);
  void unreserveCredit(long customerId, Money orderTotal);

  Optional<CustomerDto> findById(long customerId);
}
