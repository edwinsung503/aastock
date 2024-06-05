package com.aastock.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import com.aastock.stock.infra.ApiResponse;
import com.aastock.stock.infra.Syscode;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> restclientExceptionHanlder(){
        return ApiResponse.<Void>builder() //return 出去俾user
        .status(Syscode.REST_CLIENT_EXCEPTION)// 出左code 同message
        .data(null)
        .build();
    }

    @ExceptionHandler(NullPointerException.class) //要同佢講捉D什麼先會捉
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> npeclientExceptionHanlder(){
        return ApiResponse.<Void>builder() //return 出去俾user
        .status(Syscode.NULL_POINTER_EXCEPTION)// 出左code 同message
        .data(null)
        .build();
    }

    @ExceptionHandler(Exception.class) //要同佢講捉D什麼先會捉
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse<String> ExceptionHanlder(Exception e){
        return ApiResponse.<String>builder() //return 出去俾user
        .status(Syscode.GENERAL_EXCEPTION)// 出左code 同message
        .data(e.getMessage())
        .build();
    }
    
}