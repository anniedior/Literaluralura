package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository <Libro, Long> {


    Optional<Libro> findById(Long id);


    @Query("SELECT a FROM Libro l JOIN l.autores a")
    List<Autor> buscarAutores();

    @Query("SELECT a FROM Libro l JOIN l.autores a WHERE a.fechaDeFallecimiento >= :anio")
    List<Autor> buscarAutoresvivos(int anio);

    @Query("SELECT DISTINCT l FROM Libro l JOIN l.idiomas i WHERE :idioma IN (i)")
    List<Libro> encontrarLibroporIdioma(String idioma);

}
