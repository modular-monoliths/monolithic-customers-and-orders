package net.chrisrichardson.monolithic.customersandorders.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes=ApplicationIntegrationTest.Config.class)
public class ApplicationIntegrationTest {

  @Configuration
  @Import(CustomersAndOrdersConfiguration.class)
  @EnableAutoConfiguration
  public static class Config {
  }

  @LocalServerPort
  private int port;

  @Test
  public void shouldDoSomething() {

  }

}
