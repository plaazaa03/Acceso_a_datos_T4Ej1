package org.example;


import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App 
{
    private static Session session;
    public static void main( String[] args )
    {
        //Iniciar Hibernate
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
                System.out.println("5. Realizar una modificacion de un libro");
                System.out.println("6. Realizar una modificacion de un socio");
                System.out.println("7. Realizar la eliminación de un libro");
                System.out.println("8. Realizar la eliminacion de un socio");
                System.out.println("9. Salir del programa");

                System.out.println("Opcion: ");
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

    //Metodo de listar libros
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

    //Metodo listar socios
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

    //Metodo para meter mas libeos
    private static void realizarInsercionLibro() {
        Transaction transaction = session.beginTransaction();
        LibroClass nuevoLibro = new LibroClass();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el ISBN: ");
        String isbnInput = scanner.nextLine();

        System.out.println("Introduzca el titulo: ");
        String tituloInput = scanner.nextLine();

        System.out.println("Introduzca el Autor: ");
        String autorInput = scanner.nextLine();

        System.out.println("Introduza la editorial: ");
        String editorialInput = scanner.nextLine();

        nuevoLibro.setIsbn(isbnInput);
        nuevoLibro.setAutor(autorInput);
        nuevoLibro.setTitulo(tituloInput);
        nuevoLibro.setEditorial(editorialInput);

        session.save(nuevoLibro);
        transaction.commit();

    }

    //Metodo para meter mas socios
    private static void realizarInsercionSocio() {
        Transaction transaction = session.beginTransaction();
        SocioClass socioClass = new SocioClass();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el DNI: ");
        String dniInput = scanner.nextLine();

        System.out.println("Introduzca el nombre: ");
        String nombreInput = scanner.nextLine();

        System.out.println("Introduzca el autor: ");
        String telefonoInput = scanner.nextLine();

        socioClass.setDni(dniInput);
        socioClass.setNombre(nombreInput);
        socioClass.setTfno(telefonoInput);


        session.save(socioClass);
        transaction.commit();
    }

    //metodo para modificar algun libro (debe de pedir el isbn del libro que queremos modificar)
    private static void realizarModificacionLibro() {
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indroduzca el ISBN del libro que quiere modificar: ");
        String isbnMod = scanner.nextLine();

        LibroClass libroClass = session.get(LibroClass.class,isbnMod);
        System.out.println("Introduzca el nuevo titulo: ");
        String tituloMod = scanner.nextLine();
        libroClass.setTitulo(tituloMod);

        System.out.println("Introduzca el nuevo autor: ");
        String autorMod = scanner.nextLine();
        libroClass.setAutor(autorMod);

        session.save(libroClass);
        transaction.commit();



    }

    //metodo para modificar un socio (debemos de indicar el dni del socio que queremos modificar)
    private static void realizarModificacionSocio() {
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el dni del socio que quiere modificar: ");
        String dniMod = scanner.nextLine();

        SocioClass socioClass = session.get(SocioClass.class, dniMod);
        System.out.println("Introduzca el nuevo nombre: ");
        String nombreMod = scanner.nextLine();
        socioClass.setNombre(nombreMod);

        System.out.println("Introduzca el nuevo telefono: ");
        String telefonoMod = scanner.nextLine();
        socioClass.setTfno(telefonoMod);

        session.save(socioClass);
        transaction.commit();


    }

    //Eliminar un libo de la base de datos (Nos pedira que incertemos el isbn del libro que queremos borrar)
    private static void eliminarLibro() {
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el ISBN del libro que quiere borrar: ");
        String isbnDel = scanner.nextLine();

        LibroClass libroClass = session.get(LibroClass.class, isbnDel);
        libroClass.setIsbn(isbnDel);

        session.delete(libroClass);
        transaction.commit();

    }

    //Eliminar un socio de la base de datos (Nos pedira que insertemos el dni del socio que queremos borrar)
    private static void eliminarSocio() {
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el DNI del socio que quiere borrar: ");
        String dniDel = scanner.nextLine();

        SocioClass socioClass = session.get(SocioClass.class, dniDel);
        socioClass.setDni(dniDel);

        session.delete(socioClass);
        transaction.commit();

    }
}
