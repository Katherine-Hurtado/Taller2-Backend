package org.example.controllers;

import org.example.Data.DatabaseConnection;
import org.example.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioDAO {
    private DatabaseConnection databaseConnection;

    public UsuarioDAO() {
        this.databaseConnection = new DatabaseConnection();
    }

    //metodo para guardar datos nuevos
    public void crearUsuario(Usuario usuario) {
        Transaction transaction = null;

        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para obtener el usuario por id
    public Usuario obtenerUsuarioId(int id) {
        try (Session session = databaseConnection.getSession()) {
            return session.get(Usuario.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        try (Session session = databaseConnection.getSession()) {
            return session.createQuery("FROM usuario", Usuario.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo para actualizar un usuario
    public  void actualizarUsuario(Usuario usuario){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Metodo para eliminar un usuario
    public void eliminarUsuario(int id){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, id);
            if (usuario != null){
                session.delete(usuario);
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