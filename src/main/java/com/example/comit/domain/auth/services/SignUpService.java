package com.example.comit.domain.auth.services;

import com.example.comit.domain.auth.presentation.dto.request.UserSignUpRequest;
import org.springframework.http.ResponseEntity;

public interface SignUpService {
    void execute(UserSignUpRequest userSignUpRequest);
}
