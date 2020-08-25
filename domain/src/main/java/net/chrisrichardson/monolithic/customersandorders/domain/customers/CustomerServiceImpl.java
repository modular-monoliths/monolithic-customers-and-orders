package net.chrisrichardson.monolithic.customersandorders.domain.customers;

import net.chrisrichardson.monolithic.customersandorders.domain.common.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectRetrievalFailureException;

import javax.transaction.Transactional;

public class CustomerServiceImpl implements CustomerService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  @Transactional
  public Customer createCustomer(String name, Money creditLimit) {
    Customer customer = Customer.create(name, creditLimit);
    customer = customerRepository.save(customer);
    return customer;
  }

  @Override
  public void reserveCredit(Long customerId, Money orderTotal) {
    Customer customer = findCustomer(customerId);
    customer.reserveCredit(orderTotal);
  }

  private Customer findCustomer(Long customerId) {
    return customerRepository.findById(customerId)
            .orElseThrow(() -> new ObjectRetrievalFailureException(Customer.class, customerId) {
            });
  }

  @Override
  public void unreserveCredit(long customerId, Money orderTotal) {
    Customer customer = findCustomer(customerId);
    customer.unreserveCredit(orderTotal);

  }
}
