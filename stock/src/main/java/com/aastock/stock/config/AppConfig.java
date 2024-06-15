package com.aastock.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

  @Bean
  RestTemplate restTemplate(){
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getInterceptors().add((request, body, execution) -> {
      System.out.println("Request URI: " + request.getURI());
      System.out.println("Request Headers: " + request.getHeaders());
      System.out.println("Request Body: " + new String(body));
      return execution.execute(request, body);
    });
    return restTemplate;
  }
  
}
