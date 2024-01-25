package org.example;


import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    //hago estatico el session por que no me lo pilla
    private static Session session;
    public static void main( String[] args )
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        if (session != null){
            System.out.println("Se inicio sesión");
            System.out.println("==================");

            Scanner scanner = new Scanner(System.in);
            int opcion;

            do{
                System.out.println("===========MENU==========");
                System.out.println("1. Listar todos los libros");
                System.out.println("2. Listar todos los socios");
                System.out.println("3. Realizar una inserción en la tabla libro");
                System.out.println("4. Realizar una insercion en la tabla socio");
                System.out.println("5. Realizar una modificacion de un libro.");
                System.out.println("6. Realizar una modificacion de un socio");
                System.out.println("7. Realizar la eliminación de un libro");
                System.out.println("8. Realizar la eliminacion de un socio");

                opcion = scanner.nextInt();

                switch (opcion){
                    case 1:
                        listarLibros();
                        break;
                    case 2:
                        listarSocios();
                        break;
                    case 3:
                        realizarInsercionLibro();
                        break;
                    case 4:
                        realizarInsercionSocio();
                        break;
                    case 5:
                        realizarModificacionLibro();
                        break;
                    case 6:
                        realizarModificacionSocio();
                        break;
                    case 7:
                        eliminarLibro();
                        break;
                    case 8:
                        eliminarSocio();
                        break;
                    case 9:
                        System.out.println("Saliendo del programa, hasta la proxima!!!!");
                        scanner.close();
                }
            }while (opcion != 9);

            scanner.close();

        }else{
            System.out.println("Error abriendo la sesión");
        }
    }

    private static void listarLibros() {
        LibroClass libroClass = new LibroClass();
        Query q = session.createQuery("from LibroClass");
        List <LibroClass> lista = q.list();
        Iterator <LibroClass> iterator = lista.iterator();

        while (iterator.hasNext()){
            libroClass = (LibroClass) iterator.next();
            System.out.println("==========================");
            System.out.println("ISBN: "+libroClass.getIsbn());
            System.out.println("Titulo: "+libroClass.getTitulo());
            System.out.println("Autor: "+libroClass.getAutor());
            System.out.println("Editorial: "+libroClass.getEditorial());
            System.out.println("==========================");
        }
    }

    private static void listarSocios() {
        SocioClass socioClass = new SocioClass();
        Query q = session.createQuery("from SocioClass ");
        List <SocioClass> lista = q.list();
        Iterator <SocioClass> iterator = lista.iterator();

        while (iterator.hasNext()){
            socioClass = (SocioClass) iterator.next();
            System.out.println("==========================");
            System.out.println("DNI: "+socioClass.getDni());
            System.out.println("Nombre: "+socioClass.getNombre());
            System.out.println("Telefono: "+socioClass.getTfno());
            System.out.println("==========================");
        }
    }

    private static void realizarInsercionLibro() {
        
    }

    private static void realizarInsercionSocio() {
    }

    private static void realizarModificacionLibro() {
    }

    private static void realizarModificacionSocio() {
    }

    private static void eliminarLibro() {
    }

    private static void eliminarSocio() {
    }
}
