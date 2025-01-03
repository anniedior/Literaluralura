package com.alura.literatura.service;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.DatosAutor;
import com.alura.literatura.model.DatosLibros;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.AutorRepositorio;
import com.alura.literatura.repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private LibroRepositorio libroRepositorio;

    public void guardarLibro(DatosLibros d) {
        Optional<Libro> libroBuscado = libroRepositorio.findById(d.id());
        if (!libroBuscado.isPresent()) {
            List<Autor> autores = agregarAutores(d.autor());
            Libro nuevoLibro = new Libro(d.id(), d.titulo(), d.idiomas(), d.numeroDeDescargas());
            for (Autor autor : autores) {
                if (autor.getId() == null) {
                    nuevoLibro.addAuthor(autor);
                } else {
                    nuevoLibro.addAuthor(autor);
                }
            }
            libroRepositorio.save(nuevoLibro);
            System.out.println(nuevoLibro.toString());
        } else {
            System.out.println("No se registra el mismo libro más de una vez");
        }
    }

    public List<Autor> agregarAutores(List<DatosAutor> datosAutor) {
        List<Autor> autores = new ArrayList<>();
        for (DatosAutor a : datosAutor) {
            Autor autor = autorRepositorio.buscarAutor(a.nombre(), a.fechaDeNacimiento(), a.fechaDeFallecimiento());
            if (autor == null) {
                autores.add(new Autor(a.nombre(), a.fechaDeNacimiento(), a.fechaDeFallecimiento()));
            } else {
                autores.add(autor);
            }
        }
        return autores;
    }

    public void listarLibros() {
        List<Libro> libros = libroRepositorio.findAll();
        libros.stream().forEach(System.out::println);
    }

    public void listarAutores() {
        List<Autor> authors = libroRepositorio.buscarAutores();
        authors.forEach(a -> System.out.println(a.toString()));
    }

    public void listarAutoresVivos(int anio) {
        List<Autor> autores = libroRepositorio.buscarAutoresvivos(anio);
        autores.stream().forEach(System.out::println);
    }

    public void listarPorIdioma(String idioma) {
        List<Libro> libros = libroRepositorio.encontrarLibroporIdioma(idioma);
        libros.stream().forEach(System.out::println);
    }
}
