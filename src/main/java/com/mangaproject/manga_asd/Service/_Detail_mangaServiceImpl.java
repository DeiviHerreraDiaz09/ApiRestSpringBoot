package com.mangaproject.manga_asd.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangaproject.manga_asd.Model.Detail_manga;
import com.mangaproject.manga_asd.Repository.detailMangaRepository;

@Service
public class _Detail_mangaServiceImpl implements IDetail_mangaService{

    @Autowired
    private detailMangaRepository detailmangarepository;

    
    @Override
    public Detail_manga save(Detail_manga detail_manga) {
        return detailmangarepository.save(detail_manga);
    }

    @Override
    public List<Detail_manga> findAll() {
        return (List<Detail_manga>) detailmangarepository.findAll();
    }
    
}
