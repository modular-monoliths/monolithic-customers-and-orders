package net.chrisrichardson.monolithic.customersandorders.domain.customers;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
