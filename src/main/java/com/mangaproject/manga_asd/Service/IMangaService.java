package com.mangaproject.manga_asd.Service;

import java.util.List;

import com.mangaproject.manga_asd.Model.Manga;

public interface IMangaService {
    
    Manga save(Manga manga);
    
    List<Manga> findAll();

}
