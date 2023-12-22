package com.mangaproject.manga_asd.Service;

import java.util.List;

import com.mangaproject.manga_asd.Model.Detail_manga;


public interface IDetail_mangaService {
    
    Detail_manga save (Detail_manga detail_manga);

    List<Detail_manga> findAll();

    List<Detail_manga> findByUserId(Integer userId);

}
