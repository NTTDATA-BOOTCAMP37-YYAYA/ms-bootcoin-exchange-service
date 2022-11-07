package com.nttdata.bootcamp.msbootcoinexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**.*/
@SpringBootApplication
@EnableEurekaClient
public class MsBootCoinExchangeApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsBootCoinExchangeApplication.class, args);
  }

}
