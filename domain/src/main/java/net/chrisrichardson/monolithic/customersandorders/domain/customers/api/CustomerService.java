package net.chrisrichardson.monolithic.customersandorders.domain.customers.api;

import net.chrisrichardson.monolithic.customersandorders.domain.common.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.Customer;

import javax.transaction.Transactional;

public interface CustomerService {
  @Transactional
  Customer createCustomer(String name, Money creditLimit);

  void reserveCredit(Long customerId, Money orderTotal);
  void unreserveCredit(long customerId, Money orderTotal);
}
