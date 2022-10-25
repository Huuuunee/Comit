package com.example.comit.domain.user.service.impl;

import com.example.comit.domain.user.domain.entity.User;
import com.example.comit.domain.user.exception.PasswordWrongException;
import com.example.comit.domain.user.exception.UserAlreadyExistException;
import com.example.comit.domain.user.exception.UserNotFoundException;
import com.example.comit.domain.user.presentation.dto.request.SignInRequest;
import com.example.comit.domain.user.presentation.dto.request.SignUpRequest;
import com.example.comit.domain.user.presentation.dto.response.NewTokenResponse;
import com.example.comit.domain.user.presentation.dto.response.SignInResponse;
import com.example.comit.domain.user.repository.UserRepository;
import com.example.comit.domain.user.service.AuthService;
import com.example.comit.global.error.exception.TokenNotValidException;
import com.example.comit.global.security.jwt.TokenProvider;
import com.example.comit.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional(rollbackFor = Exception.class)
    public SignInResponse signIn(SignInRequest signInRequest){
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

    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpRequest userSignUpRequest){
        //이메일로 유저 검색후 있으면 에러처리
        if(userRepository.findByEmail(userSignUpRequest.getEmail()).isPresent()){
            throw new UserAlreadyExistException("이미 존재하는 이메일입니다");
        }
        //user 빌더패턴으로 생성 후 저장3
        User user = User.builder()
                .email(userSignUpRequest.getEmail())
                .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                .introduce(userSignUpRequest.getIntroduce())
                .name(userSignUpRequest.getName())
                .build();
        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public NewTokenResponse reToken(String token){
        String email = tokenProvider.getUserId(token,jwtProperties.getRefreshSecret());
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("유저가 존재하지 않습니다"));
        if(!user.getRefreshToken().equals(token)){
            throw new TokenNotValidException("토큰이 유효하지 않습니다");
        }
        //토큰발급
        String accessToken = tokenProvider.generatedAccessToken(email);
        String refreshToken = tokenProvider.generatedRefreshToken(email);
        //유저 refreshToken 업데이트
        user.exchangeRefreshToken(refreshToken);
        //return 값으로 빌드하기
        return NewTokenResponse.builderNewTokenResponse(accessToken,refreshToken,tokenProvider.getExpiredAtToken(accessToken,jwtProperties.getAccessSecret()));
    }

}
