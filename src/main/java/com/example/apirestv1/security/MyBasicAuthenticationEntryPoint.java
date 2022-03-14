package com.example.apirestv1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    //METHODS
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //super.commence(request, response, authException);
        response.addHeader("WWW-Authenticate", "Basic realm=\\" + getRealmName() + "\\");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter writer = response.getWriter();
        writer.println("Access not authorized");
    }

    @PostConstruct
    public void initRealmname() {
        setRealmName("series.itb");
    }
}
