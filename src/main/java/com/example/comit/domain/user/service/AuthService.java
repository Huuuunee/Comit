package com.example.comit.domain.user.service;

import com.example.comit.domain.user.presentation.dto.request.SignInRequest;
import com.example.comit.domain.user.presentation.dto.request.SignUpRequest;
import com.example.comit.domain.user.presentation.dto.response.NewTokenResponse;
import com.example.comit.domain.user.presentation.dto.response.SignInResponse;

public interface AuthService {
    NewTokenResponse reToken(String email);
    SignInResponse signIn(SignInRequest signInRequest);
    void signUp(SignUpRequest userSignUpRequest);
}
