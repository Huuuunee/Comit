package com.example.comit.domain.user.service.impl;

import com.example.comit.domain.user.presentation.dto.response.UserInfoResponse;
import com.example.comit.domain.user.service.UserService;
import com.example.comit.domain.user.domain.entity.*;
import com.example.comit.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CurrentUserUtil currentUserUtil;

    @Transactional(rollbackFor = Exception.class)
    public void logOut(){
        User user = currentUserUtil.currentUser();
        user.exchangeRefreshToken("");
    }

    @Transactional(rollbackFor = Exception.class)
    public UserInfoResponse userInfo() {
        User user = currentUserUtil.currentUser();
        return UserInfoResponse.builderUserInfoResponse(user.getId(), user.getEmail(), user.getName(), user.getIntroduce());
    }

}
