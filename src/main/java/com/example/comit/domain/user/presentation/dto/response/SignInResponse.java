package com.example.comit.domain.user.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {

    private String accessToken;

    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime expiredAt;

    public static SignInResponse builderSignInResponse(String accessToken, String refreshToken, ZonedDateTime expiredAt){
        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(expiredAt)
                .build();
    }

}
