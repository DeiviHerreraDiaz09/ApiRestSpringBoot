package com.mangaproject.manga_asd.Repository;

import org.springframework.data.repository.CrudRepository;

import com.mangaproject.manga_asd.Model.Manga;

public interface mangaRepository extends CrudRepository<Manga, Integer> {
    
    // Aplicar una query que trabaje un decremento / incremento en la cantidad de los mangas que hayan 
    
}
