package com.example.comit.domain.user.service.impl;

import com.example.comit.domain.user.repository.UserRepository;
import com.example.comit.domain.user.service.UserService;
import com.example.comit.domain.user.domain.entity.*;
import com.example.comit.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrentUserUtil currentUserUtil;

    public void logOut(){
        User user = currentUserUtil.currentUser();
        user.exchangeRefreshToken("");
    }

}
