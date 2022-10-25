package com.example.comit.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    @NotBlank
    private String email;

    @Pattern(regexp="(?=.*\\W)(?=\\S+$).{8,40}",
            message = "비밀번호에 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 40자의 비밀번호여야 합니다.")
    private String password;


}
