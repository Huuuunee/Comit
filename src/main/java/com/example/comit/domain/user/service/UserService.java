package com.example.comit.domain.user.service;

import com.example.comit.domain.user.presentation.dto.response.UserInfoResponse;

public interface UserService {
    void logOut();
    UserInfoResponse userInfo();
}
