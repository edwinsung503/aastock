package com.aastock.stock.infra;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    public ApiResponse(final String code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder();
    }

    public static class ApiResponseBuilder<T> {
        private String code;
        private String message;
        private T data;

        ApiResponseBuilder() {

        }

        public ApiResponseBuilder<T> status(Syscode syscode) {
        this.code = syscode.getCode();
        this.message = syscode.getMessage();
        return this;
        }

        // public ApiResponseBuilder<T> code(final String code) {
        //   this.code = code;
        //   return this;
        // }

        // public ApiResponseBuilder<T> message(final String message) {
        //   this.message = message;
        //   return this;
        // }

        public ApiResponseBuilder<T> data(final T data) {
        this.data = data;
        return this;
        }

        public ApiResponse<T> build() {
        return new ApiResponse(this.code, this.message, this.data);
        }

        public String toString() {
        String var10000 = this.code;
        return "ApiResponse.ApiResponseBuilder(code=" + var10000 + ", message="
            + this.message + ", data=" + String.valueOf(this.data) + ")";
        }
    }
}


