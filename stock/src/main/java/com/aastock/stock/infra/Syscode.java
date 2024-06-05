package com.aastock.stock.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Syscode {

    OK("000000", "OK."), //
    NOTFOUND("000001", "Resource Not Found"),
    REST_CLIENT_EXCEPTION("90000","RestClientExcpetion - aaStock Service is unavailable"),
    NULL_POINTER_EXCEPTION("90002","NPE"),
    WRONG_CURRENCY("90004","Wrong Currency"),
    GENERAL_EXCEPTION("90003","AllArgurmentException");


    private String code;
    private String message;
    
}
