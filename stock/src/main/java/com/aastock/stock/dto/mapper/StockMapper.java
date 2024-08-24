package com.aastock.stock.dto.mapper;

public class StockMapper {
  
  public static String mapToStockString(String s0, String s1, String r0, String r1, String r2, 
                                          String r3, String r4, String r5, String r6, 
                                          String r7) {
        StringBuilder stockInfo = new StringBuilder();

        stockInfo.append("Stock No: ").append(s0).append("\n")
                 .append("Stock Code: ").append(s1).append("\n")
                 .append("Last Update Time: ").append(r0).append("\n")
                 .append("Previous Close Price: ").append(r1).append("\n")
                 .append("Closing Price: ").append(r2).append("\n")
                 .append("Opening Price: ").append(r3).append("\n")
                 .append("Highest Price of the Day: ").append(r4).append("\n")
                 .append("Lowest Price of the Day: ").append(r5).append("\n")
                 .append("Transaction Amount: ").append(r6).append("\n")
                 .append("Transaction Volume: ").append(r7);

        return stockInfo.toString();
    }
}
