package com.mangaproject.manga_asd.Controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mangaproject.manga_asd.Model.Detail_manga;
import com.mangaproject.manga_asd.Repository.detailMangaRepository;
import com.mangaproject.manga_asd.Service.IDetail_mangaService;

import java.util.ArrayList;
import java.util.List;

@Log
@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/details")
public class DetailMangaController {

    @Autowired
    private IDetail_mangaService detailService;

    @Autowired
    private detailMangaRepository detailRepository;

    @PostMapping("/add")
    public ResponseEntity<Object> addDetails(@RequestBody List<Detail_manga> detailList) {
        log.info("Añadiendo nuevos detalles de manga");

        List<Detail_manga> savedDetails = new ArrayList<>();

        for (Detail_manga detail : detailList) {
            savedDetails.add(detailService.save(detail));
        }

        return new ResponseEntity<>(savedDetails, HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Detail_manga> getAllDetails() {
        log.info("Obteniendo todos los detalles del manga");

        List<Detail_manga> details = detailService.findAll();

        details.forEach(detail -> {
            if (detail.getIdUserFK() != null) {
                detail.setIdUserFKName(detail.getIdUserFK().getName());
            }
            if (detail.getIdMangaFK() != null) {
                detail.setIdMangaFKName(detail.getIdMangaFK().getTitle());
            }
        });

        return details;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDetail(@PathVariable Integer id, @RequestBody Detail_manga updatedDetail) {
        Detail_manga existingDetail = detailRepository.findDetailById(id);

        if (existingDetail != null || existingDetail.isRestore() == false) {

                existingDetail.setRestore(updatedDetail.isRestore());    
            
            Detail_manga updatedDetailEntity = detailService.save(existingDetail);

            return new ResponseEntity<>(updatedDetailEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Detalle de manga no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
