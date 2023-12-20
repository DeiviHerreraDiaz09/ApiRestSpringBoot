package com.mangaproject.manga_asd.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "fechaDevolucion", nullable = false)
    private Date fechaDevolucion;

    @ManyToOne
    @JoinColumn(name = "idUserFK", nullable = false)
    private User idUserFK;

    @ManyToOne
    @JoinColumn(name = "idMangaFK", nullable = false)
    private Manga idMangaFK;

    public Detail_manga(Integer idDetail_ma, Date fechaAlquiler, Date fechaDevolucion, User idUserFK, Manga idMangaFK) {
        this.idDetail_ma = idDetail_ma;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
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
