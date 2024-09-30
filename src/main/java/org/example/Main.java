package org.example;

import org.example.Data.DatabaseConnection;
import org.example.add_data.Data;
import org.example.controllers.LibroDAO;
import org.example.controllers.PersonaDAO;
import org.example.controllers.PrestamoDAO;
import org.example.controllers.UsuarioDAO;
import org.example.entities.Libro;
import org.example.entities.Persona;
import org.example.entities.Prestamo;
import org.example.entities.Usuario;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseConnection dbconnect = new DatabaseConnection();
        dbconnect.connectDb();
        Data addData = new Data();
        addData.enterData();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los datos para crear la persona:");
        Persona persona = new Persona();

        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine();
        persona.setNombre(nombre);

        System.out.println("Ingrese el apellido:");
        String apellido = scanner.nextLine();
        persona.setApellido(apellido);

        System.out.println("Ingrese el sexo:");
        String sexo = scanner.nextLine();
        persona.setSexo(sexo);

        // Crear instancia del DAO y guardar la persona
        PersonaDAO personaDAO = new PersonaDAO();
        personaDAO.crearPersona(persona);

        // Método para guardar la persona en la base de datos

        System.out.println("Persona guardada exitosamente.");

        ///////

        System.out.println("Ingrese los datos para crear el usuario:");
        Usuario usuario = new Usuario();

        System.out.println("Ingrese el rol:");
        String rol = scanner.nextLine();
        usuario.setRol(rol);

        usuario.setIdPersona(persona);

        // Crear instancia del DAO y guardar el usuario
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.crearUsuario(usuario);

        // Método para guardar el usuario en la base de datos

        System.out.println("Usuario guardado exitosamente.");

        /////////// Crear el libro

        System.out.println("Ingrese los datos para crear el libro:");
        Libro libro = new Libro();

        System.out.println("Ingrese el titulo:");
        String titulo = scanner.nextLine();
        libro.setTitulo(titulo);

        System.out.println("Ingrese el autor del libro:");
        String autor = scanner.nextLine();
        libro.setAutor(autor);

        System.out.println("Ingrese el isbn del libro:");
        String isbn = scanner.nextLine();
        libro.setIsbn(isbn);


        // Crear instancia del DAO y guardar el libro
        LibroDAO libroDAO = new LibroDAO();
        libroDAO.crearLibro(libro);

        // Método para guardar el libro en la base de datos

        System.out.println("Libro guardado exitosamente.");

        // Actualizar libro.////////////////////////////

        System.out.println("Ingrese el Id del libro a actualizar:");
        String libroIdStr = scanner.nextLine();
        int libroId = Integer.parseInt(libroIdStr); // Convertir el ID a int

        // Obtener el libro desde la base de datos
        Libro libroActualizar = libroDAO.obtenerLibroId(libroId);

        if (libroActualizar != null) {
            // Modificar los atributos del libro
            System.out.println("Ingrese el nuevo título (deje en blanco si no desea cambiarlo):");
            String nuevoTitulo = scanner.nextLine();
            if (!nuevoTitulo.isEmpty()) {
                libroActualizar.setTitulo(nuevoTitulo);
            }

            System.out.println("Ingrese el nuevo autor (deje en blanco si no desea cambiarlo):");
            String nuevoAutor = scanner.nextLine();
            if (!nuevoAutor.isEmpty()) {
                libroActualizar.setAutor(nuevoAutor);
            }

            System.out.println("Ingrese el nuevo ISBN (deje en blanco si no desea cambiarlo):");
            String nuevoIsbn = scanner.nextLine();
            if (!nuevoIsbn.isEmpty()) {
                libroActualizar.setIsbn(nuevoIsbn);
            }

            // Guardar los cambios en la base de datos
            libroDAO.actualizarLibro(libroActualizar); // Usa libroActualizar en lugar de libro

            System.out.println("Libro actualizado exitosamente.");
        } else {
            System.out.println("No se encontró el libro con el ID especificado.");
}

        // Eliminar libro.
        System.out.println("Ingrese el ID del libro a eliminar:");
        int libroIdStr2 = scanner.nextInt();

        LibroDAO libroDao2 = new LibroDAO();
        libroDAO.eliminarLibro(libroIdStr2);

        /////////// Crear el prestamo

        System.out.println("Ingrese los datos para el prestamo:");
        Prestamo prestamo = new Prestamo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("Fecha del prestamo:");
        String fechaPrestamo = scanner.nextLine();
        prestamo.setFechaPrestamo(dateFormat.parse(fechaPrestamo));

        System.out.println("Fecha de la devolución:");
        String fechaDevolucion = scanner.nextLine();
        prestamo.setFechaDevolucion(dateFormat.parse(fechaDevolucion));

        prestamo.setIdUsuario(usuario);
        prestamo.setIdLibro(libro);

        // Crear instancia del DAO y guardar el libro
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        prestamoDAO.crearPrestamo(prestamo);

        // Método para guardar el prestamo en la base de datos

        System.out.println("Prestamo guardado exitosamente.");

}
}