package com.mangaproject.manga_asd.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mangaproject.manga_asd.Model.Manga;
import com.mangaproject.manga_asd.Service.IMangaService;
import com.mangaproject.manga_asd.Service.JwtVerificationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log
@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/mangas")
public class mangaController {

    @Autowired
    private IMangaService mangad;

    @Autowired
    private JwtVerificationService JwtD;

    // 2. Que permita registrar mangas (El requisito es que solo el rol
    // "administrador" lo pueda hacer)

    @PostMapping("/add")
    public ResponseEntity<?> addManga(@RequestBody Manga manga, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userRole = JwtD.extractUserRole(token);

        log.info("Trying to add a manga with user role: " + userRole);

        if (userRole != null && userRole.equals("administrador")) {
            log.info("Añadiendo un nuevo manga " + manga.getTitle());
            mangad.save(manga);
            return ResponseEntity.ok("Manga creado exitosamente");
        } else {
            log.warning("Permiso denegado. Valor de userRole: " + userRole);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permiso denegado");
        }
    }

    @GetMapping("/list")
    public List<Manga> getAllUsers() {
        log.info("Obteniendo todos los mangas");
        return mangad.findAll();
    }

}
