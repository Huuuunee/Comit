package com.example.comit.domain.auth.presentation;

import com.example.comit.domain.auth.presentation.dto.request.SignInRequest;
import com.example.comit.domain.auth.presentation.dto.request.SignUpRequest;
import com.example.comit.domain.auth.presentation.dto.response.SignInResponse;
import com.example.comit.domain.auth.services.SignInService;
import com.example.comit.domain.auth.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final SignUpService signUpService;
    private final SignInService signInService;

    @PostMapping("signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest userSignUpRequest){
        signUpService.execute(userSignUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest){
        SignInResponse signInResponse = signInService.execute(signInRequest);
        return new ResponseEntity<>(signInResponse,HttpStatus.OK);
    }

}
