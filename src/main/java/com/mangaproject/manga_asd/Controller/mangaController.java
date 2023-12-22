package com.mangaproject.manga_asd.Controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mangaproject.manga_asd.Model.Manga;
import com.mangaproject.manga_asd.Service.IMangaService;
import com.mangaproject.manga_asd.Service.JwtVerificationService;
import com.mangaproject.manga_asd.Service._MangaServiceImpl;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Log
@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/mangas")
public class mangaController {

    @Autowired
    private IMangaService mangad;

    @Autowired
    private _MangaServiceImpl _mangaServiceImpl;

    @Autowired
    private JwtVerificationService JwtD;

    // 2. Que permita registrar mangas (El requisito es que solo el rol
    // "administrador" lo pueda hacer)

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
    String userRole = JwtD.extractUserRole(token);

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
        mangad.save(manga);

        _mangaServiceImpl.saveImage(file);

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

    @GetMapping("/image/{imagen}")
    public ResponseEntity<FileSystemResource> traerImagen(@PathVariable String imagen ){

        String FotoFisica = "C:\\Users\\dherrerad\\Desktop\\ApiRestSpringBoot\\src\\main\\resources\\img\\" + imagen;

        File file = new File(FotoFisica);

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
