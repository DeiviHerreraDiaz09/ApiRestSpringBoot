package com.mangaproject.manga_asd.Model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "detail_manga")
public class Detail_manga {

    @Transient
    private String idUserFKName;

    @Transient
    private String idMangaFKName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetail_ma;

    @Column(name = "fechaAlquiler", nullable = false)
    private Date fechaAlquiler;

    @Column(name = "fechaLimite", nullable = false)
    private Date fechaLimite;

    @Column(name = "restore", nullable = false)
    private boolean restore;

    @ManyToOne
    @JoinColumn(name = "idUserFK", nullable = false)
    private User idUserFK;

    @ManyToOne
    @JoinColumn(name = "id_mangafk", nullable = false)
    private Manga idMangaFK;



    public Detail_manga(String idUserFKName, String idMangaFKName, Integer idDetail_ma, Date fechaAlquiler,
            Date fechaLimite, boolean restore, User idUserFK, Manga idMangaFK) {
        this.idUserFKName = idUserFKName;
        this.idMangaFKName = idMangaFKName;
        this.idDetail_ma = idDetail_ma;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaLimite = fechaLimite;
        this.restore = restore;
        this.idUserFK = idUserFK;
        this.idMangaFK = idMangaFK;
    }

    public Detail_manga(){}
    
    // Métodos especificos

    public void setUserFK(User user) {
        this.idUserFK = user;
    }

    public String getIdUserFKName() {
        return idUserFKName;
    }

    public void setIdUserFKName(String idUserFKName) {
        this.idUserFKName = idUserFKName;
    }

    public String getIdMangaFKName() {
        return idMangaFKName;
    }

    public void setIdMangaFKName(String idMangaFKName) {
        this.idMangaFKName = idMangaFKName;
    }

    

}
