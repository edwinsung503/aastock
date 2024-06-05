package com.aastock.stock.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.aastock.stock.dto.response.StockDTO;
import com.aastock.stock.service.StockService;

@Service
public class StockServiceImpl implements StockService {

  private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

  @Autowired
  private RestTemplate restTemplate;

  @Value(value = "${domain.aastockAuth}")
  private String aastock;

  @Value(value = "${aastock.url}")
  private String url;

  @Value(value = "${aastock.auth}")
  private String auth;

  @Value(value = "${aastock.referer}")
  private String referer;
  
  @Override
  public StockDTO getStocks(List<String> stockId){
    HttpHeaders headers= creaHttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>("",headers);
    ResponseEntity<String> response;
    try {
      response = restTemplate.exchange(aastock, HttpMethod.GET, entity, String.class);
    } catch (Exception e) {
      logger.error("Error fetching stock data", e);
      throw new RuntimeException("Error fetching stock data", e);
    }
    String responseBody = response.getBody();
    logger.info("Response from aastock: {}", responseBody);

    List<String> stockList = stockId.stream()
                                    .map(StockServiceImpl::formatElement)
                                    .collect(Collectors.toList());
        
    logger.info("Formatted stock list: {}", stockList);

    String result = String.join("%2C", stockList);
    logger.info("Concatenated stock list: {}", result);

    String finalUrl = String.format("%s%s&grp0=%s%%7C127%%2C59%%2C29%%7CF%%3DY", url, responseBody, result);
    logger.info("Final URL: {}", finalUrl);

    StockDTO stockDTO;
    try {
      ResponseEntity<String> rawResponse = restTemplate.getForEntity(finalUrl, String.class);
      logger.info("Raw response from final URL: {}", rawResponse.getBody());

      stockDTO = restTemplate.getForObject(finalUrl, StockDTO.class);
      logger.info("Deserialized StockDTO: {}", stockDTO);
    } catch (Exception e) {
        logger.error("Error fetching stock DTO", e);
        throw new RuntimeException("Error fetching stock DTO", e);
    }
    return stockDTO;
  }

  private HttpHeaders creaHttpHeaders(){
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set(HttpHeaders.REFERER,referer);
    headers.set("Auth","Bearer ".concat(auth));
    return headers;
  }
  
  private static String formatElement(String element){
    try {
      int number = Integer.parseInt(element);
      return String.format("%05d.HK", number);
    } catch (NumberFormatException e) {
        return element + ".US";
    }
  }
}
