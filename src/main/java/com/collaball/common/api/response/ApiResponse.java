package com.collaball.common.api.response;

import com.collaball.common.api.code.ErrorCode;
import com.collaball.common.api.code.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(boolean success, String code, String message, T data) {

    public static <T> ResponseEntity<ApiResponse<T>> ok(ResponseCode responseCode, T data) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, responseCode.getCode(), responseCode.getMessage(), data)
        );
    }

    public static ResponseEntity<ApiResponse<Void>> ok(ResponseCode responseCode) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, responseCode.getCode(), responseCode.getMessage(), null)
        );
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(ResponseCode responseCode, T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, responseCode.getCode(), responseCode.getMessage(), data));
    }

    public static ResponseEntity<ApiResponse<Void>> created(ResponseCode responseCode) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, responseCode.getCode(), responseCode.getMessage(), null));
    }

    public static ResponseEntity<ApiResponse<Void>> error(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ApiResponse<>(false, errorCode.getCode(), errorCode.getMessage(), null));
    }

    public static ResponseEntity<ApiResponse<Void>> error(ErrorCode errorCode, String message) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ApiResponse<>(false, errorCode.getCode(), message, null));
    }
}
