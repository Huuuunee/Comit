package com.example.comit.domain.auth.services;

import com.example.comit.domain.auth.presentation.dto.request.SignUpRequest;

public interface SignUpService {
    void execute(SignUpRequest userSignUpRequest);
}
