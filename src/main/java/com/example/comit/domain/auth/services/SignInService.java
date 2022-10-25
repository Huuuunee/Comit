package com.example.comit.domain.auth.services;

import com.example.comit.domain.auth.presentation.dto.request.SignInRequest;
import com.example.comit.domain.auth.presentation.dto.response.SignInResponse;

public interface SignInService {
    SignInResponse execute(SignInRequest signInRequest);
}
