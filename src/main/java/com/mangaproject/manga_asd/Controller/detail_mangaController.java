package com.mangaproject.manga_asd.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mangaproject.manga_asd.Model.Detail_manga;
import com.mangaproject.manga_asd.Repository.detailMangaRepository;
import com.mangaproject.manga_asd.Service.IDetail_mangaService;
import lombok.extern.java.Log;

@Log
@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/details")
public class detail_mangaController {
    
    @Autowired
    private IDetail_mangaService detaild;

    @Autowired
    private detailMangaRepository detailmangarepository;  


    @PostMapping("/add")
    public Detail_manga addDetails(@RequestBody Detail_manga detail_manga) {
       
            log.info("Añadiendo un nuevo detalle de manga: "+ detail_manga.getFechaAlquiler());
        
        return detaild.save(detail_manga);
    }

    @GetMapping("/list")
    public List<Detail_manga> getAllDetails() {
        log.info("Obteniendo todos los detalles del manga");

        List<Detail_manga> details = detaild.findAll();

        for (Detail_manga detail : details) {
            if (detail.getIdUserFK() != null) {
                detail.setIdUserFKName(detail.getIdUserFK().getName());
            }
            if (detail.getIdMangaFK() != null) {
                detail.setIdMangaFKName(detail.getIdMangaFK().getTitle());
            }
        }
        return details;
    }

    @PutMapping("/{id}")
public ResponseEntity<Object> updateDetail(@PathVariable Integer id, @RequestBody Detail_manga updatedDetail) {
    Detail_manga existingDetail = detailmangarepository.findDetailById(id);

    if (existingDetail != null) {
        existingDetail.setFecha_devolucion(updatedDetail.getFecha_devolucion());

        Detail_manga updatedDetailEntity = detaild.save(existingDetail);

        return new ResponseEntity<>(updatedDetailEntity, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Detalle de manga no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    }
}




}
