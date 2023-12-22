package com.mangaproject.manga_asd.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mangaproject.manga_asd.Model.Manga;
import com.mangaproject.manga_asd.Repository.mangaRepository;

@Service
public class _MangaServiceImpl implements IMangaService{

    @Autowired
    private mangaRepository mangarepository;

    @Override
    public Manga save(Manga manga) {
        return mangarepository.save(manga);
    }

    @Override
    public List<Manga> findAll() {
       return (List<Manga>) mangarepository.findAll();
    }
    
}
