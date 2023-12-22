package com.mangaproject.manga_asd.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mangaproject.manga_asd.Model.Manga;

import io.jsonwebtoken.io.IOException;

public interface IMangaService {
    
    Manga save(Manga manga);
    
    List<Manga> findAll();

    String saveImage(MultipartFile imagen) throws IOException, java.io.IOException;


}
