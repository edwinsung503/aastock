package com.aastock.stock.service.Impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.aastock.stock.dto.mapper.StockMapper;
import com.aastock.stock.dto.response.StockDTO;
import com.aastock.stock.service.GetAuthKeyService;
import com.aastock.stock.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockServiceImpl implements StockService {

  private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private GetAuthKeyService getAuthKeyService;

  @Value(value = "${domain.aastockAuth}")
  private String aastock;

  @Value(value = "${aastock.url}")
  private String url;

  @Value(value = "${aastock.auth}")
  private String auth;

  @Value(value = "${aastock.referer}")
  private String referer;
  
  @Override
  public String getStocks(List<String> stockId){
    HttpHeaders headers= creaHttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null,headers);
    ResponseEntity<String> response;
    try {
      response = restTemplate.exchange(aastock, HttpMethod.GET, entity, String.class);
    } catch (Exception e) {
      //logger.error("Error fetching stock data", e);
      throw new RuntimeException("Error fetching stock data", e);
    }
    String responseBody = response.getBody();
    
    //logger.info("Response from aastock: {}", responseBody);

    List<String> stockList = stockId.stream()
                                    .map(StockServiceImpl::formatElement)
                                    .collect(Collectors.toList());
    //logger.info("Formatted stock list: {}", stockList);

    String result = String.join(",", stockList);
    //logger.info("Concatenated stock list: {}", result);
    String finalUrl = String.format("%s%s&grp0=%s|127,76,40,6|F=Y",url, responseBody, result);

    StockDTO stockDTO;
    try {
      HttpEntity<String> entity2 = new HttpEntity<>("", headers);
      ResponseEntity<StockDTO> rawResponse = restTemplate.exchange(finalUrl, HttpMethod.GET, entity2, StockDTO.class);
      stockDTO = rawResponse.getBody();
      //logger.info("Deserialized StockDTO: {}", stockDTO.toString());
    } catch (HttpClientErrorException | ResourceAccessException e) {
        //logger.error("Error fetching stock DTO", e);
        throw new RuntimeException("Error fetching stock DTO", e);
    }

    String formattedStockData = "" ;
    if (stockDTO != null && stockDTO.getRoot() != null) {
      List<StockDTO.Stock> stocks = stockDTO.getRoot().getGp().get(0).getStock();
      formattedStockData = stocks.stream()
          .map(stock -> StockMapper.mapToStockString(
              stock.getS0(), stock.getS1(), stock.getR0(),
              stock.getR1(), stock.getR2(), stock.getR3(),
              stock.getR4(), stock.getR5(), stock.getR6(),
              stock.getR7()))
          .collect(Collectors.joining("\n\n"));

    }
    return formattedStockData;
  }

  private HttpHeaders creaHttpHeaders(){
    HttpHeaders headers = new HttpHeaders();
    String authToken = getAuthKeyService.getAuthKey();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set(HttpHeaders.REFERER,referer);
    headers.set("Auth","Bearer ".concat(authToken));
    //headers.set(HttpHeaders.HOST,"fctdata.aastocks.com");
    //headers.set(HttpHeaders.COOKIE,"aa_cookie=58.153.74.223_54720_1717728793");
    //headers.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36");
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
