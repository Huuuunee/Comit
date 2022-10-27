package com.example.comit.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

    private Long id;

    private String email;

    private String name;

    private String introduce;

    public static UserInfoResponse builderUserInfoResponse(Long id ,String email , String name , String introduce){
        return UserInfoResponse.builder()
                .id(id)
                .email(email)
                .name(name)
                .introduce(introduce)
                .build();
    }

}