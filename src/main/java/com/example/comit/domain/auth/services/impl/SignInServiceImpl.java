package com.example.comit.domain.auth.services.impl;

import com.example.comit.domain.auth.exception.PasswordWrongException;
import com.example.comit.domain.auth.exception.UserNotFoundException;
import com.example.comit.domain.auth.presentation.dto.request.SignInRequest;
import com.example.comit.domain.auth.presentation.dto.response.SignInResponse;
import com.example.comit.domain.auth.services.SignInService;
import com.example.comit.domain.user.repository.UserRepository;
import com.example.comit.domain.user.domain.entity.User;
import com.example.comit.global.security.jwt.TokenProvider;
import com.example.comit.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional(rollbackFor = Exception.class)
    public SignInResponse execute(SignInRequest signInRequest){
        //유저 있는지 없는지 검사
        User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new UserNotFoundException("유저가 존재하지 않습니다"));
        //비밀번호가 맞는지 검사
        if(!passwordEncoder.matches(signInRequest.getPassword() , user.getPassword())){
            throw new PasswordWrongException("비밀번호가 일치하지 않습니다");
        }
        //토큰발급
        String accessToken = tokenProvider.generatedAccessToken(signInRequest.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(signInRequest.getEmail());
        //유저 refreshToken 저장
        user.exchangeRefreshToken(refreshToken);
        //return 값으로 빌드하기
        return SignInResponse.builderSignInResponse(accessToken,refreshToken,tokenProvider.getExpiredAtToken(accessToken,jwtProperties.getAccessSecret()));

    }
}
