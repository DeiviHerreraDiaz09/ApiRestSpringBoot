package com.mangaproject.manga_asd.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
    private Long image; 

    @Column(name = "amount", nullable = false)
    private Integer amount; 

    @Column(name = "price", nullable = false)
    private Integer price;

    public Manga(Integer idManga, String description, Long image, Integer amount, Integer price) {
        this.idManga = idManga;
        this.description = description;
        this.image = image;
        this.amount = amount;
        this.price = price;
    }

    public Manga(){}
    
}
