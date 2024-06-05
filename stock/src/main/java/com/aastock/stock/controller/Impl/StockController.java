package com.aastock.stock.controller.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aastock.stock.controller.StockOperation;
import com.aastock.stock.dto.response.StockDTO;
import com.aastock.stock.service.StockAddService;
import com.aastock.stock.service.StockDeleteService;
import com.aastock.stock.service.StockService;

@RestController
@RequestMapping(value = "/aastock/stockprice/api/v1")
public class StockController implements StockOperation{

    @Autowired
    private StockService stockService;

    @Autowired
    private StockAddService stockAddService;

    @Autowired
    private StockDeleteService stockDeleteService;

    @Override
    public ResponseEntity<String> addStocks(List<String> stockId){
      stockAddService.addStocks(stockId);
      return new ResponseEntity<String>("Stocks add successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteStocks(List<String> stockId){
      stockDeleteService.deleteStocks(stockId);
      return new ResponseEntity<String>("Stocks delete successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<StockDTO> getStocks(List<String> stockId){
      StockDTO stockDTO = stockService.getStocks(stockId);
      //return new ResponseEntity<String>("Stock Price get successfully", HttpStatus.CREATED);
      return ResponseEntity.ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(stockDTO);
    }
  
}
