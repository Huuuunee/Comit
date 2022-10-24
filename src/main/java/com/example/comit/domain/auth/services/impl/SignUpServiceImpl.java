package com.example.comit.domain.auth.services.impl;

import com.example.comit.domain.auth.exception.UserAlreadyExistException;
import com.example.comit.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.example.comit.domain.auth.services.SignUpService;
import com.example.comit.domain.user.domain.entity.User;
import com.example.comit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void execute(UserSignUpRequest userSignUpRequest){
        //이메일로 유저 검색후 있으면 에러처리
        if(userRepository.findByEmail(userSignUpRequest.getEmail()).isPresent()){
            throw new UserAlreadyExistException("이미 존재하는 이메일입니다");
        }
        //user 빌더패턴으로 생성 후 저장
        User user = User.builder()
                .email(userSignUpRequest.getEmail())
                .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                .introduce(userSignUpRequest.getIntroduce())
                .name(userSignUpRequest.getName())
                .build();
        userRepository.save(user);
    }

}
