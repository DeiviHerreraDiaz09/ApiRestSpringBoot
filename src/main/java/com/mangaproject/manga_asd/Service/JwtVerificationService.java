package com.mangaproject.manga_asd.Service;

import org.springframework.http.ResponseEntity;

public interface JwtVerificationService {

    ResponseEntity<?> verifyToken(String jwtToken);

    String extractUserRole(String jwtToken);

    
}
