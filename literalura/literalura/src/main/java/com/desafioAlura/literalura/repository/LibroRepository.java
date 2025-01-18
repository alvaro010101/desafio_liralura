package com.desafioAlura.literalura.repository;

import com.desafioAlura.literalura.modelo.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libros,Long> {
    @Query("SELECT l FROM Libros l WHERE l.idioma ILIKE %:lenguaje%")
    List<Libros> findByIdioma(String lenguaje);
}
