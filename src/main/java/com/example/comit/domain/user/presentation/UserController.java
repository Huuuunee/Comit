package com.example.comit.domain.user.presentation;

import com.example.comit.domain.user.presentation.dto.response.UserInfoResponse;
import com.example.comit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(){
        userService.logOut();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<UserInfoResponse> userInfo(){
        UserInfoResponse userInfoResponse = userService.userInfo();
        return ResponseEntity.ok().body(userInfoResponse);
    }

}
