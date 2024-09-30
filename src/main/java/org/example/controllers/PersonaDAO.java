package org.example.controllers;

import org.example.Data.DatabaseConnection;
import org.example.entities.Libro;
import org.example.entities.Persona;
import org.example.entities.Prestamo;
import org.example.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonaDAO {
    private DatabaseConnection databaseConnection;

    public PersonaDAO() {
        this.databaseConnection = new DatabaseConnection();
    }

    //metodo para guardar datos nuevos
    public void crearPersona(Persona persona) {
        Transaction transaction = null;

        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.save(persona);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para obtener el persona por id
    public Persona obtenerPersonaId(int id) {
        try (Session session = databaseConnection.getSession()) {
            return session.get(Persona.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para obtener todos los persona
    public List<Persona> obtenerPersonas() {
        try (Session session = databaseConnection.getSession()) {
            return session.createQuery("FROM persona", Persona.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo para actualizar una persona
    public void actualizarPersona(Persona persona) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.update(persona);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para eliminar una persona
    public void eliminarPersona(int id) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            Persona persona = session.get(Persona.class, id);
            Usuario usuario = session.get(Usuario.class, id);
            Libro libro = session.get(Libro.class, id);
            Prestamo prestamo = session.get(Prestamo.class, id);
            if (persona != null) {
                session.delete(persona);
                session.delete(usuario);
                session.delete(libro);
                session.delete(prestamo);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}