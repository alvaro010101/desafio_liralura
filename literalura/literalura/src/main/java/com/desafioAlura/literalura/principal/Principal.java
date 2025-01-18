package com.desafioAlura.literalura.principal;

import com.desafioAlura.literalura.modelo.*;
import com.desafioAlura.literalura.repository.*;
import com.desafioAlura.literalura.service.*;
import java.util.*;

public class Principal {
    private ConsumoAPI consumoApi = new ConsumoAPI();
    Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private AutorRepository repositorio;
    private LibroRepository libroRepository;
    private List<Libros> libros;
    private List<Autor> autores;

    public Principal(AutorRepository repository, LibroRepository libroRepository) {
        this.repositorio =repository;
        this.libroRepository=libroRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    LITERALURA_CHALLENGE
                    
                    1 - Buscar libro
                    2 - Consultar libros buscados
                    3 - Consultar autores
                    4 - Consultar autores de un año especifico
                    5 - Consultar libros por lenguaje
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    consultarLibros();
                    break;
                case 3:
                    consultarAutores();
                    break;
                case 4:
                    consultarAutoresPorAno();
                    break;
                case 5:
                    consultarLibrosLenguaje();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosLibros getDatosLibros() {
        System.out.println("Escriba el nombre del libro a buscar: ");
        var nombreBuscar =teclado.nextLine().toLowerCase().replace(" ","%20");
        var json = consumoApi.obtenerDatos(URL_BASE +"?search=" + nombreBuscar);
        System.out.println(json);
        DatosLibros datosLibros = conversor.obtenerDatos(json,DatosLibros.class);
        return datosLibros;
    }

    private void buscarLibro() {
        DatosLibros datosLibros = getDatosLibros();
        try{
            Libros libro = new Libros(datosLibros.resultados().get(0));
            Autor autor = new Autor(datosLibros.resultados().get(0).autorList().get(0));


            System.out.println("""
                    libro[
                        titulo: %s
                        author: %s
                        lenguaje: %s
                        descargas: %s
                    ]
                    """.formatted(libro.getTitulo(),
                    libro.getAutor(),
                    libro.getIdioma(),
                    libro.getDescargas().toString()));

            libroRepository.save(libro);
            repositorio.save(autor);
        }catch (Exception e){
            System.out.println("no se encontro ese libro");
        }

    }

    private void consultarLibros() {
        libros = libroRepository.findAll();
        libros.stream().forEach(l -> {
            System.out.println("""    
                        Titulo: %s
                        Author: %s
                        Lenguaje: %s
                        Descargas: %s
                    """.formatted(l.getTitulo(),
                    l.getAutor(),
                    l.getIdioma(),
                    l.getDescargas().toString()));
        });
    }

    private void consultarAutores() {
        autores = repositorio.findAll();
        autores.stream().forEach(a -> {
            System.out.println("""
                        Autor: %s
                        Año de nacimiento: %s
                        Año de defuncion: %s
                    """.formatted(a.getNombre(),
                    a.getAnoNacimiento().toString(),
                    a.getAnoFallecimiento().toString()));
        });
    }

    public void consultarAutoresPorAno()
    {
        System.out.println("Ingresa el año a partir del cual buscar:");
        var anoBusqueda = teclado.nextInt();
        teclado.nextLine();

        List<Autor> authors = repositorio.autorPorFecha(anoBusqueda);
        authors.forEach( a -> {
            System.out.println("""
                    Nombre: %s
                    Fecha de nacimiento: %s
                    Fecha de defuncion: %s
                    """.formatted(a.getNombre(),a.getAnoNacimiento().toString(),a.getAnoFallecimiento().toString()));
        });
    }

    private void consultarLibrosLenguaje()
    {
        System.out.println("""
                ****************************************************************    
                    Selcciona el lenguaje de los libros que deseas consultar
                ****************************************************************
                1 - En (Ingles)
                2 - Es (Español)
                """);

        try {

            var opcion2 = teclado.nextInt();
            teclado.nextLine();

            switch (opcion2) {
                case 1:
                    libros = libroRepository.findByIdioma("en");
                    break;
                case 2:
                    libros = libroRepository.findByIdioma("es");
                    break;

                default:
                    System.out.println("Ingresa una opcion valida");
            }

            libros.stream().forEach(l -> {
                System.out.println("""    
                        Titulo: %s
                        Author: %s
                        Lenguaje: %s
                        Descargas: %s
                    """.formatted(l.getTitulo(),
                        l.getAutor(),
                        l.getIdioma(),
                        l.getDescargas().toString()));
            });

        } catch (Exception e){
            System.out.println("Ingresa un valor valido");
        }
    }




}
