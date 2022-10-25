package com.example.comit.domain.user.presentation;

import com.example.comit.domain.user.presentation.dto.request.SignInRequest;
import com.example.comit.domain.user.presentation.dto.request.SignUpRequest;
import com.example.comit.domain.user.presentation.dto.response.NewTokenResponse;
import com.example.comit.domain.user.presentation.dto.response.SignInResponse;
import com.example.comit.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest userSignUpRequest){
        authService.signUp(userSignUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest){
        SignInResponse signInResponse = authService.signIn(signInRequest);
        return new ResponseEntity<>(signInResponse,HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<NewTokenResponse> refreshToken(@RequestHeader("RefreshToken")String token){
        NewTokenResponse newTokenResponse = authService.reToken(token);
        return new ResponseEntity<>(newTokenResponse,HttpStatus.OK);
    }

}
