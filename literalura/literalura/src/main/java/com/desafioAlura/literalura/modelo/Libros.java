package com.desafioAlura.literalura.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String autor;

    private String titulo;

    private String idioma;

    private Integer descargas;

    public Libros(){}

    public Libros (DatosResultados datosLibros){


        this.titulo =datosLibros.titulo();
        this.autor =datosLibros.autorList().get(0).nombreAutor();
        this.idioma=datosLibros.idioma().get(0);
        this.descargas= datosLibros.descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public String getAutor() {
        return autor;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }
}
