package net.chrisrichardson.monolithic.customersandorders.web.customers;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.AvailableCreditManagement;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.OrdersDomainConfiguration;
import net.chrisrichardson.monolithic.customersandorders.web.orderhistory.OrderViewRepository;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes= OrdersDomainConfiguration.class)
@EnableAutoConfiguration
public class OrderViewRepositoryIntegrationTest {

  @Autowired
  private OrderViewRepository orderViewRepository;

  @MockBean
  private AvailableCreditManagement availableCreditManagement;

  @Test
  public void shouldRetrieveZeroOrders() {
    assertThat(orderViewRepository.findByCustomerId(System.currentTimeMillis()), empty());
  }
}
