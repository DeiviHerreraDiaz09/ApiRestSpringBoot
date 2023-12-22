package com.mangaproject.manga_asd.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mangaproject.manga_asd.Model.Detail_manga;

public interface detailMangaRepository extends CrudRepository<Detail_manga, Integer>{
    
    @Query("SELECT d FROM Detail_manga d WHERE d.idDetail_ma = :id")
    Detail_manga findDetailById(@Param("id") Integer id);

}
