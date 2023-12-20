package com.mangaproject.manga_asd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mangaproject.manga_asd.Model.Detail_manga;
import com.mangaproject.manga_asd.Service.IDetail_mangaService;


import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/api/details")
public class detail_mangaController {
    
    @Autowired
    private IDetail_mangaService detaild;

    @PostMapping("/add")
    public Detail_manga addDetails(@RequestBody Detail_manga detail_manga) {
        
        log.info("Añadiendo un nuevo detalle de manga: "+ detail_manga.getFechaAlquiler());

        return detaild.save(detail_manga);
    }

}