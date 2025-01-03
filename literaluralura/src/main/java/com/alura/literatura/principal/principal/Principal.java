package com.alura.literatura.principal.principal;

import com.alura.literatura.model.Datos;
import com.alura.literatura.model.DatosLibros;

import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConviereDatos;
import com.alura.literatura.service.LibroServicio;

import java.util.List;
import java.util.Scanner;
public class Principal {

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConviereDatos conversor = new ConviereDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroServicio libroServicio;

    public Principal(LibroServicio servicio) {
        this.libroServicio = servicio;
    }

    public void ejecutable(){

        var opcion = -1;
        while(opcion != 0) {
            var menu = """
                    ------------------------------------
                    1 - Buscar libro por titulo.
                    2 - Listar libros registrados.
                    3 - Listar autores registrados.
                    4 - Listar autores vivos en determinado año.
                    5 - Listar libros por lenguaje.
                    
                    0 - Salir.
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch(opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosPorAnio();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        }
    }

    public void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        String titulo = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(titulo);
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        List<DatosLibros> resultados = datos.resultados();
        if (resultados.isEmpty()) {
            System.out.println("Libro no encontrado");
        } else {
            libroServicio.guardarLibro(resultados.get(0));
        }
    }

    private void mostrarLibrosBuscados() {
        libroServicio.listarLibros();
    }

    private void mostrarAutoresRegistrados() {
        libroServicio.listarAutores();
    }

    private void mostrarAutoresVivosPorAnio() {
        System.out.println("Ingresa el año que desea consultar");
        int anio = Integer.parseInt(teclado.nextLine());
        libroServicio.listarAutoresVivos(anio);
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("Ingrese las dos letras del idioma del libro que desea buscar. \n");
        String idioma = teclado.nextLine();
        libroServicio.listarPorIdioma(idioma);
    }

}