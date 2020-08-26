package net.chrisrichardson.monolithic.customersandorders.domain.customers;

import net.chrisrichardson.monolithic.customersandorders.domain.money.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.AvailableCreditManagement;

import javax.transaction.Transactional;

public interface CustomerService extends AvailableCreditManagement {
  @Transactional
  Customer createCustomer(String name, Money creditLimit);
}
