package com.example.comit.global.filter;

import com.example.comit.global.error.exception.TokenNotValidException;
import com.example.comit.global.security.jwt.TokenProvider;
import com.example.comit.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("Authorization");
        if(accessToken != null) {
            tokenProvider.extractAllClaims(accessToken, jwtProperties.getAccessSecret());
            System.out.print(tokenProvider.getTokenType(accessToken,jwtProperties.getAccessSecret()));
            if (!tokenProvider.getTokenType(accessToken, jwtProperties.getAccessSecret()).equals("ACCESS_TOKEN"))
                throw new TokenNotValidException("Token is not valid");
            String email = tokenProvider.getUserId(accessToken, jwtProperties.getAccessSecret());
            registerSecurityContext(request, email);
        }
        filterChain.doFilter(request, response);
    }

    private void registerSecurityContext(HttpServletRequest request, String email) {
        UsernamePasswordAuthenticationToken authenticationToken = tokenProvider.authentication(email);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}