package com.example.high_school_course_registration.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ResponseDto<T> setSuccess(String message, T data) {
        return new ResponseDto<>(true, message, data);
    }

    public static <T> ResponseDto<T> setFailed(String message) {
        return new ResponseDto<>(false, message, null);
    }

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(true, "요청 성공", data);
    }

    public static <T> ResponseDto<T> fail(String message, T data) {
        return new ResponseDto<>(false, message, data);
    }
}

