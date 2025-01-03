package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AutorRepositorio extends JpaRepository <Autor, Long>{

    @Query("SELECT a FROM Autor a WHERE a.nombre LIKE %:nombre% AND (:fechaDeNacimiento IS NULL OR a.fechaDeNacimiento = :fechaDeNacimiento) AND a.fechaDeFallecimiento = :fechaDeFallecimiento")
    Autor buscarAutor(@Param("nombre") String nombre, @Param("fechaDeNacimiento") String fechaDeNacimiento, @Param("fechaDeFallecimiento") String fechaDeFallecimiento);
}
