package com.collaball.domain.auth.controller;

import com.collaball.common.api.code.ResponseCode;
import com.collaball.common.api.response.ApiResponse;
import com.collaball.domain.auth.dto.LoginRequest;
import com.collaball.domain.auth.dto.SignupRequest;
import com.collaball.domain.auth.dto.TokenResponse;
import com.collaball.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(@Valid @RequestBody SignupRequest request) {
        authService.signup(request);
        return ApiResponse.created(ResponseCode.SIGNUP_SUCCESS);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest request) {
        TokenResponse tokens = authService.login(request);
        return ApiResponse.ok(ResponseCode.LOGIN_SUCCESS, tokens);
    }
}
