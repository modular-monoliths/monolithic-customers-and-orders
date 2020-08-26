package net.chrisrichardson.monolithic.customersandorders.domain.customers.api;

import net.chrisrichardson.monolithic.customersandorders.domain.money.Money;

public interface AvailableCreditManagement {
  void reserveCredit(Long customerId, Money orderTotal);
  void unreserveCredit(long customerId, Money orderTotal);
}
