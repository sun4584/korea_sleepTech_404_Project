package com.example.back404.teamproject.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ResponseDto<T> setSuccess() {
        ResponseDto<T> response = new ResponseDto<>();
        response.success = true;
        return response;
    }

    public static <T> ResponseDto<T> setSuccess(String message) {
        ResponseDto<T> response = new ResponseDto<>();
        response.success = true;
        response.message = message;
        return response;
    }

    public static <T> ResponseDto<T> setSuccess(String message, T data) {
        ResponseDto<T> response = new ResponseDto<>();
        response.success = true;
        response.message = message;
        response.data = data;
        return response;
    }

    public static <T> ResponseDto<T> setFailed(String message) {
        ResponseDto<T> response = new ResponseDto<>();
        response.success = false;
        response.message = message;
        return response;
    }
}