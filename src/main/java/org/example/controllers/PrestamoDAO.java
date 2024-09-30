package org.example.controllers;

import org.example.Data.DatabaseConnection;
import org.example.entities.Prestamo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


public class PrestamoDAO {
    private DatabaseConnection databaseConnection;

    public PrestamoDAO() {
        this.databaseConnection = new DatabaseConnection();
    }

    //metodo para guardar datos nuevos
    public void crearPrestamo(Prestamo prestamo) {
        Transaction transaction = null;

        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para obtener el prestamo por id
    public Prestamo obtenerPrestamoId(int id) {
        try (Session session = databaseConnection.getSession()) {
            return session.get(Prestamo.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para obtener todos los prestamos
    public List<Prestamo> obtenerPrestamos() {
        try (Session session = databaseConnection.getSession()) {
            return session.createQuery("FROM prestamo", Prestamo.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo para actualizar un prestamo
    public  void actualizarPrestamo(Prestamo prestamo){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            session.update(prestamo);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Metodo para eliminar un prestamo
    public void eliminarPrestamo(int id){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            Prestamo prestamo = session.get(Prestamo.class, id);
            if (prestamo != null){
                session.delete(prestamo);
                transaction.commit();
            }
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}



