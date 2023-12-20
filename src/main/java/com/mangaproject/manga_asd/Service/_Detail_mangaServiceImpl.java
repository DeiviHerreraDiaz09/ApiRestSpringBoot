package com.mangaproject.manga_asd.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangaproject.manga_asd.Model.Detail_manga;
import com.mangaproject.manga_asd.Repository.detailMangaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class _Detail_mangaServiceImpl implements IDetail_mangaService{

    @Autowired
    private detailMangaRepository detailmangarepository;

    
    @Override
    public Detail_manga save(Detail_manga detail_manga) {
        try {
            log.debug("Intentando guardar Detail_manga: {}", detail_manga);
            return detailmangarepository.save(detail_manga);
        } catch (Exception e) {
            log.error("Error al guardar Detail_manga", e);
            throw e; 
        }
    }

    @Override
    public List<Detail_manga> findAll() {
        return (List<Detail_manga>) detailmangarepository.findAll();
    }
    
}
