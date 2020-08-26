package net.chrisrichardson.monolithic.customersandorders.domain.customers.api;

import java.util.Optional;

public interface CustomerDetailsRepository {

  Optional<CustomerDetails> findById(long customerId);
}
