package com.mangaproject.manga_asd.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "manga")
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idManga;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private String image; 

    @Column(name = "amount", nullable = false)
    private Integer amount; 

    @Column(name = "price", nullable = false)
    private Integer price;


    public Manga(Integer idManga, String description, String image, Integer amount, Integer price) {
        this.idManga = idManga;
        this.description = description;
        this.image = image;
        this.amount = amount;
        this.price = price;
    }

    public Manga(){}
    
}
