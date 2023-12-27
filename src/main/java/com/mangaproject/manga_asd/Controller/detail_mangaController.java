package com.mangaproject.manga_asd.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Object> addDetails(@RequestBody List<Detail_manga> detail_mangaList) {
    log.info("Añadiendo nuevos detalles de manga");

    List<Detail_manga> savedDetails = new ArrayList<>();

    for (Detail_manga detail_manga : detail_mangaList) {
        savedDetails.add(detaild.save(detail_manga));
    }

    return new ResponseEntity<>(savedDetails, HttpStatus.OK);
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
