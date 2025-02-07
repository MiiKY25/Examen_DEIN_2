package org.mikel.examen_dein_2.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mikel.examen_dein_2.bbdd.ConexionBBDD;
import org.mikel.examen_dein_2.modelos.Libro;
import java.sql.*;

/**
 * Clase DAO para gestionar las operaciones relacionadas con los libros en la base de datos.
 */
public class DaoLibro {

    /**
     * Obtiene una lista de todos los libros almacenados en la base de datos.
     *
     * @return Lista observable de libros.
     */
    public static ObservableList<Libro> todosLibros() {
        ConexionBBDD connection;
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        try {
            connection = new ConexionBBDD();
            String consulta = "SELECT isbn,titulo,autor,editorial,estado,baja FROM Libro";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int isbn = rs.getInt(1);
                String titulo = rs.getString(2);
                String autor = rs.getString(3);
                String editorial = rs.getString(4);
                String estado = rs.getString(5);
                int baja = rs.getInt(6);
                Libro l=new Libro(isbn,titulo,autor,editorial,estado,baja);
                libros.add(l);
            }
            rs.close();
            connection.CloseConexion();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return libros;
    }

    /**
     * Busca un libro en la base de datos por su código.
     *
     * @param isbn Código del libro a buscar.
     * @return Objeto Libro si se encuentra, null en caso contrario.
     */
    public static Libro LibroID(String isbn) {
        ConexionBBDD connection;
        Libro libro = null;
        try {
            connection = new ConexionBBDD();
            String consulta = "SELECT isbn,titulo,autor,editorial,estado,baja FROM Libro WHERE isbn = ?";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setString(1, isbn);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int isbn_libro = rs.getInt(1);
                String titulo = rs.getString(2);
                String autor = rs.getString(3);
                String editorial = rs.getString(4);
                String estado = rs.getString(5);
                int baja = rs.getInt(6);
                libro = new Libro(isbn_libro,titulo,autor,editorial,estado,baja);
            }
            rs.close();
            connection.CloseConexion();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return libro;
    }

    /**
     * Inserta un nuevo libro en la base de datos.
     *
     * @param l Objeto Libro a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public static boolean crearLibro(Libro l) {
        ConexionBBDD connection;
        int resul = 0;
        try {
            connection = new ConexionBBDD();
            String consulta = "INSERT INTO Libro (titulo,autor,editorial,estado,baja) VALUES (?,?,?,?,?) ";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setString(1, l.getTitulo());
            pstmt.setString(2, l.getAutor());
            pstmt.setString(3, l.getEditorial());
            pstmt.setString(4, l.getEstado());
            pstmt.setInt(5, l.getBaja());

            resul = pstmt.executeUpdate();
            pstmt.close();
            connection.CloseConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resul > 0;
    }

    /**
     * Edita la información de un libro en la base de datos.
     *
     * @param l Objeto Libro con los nuevos datos.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public static boolean editarLibro(Libro l) {
        ConexionBBDD connection;
        int resul = 0;
        try {
            connection = new ConexionBBDD();
            String consulta = "UPDATE Libro SET titulo = ?,autor = ?,editorial = ?,estado = ?,baja = ? WHERE isbn = ?";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setString(1, l.getTitulo());
            pstmt.setString(2, l.getAutor());
            pstmt.setString(3, l.getEditorial());
            pstmt.setString(4, l.getEstado());
            pstmt.setInt(5, l.getBaja());
            pstmt.setInt(6, l.getIsbn());

            resul = pstmt.executeUpdate();
            pstmt.close();
            connection.CloseConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resul > 0;
    }

    /**
     * Marca un libro como dado de baja en la base de datos.
     *
     * @param isbn Código del libro a dar de baja.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public static boolean darDeBajaLibro(int isbn) {
        ConexionBBDD connection;
        int resul = 0;
        try {
            connection = new ConexionBBDD();
            // Consulta para actualizar el estado del libro a 'baja = 1'
            String consulta = "UPDATE Libro SET baja = 1 WHERE isbn = ?";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setInt(1, isbn);

            resul = pstmt.executeUpdate();
            pstmt.close();
            connection.CloseConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resul > 0;
    }

    /**
     * Obtiene una lista de todos los libros que están activos (no dados de baja).
     *
     * @return Lista observable de libros activos.
     */
    public static ObservableList<Libro> todosLibrosActivos() {
        ConexionBBDD connection;
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        try {
            connection = new ConexionBBDD();
            String consulta = "SELECT isbn,titulo,autor,editorial,estado,baja FROM Libro WHERE baja=0";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int isbn = rs.getInt(1);
                String titulo = rs.getString(2);
                String autor = rs.getString(3);
                String editorial = rs.getString(4);
                String estado = rs.getString(5);
                int baja = rs.getInt(6);
                Libro l=new Libro(isbn,titulo,autor,editorial,estado,baja);
                libros.add(l);
            }
            rs.close();
            connection.CloseConexion();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return libros;
    }

    /**
     * Obtiene una lista de libros disponibles para ser prestados.
     * Un libro es considerado disponible si no está dado de baja y no está en préstamo.
     *
     * @return Lista observable de libros disponibles para préstamo.
     */
    public static ObservableList<Libro> todosLibrosParaPrestar() {
        ConexionBBDD connection;
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        try {
            connection = new ConexionBBDD();
            String consulta = """
            SELECT l.isbn, l.titulo, l.autor, l.editorial, l.estado, l.baja
            FROM Libro l
            LEFT JOIN Prestamo p ON l.isbn = p.isbn
            WHERE l.baja = 0 AND p.id_prestamo IS NULL
        """;
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int isbn = rs.getInt(1);
                String titulo = rs.getString(2);
                String autor = rs.getString(3);
                String editorial = rs.getString(4);
                String estado = rs.getString(5);
                int baja = rs.getInt(6);
                Libro l = new Libro(isbn, titulo, autor, editorial, estado, baja);
                libros.add(l);
            }
            rs.close();
            pstmt.close();
            connection.CloseConexion();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return libros;
    }
}
