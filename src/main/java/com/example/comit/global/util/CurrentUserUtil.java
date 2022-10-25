package com.example.comit.global.util;

import com.example.comit.domain.user.exception.UserNotFoundException;
import com.example.comit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.comit.domain.user.domain.entity.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserUtil {

    private final UserRepository userRepository;

    public User currentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("유저가 존재하지 않습니다"));
    }

}
