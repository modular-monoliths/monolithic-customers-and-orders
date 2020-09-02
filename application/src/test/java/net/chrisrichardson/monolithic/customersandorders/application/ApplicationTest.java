package net.chrisrichardson.monolithic.customersandorders.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ApplicationTest.Config.class)
public class ApplicationTest {

  @Configuration
  @Import(CustomersAndOrdersConfiguration.class)
  @EnableAutoConfiguration
  static public class Config {
  }

  @Test
  public void shouldStart() {

  }
}