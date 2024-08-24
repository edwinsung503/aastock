package com.aastock.stock.service;

import java.util.List;
import com.aastock.stock.dto.response.StockDTO;

public interface StockService {

  String getStocks(List<String> stockId);
}
