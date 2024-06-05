package com.aastock.stock.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.aastock.stock.dto.response.StockDTO;

public interface StockOperation {

  //http://localhost:8100/aastock/stockprice/api/v1/add/stocks?
  @GetMapping(value = "/add/stocks")
  ResponseEntity<String> addStocks(@RequestParam(value="sotckId") List<String> stockId);
  
  @GetMapping(value = "/delete/stocks")
  ResponseEntity<String> deleteStocks(@RequestParam(value="sotckId") List<String> stockId);

  //http://localhost:8100/aastock/stockprice/api/v1/get/stocks?
  @GetMapping(value = "/get/stocks")
  ResponseEntity<StockDTO> getStocks(@RequestParam(value="stockId") List<String> stockId);
}
