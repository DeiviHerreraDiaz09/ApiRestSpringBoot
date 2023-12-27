package com.mangaproject.manga_asd.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.mangaproject.manga_asd.Model.Manga;
import com.mangaproject.manga_asd.Repository.mangaRepository;
import io.jsonwebtoken.io.IOException;
import lombok.extern.java.Log;


@Log
@Service
public class _MangaServiceImpl implements IMangaService{

    @Autowired
    private mangaRepository mangarepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public Manga save(Manga manga)   {
        return mangarepository.save(manga);
    }

    @Override
    public List<Manga> findAll() {
       return (List<Manga>) mangarepository.findAll();
    }


    public String saveImage(MultipartFile image) throws IOException, java.io.IOException {
        try {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
    
            String fileName = image.getOriginalFilename();
            String filePathString = uploadPath + File.separator + fileName;

            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: "+filePathString);

            File filePath = new File(filePathString);
    
            Files.write(filePath.toPath(), image.getBytes());
    
            return fileName;
        } catch (IOException | java.io.IOException e) {
            log.warning("Error al guardar la imagen: " + e.getMessage());

            return null;
        }
        
    }

    
    
    

    
}
