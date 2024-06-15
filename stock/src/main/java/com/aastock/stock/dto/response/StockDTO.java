package com.aastock.stock.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StockDTO {

    private Root root;

    @Getter
    @Setter
    public static class Root{
      private List<Gp> gp;
    }

    @Getter
    @Setter
    public static class Gp{
      private List<Stock> stock;
    }

    @Getter
    @Setter
    public static class Stock{
      private String s0;
      private String s1;
      private String s134;
      private String r0;
      private String r1;
      private String r2;
      private String r3;
      private String r4;
      private String r5;
      private String r6;
      private String r7;
      private String r108;
      private String r115;
      private String r118;
      private String r132;
      private String r166;
      private String r167;
      private String r173;
      private String r174;
      private String r180;
      private String r181;
      private String r183;
      private String r189;
      private String r190;
      private String r191;
      private String r192;
      private String c5;
      private String c6;
    }
  
}