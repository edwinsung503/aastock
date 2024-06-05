package com.aastock.stock.service;

import java.util.List;

public interface StockDeleteService {
  
  void deleteStocks(List<String> stockId);
}
