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
      private String s0; //stockNo
      private String s1; //stockCode
      //private String s134;
      private String r0;// stock last update time
      private String r1;//前收市價
      private String r2;//收市價
      private String r3;//開市價
      private String r4;//當天最高價
      private String r5;//當天最低價
      private String r6;//成交金額
      private String r7;//成交量
      //private String r108;
      //private String r115;
      //private String r118;//派息
      //private String r132;
      //private String r166;
      //private String r167;
      //private String r173;
      //private String r174;
      //private String r180;
      //private String r181;
      //private String r183;
      //private String r189;
      //private String r190;
      //private String r191;
      //private String r192;
      //private String c5;
      //private String c6;
    }
  
}