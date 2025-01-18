package com.desafioAlura.literalura.repository;

import com.desafioAlura.literalura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long>
{
    @Query("SELECT a FROM Autor a WHERE a.anoNacimiento >= :anoBusqueda ORDER BY a.anoNacimiento ASC ")
    List<Autor> autorPorFecha(int anoBusqueda);

}
