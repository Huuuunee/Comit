package com.example.comit.domain.auth.presentation;

import com.example.comit.domain.auth.presentation.dto.request.UserSignUpRequest;
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

    @PostMapping("signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest){
        signUpService.execute(userSignUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
