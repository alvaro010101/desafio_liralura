package com.desafioAlura.literalura.modelo;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreAutor;

    private  Integer anoNacimiento;

    private Integer anoFallecimiento;


    private Autor(){}
    public Autor (DatosAutor datosAutor){
        this.nombreAutor = datosAutor.nombreAutor();
        this.anoNacimiento = datosAutor.anoNacimiento();
        this.anoFallecimiento = datosAutor.anoFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombreAutor;
    }

    public void setNombre(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(Integer anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }


}
