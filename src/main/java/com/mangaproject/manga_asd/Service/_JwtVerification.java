package com.mangaproject.manga_asd.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mangaproject.manga_asd.Utils.JwtUtil;

import io.jsonwebtoken.Jws;

@Service
public class _JwtVerification implements JwtVerificationService{

    private final JwtUtil jwtUtil;

    public _JwtVerification(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    public ResponseEntity<?> verifyToken(String jwtToken) {
        if (jwtToken == null || !jwtToken.startsWith("Bearer ")) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Token no proporcionado en la cabecera Authorization");
            return ResponseEntity.badRequest().body(response);
        }

        String token = jwtToken.substring(7);

        Jws<?> claims = jwtUtil.validateToken(token);

        if (claims != null) {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Token válido");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Token no válido");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
}
