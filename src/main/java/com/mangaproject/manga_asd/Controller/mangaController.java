package com.mangaproject.manga_asd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mangaproject.manga_asd.Model.Manga;
import com.mangaproject.manga_asd.Service.IMangaService;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Log
@RestController
@RequestMapping("/api/mangas")
public class mangaController {
    
    @Autowired
    private IMangaService mangad;

    // 2. Que permita registrar mangas (El requisito es que solo el rol "administrador" lo pueda hacer)

    @PostMapping("/add")
    public Manga addManga(@RequestBody Manga manga) {
    
        log.info("Añadiendo un nuevo manga "+ manga.getTitle());
        return mangad.save(manga);
    }
    


}
