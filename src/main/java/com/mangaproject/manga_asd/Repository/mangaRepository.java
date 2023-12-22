package com.mangaproject.manga_asd.Repository;

import org.springframework.data.repository.CrudRepository;

import com.mangaproject.manga_asd.Model.Manga;

public interface mangaRepository extends CrudRepository<Manga, Integer> {
    
}
