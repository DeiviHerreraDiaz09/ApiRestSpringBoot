package com.mangaproject.manga_asd.Controller;

import com.mangaproject.manga_asd.Model.Manga;
import com.mangaproject.manga_asd.Service.IMangaService;
import com.mangaproject.manga_asd.Service.JwtVerificationService;
import com.mangaproject.manga_asd.Service._MangaServiceImpl;
import io.jsonwebtoken.io.IOException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Log
@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/mangas")
public class MangaController {

    @Autowired
    private IMangaService mangaService;

    @Autowired
    private _MangaServiceImpl mangaServiceImpl;

    @Autowired
    private JwtVerificationService jwtService;

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity<?> addManga(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("amount") Integer amount,
            @RequestParam("price") Integer price,
            HttpServletRequest request
    ) throws IOException, java.io.IOException {
        String token = request.getHeader("Authorization");
        String userRole = jwtService.extractUserRole(token);

        log.info("Nombre del archivo: " + file.getOriginalFilename());
        log.info("Rol: " + userRole);

        if (userRole != null && userRole.equals("administrador")) {
            Manga manga = new Manga();
            manga.setTitle(title);
            manga.setDescription(description);
            manga.setAmount(amount);
            manga.setPrice(price);
            manga.setImage(file.getOriginalFilename());

            log.info("Añadiendo un nuevo manga " + manga.getTitle());
            mangaService.save(manga);

            mangaServiceImpl.saveImage(file);

            return ResponseEntity.ok("Manga creado exitosamente");
        } else {
            log.warning("Permiso denegado. Valor de userRole: " + userRole);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permiso denegado");
        }
    }

    @GetMapping("/list")
    public List<Manga> getAllMangas() {
        log.info("Obteniendo todos los mangas");
        return mangaService.findAll();
    }

    @GetMapping("/image/{imagen}")
    public ResponseEntity<FileSystemResource> getImage(@PathVariable String imagen) {
        String photoPath = "src\\main\\resources\\img\\" + imagen;
        File file = new File(photoPath);
        
        log.info(file.getPath());

        if (file.exists()) {
            log.info("Existe");
            return ResponseEntity.ok(new FileSystemResource(file));
        } else {
            log.info("No Existe");
            return ResponseEntity.notFound().build();
        }
    }
}
