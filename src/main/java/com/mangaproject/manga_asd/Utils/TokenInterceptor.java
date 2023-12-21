package com.mangaproject.manga_asd.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mangaproject.manga_asd.Service.JwtVerificationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    
 @Autowired
    private JwtVerificationService jwtVerificationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        String token = request.getHeader("Authorization");


        ResponseEntity<?> verificationResponse = jwtVerificationService.verifyToken(token);

        if (verificationResponse.getStatusCode() == HttpStatus.OK) {
     
            return true;
        } else {
     
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token no válido");
            return false;
        }
    }

}
