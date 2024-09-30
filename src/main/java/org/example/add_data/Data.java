package org.example.add_data;

import org.example.entities.Libro;
import org.example.entities.Persona;
import org.example.entities.Prestamo;
import org.example.entities.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.List;

public class Data {
    private SessionFactory sessionFactory;
    private boolean dataInit;

    /* Constructor */
    public Data() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /* Declaracion del metodo */

    public void enterData() throws ParseException {
        if (dataInit) {
            System.out.println("Los datos ya han sido creados");
            return;
        }

        Session session = sessionFactory.openSession();

        try{
        long countPersonas = (long) session.createQuery("SELECT COUNT (p.id) FROM Persona p").uniqueResult();
        if (countPersonas>0){
            System.out.println("Los datos ya existen en la base de datos");
            dataInit = true;
            session.close();
            return;
        }

        session.beginTransaction();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        //persona
        Persona persona1 = new Persona();
        persona1.setNombre("Katherine");
        persona1.setApellido("Hurtado");
        persona1.setSexo("femenino");

        Persona persona2 = new Persona();
        persona2.setNombre("Cristian");
        persona2.setApellido("Zapata");
        persona2.setSexo("masculino");

        Persona persona3 = new Persona();
        persona3.setNombre("Diana");
        persona3.setApellido("Lopez");
        persona3.setSexo("femenino");

        Persona persona4 = new Persona();
        persona4.setNombre("Andres");
        persona4.setApellido("Giraldo");
        persona4.setSexo("masculino");

        Persona persona5 = new Persona();
        persona5.setNombre("Eliana");
        persona5.setApellido("Montes");
        persona5.setSexo("femenino");


        //usuario
        Usuario usuario1 = new Usuario();
        usuario1.setIdPersona(persona1);
        usuario1.setRol("Bibliotecario");

        Usuario usuario2 = new Usuario();
        usuario2.setIdPersona(persona2);
        usuario2.setRol("Visitante");

        Usuario usuario3 = new Usuario();
        usuario3.setIdPersona(persona3);
        usuario3.setRol("Bibliotecario");

        Usuario usuario4 = new Usuario();
        usuario4.setIdPersona(persona4);
        usuario4.setRol("Visitante");

        Usuario usuario5 = new Usuario();
        usuario5.setIdPersona(persona5);
        usuario5.setRol("Bibliotecario");


        //libro
        Libro libro1 = new Libro();
        libro1.setAutor("Stephen King");
        libro1.setTitulo("El Instituto");
        libro1.setIsbn("15");

        Libro libro2 = new Libro();
        libro2.setAutor("Jane Austen");
        libro2.setTitulo("Orgullo y prejuicio");
        libro2.setIsbn("20");

        Libro libro3 = new Libro();
        libro3.setAutor("Marcel Proust");
        libro3.setTitulo("En busca del tiempo perdido");
        libro3.setIsbn("17");

        Libro libro4 = new Libro();
        libro4.setAutor("Emily Bronte");
        libro4.setTitulo("Cumbres borrascosas");
        libro4.setIsbn("35");

        Libro libro5 = new Libro();
        libro5.setAutor("Homero");
        libro5.setTitulo("La Odisea");
        libro5.setIsbn("08");


        //prestamo
        Prestamo prestamo1 = new Prestamo();
        prestamo1.setIdUsuario(usuario1);
        prestamo1.setIdLibro(libro1);
        prestamo1.setFechaPrestamo(format.parse("2024-02-09"));
        prestamo1.setFechaDevolucion(format.parse("0000-00-00"));
        prestamo1.setActivo(true);

        Prestamo prestamo2 = new Prestamo();
        prestamo2.setIdUsuario(usuario2);
        prestamo2.setIdLibro(libro2);
        prestamo2.setFechaPrestamo(format.parse("2023-10-12"));
        prestamo2.setFechaDevolucion(format.parse("2024-01-10"));
        prestamo2.setActivo(false);

        Prestamo prestamo3 = new Prestamo();
        prestamo3.setIdUsuario(usuario3);
        prestamo3.setIdLibro(libro3);
        prestamo3.setFechaPrestamo(format.parse("2023-12-20"));
        prestamo3.setFechaDevolucion(format.parse("0000-00-00"));
        prestamo3.setActivo(true);

        Prestamo prestamo4 = new Prestamo();
        prestamo4.setIdUsuario(usuario4);
        prestamo4.setIdLibro(libro4);
        prestamo4.setFechaPrestamo(format.parse("2024-08-09"));
        prestamo4.setFechaDevolucion(format.parse("2024-09-17"));
        prestamo4.setActivo(false);

        Prestamo prestamo5 = new Prestamo();
        prestamo5.setIdUsuario(usuario5);
        prestamo5.setIdLibro(libro5);
        prestamo5.setFechaPrestamo(format.parse("2024-04-13"));
        prestamo5.setFechaDevolucion(format.parse("2024-06-14"));
        prestamo5.setActivo(false);

        List<Object> entities = Arrays.asList(persona1, persona2, persona3, persona4,
                persona5, usuario1, usuario2, usuario3, usuario4, usuario5, libro1, libro2,
                libro3, libro4, libro5, prestamo1, prestamo2, prestamo3, prestamo4, prestamo5);

        for (Object entity : entities) {
            session.persist(entity);
        }


        session.getTransaction().commit();
        dataInit = true;
        System.out.println("Los datos fueron agregados correctamente");
        }catch (HibernateException error){
        System.out.println("El error es "+ error.getMessage());
        }
        finally {
            session.close();
        }
    }

}
