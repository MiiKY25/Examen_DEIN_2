package org.mikel.examen_dein_2.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mikel.examen_dein_2.bbdd.ConexionBBDD;
import org.mikel.examen_dein_2.modelos.Estudiante;
import org.mikel.examen_dein_2.modelos.Libro;
import org.mikel.examen_dein_2.modelos.Prestamo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * La clase DaoPrestamo proporciona métodos para gestionar los préstamos de libros en la base de datos.
 * Incluye métodos para obtener todos los préstamos, crear un nuevo préstamo y eliminar un préstamo existente.
 */
public class DaoPrestamo {

    public static ObservableList<Prestamo> todosPrestamos() {
        ConexionBBDD connection;
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();
        try {
            connection = new ConexionBBDD();
            String consulta = "SELECT id_prestamo,dni_estudiante,isbn,prestamo_online,fecha_prestamo FROM Prestamo";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id_prestamo = rs.getInt(1);
                String dni_alumno = rs.getString(2);
                String codigo_libro = rs.getString(3);
                Boolean prestamo_online = rs.getBoolean(4);
                LocalDate fecha_prestamo = rs.getDate(5).toLocalDate();

                Estudiante e= DaoEstudiante.EstudianteID(dni_alumno);
                Libro l= DaoLibro.LibroID(codigo_libro);

                Prestamo p =new Prestamo(id_prestamo,e,l,prestamo_online,fecha_prestamo);
                prestamos.add(p);
            }
            rs.close();
            connection.CloseConexion();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return prestamos;
    }

    /**
     * Obtiene todos los préstamos registrados en la base de datos.
     *
     * @return Una lista observable de objetos {@link Prestamo} que contienen los detalles de los préstamos.
     */
    public static int crearPrestamo(Prestamo p) {
        ConexionBBDD connection;
        int generatedId = -1; // Valor por defecto en caso de error o si no se genera un ID
        try {
            connection = new ConexionBBDD();
            String consulta = "INSERT INTO Prestamo (dni_estudiante,isbn,prestamo_online,fecha_prestamo) VALUES (?, ?, ?,?)";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, p.getEstudiante().getDni());
            pstmt.setInt(2, p.getLibro().getIsbn());
            pstmt.setBoolean(3, p.getPrestamo_online());
            pstmt.setDate(4, Date.valueOf(p.getFecha_prestamo()));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Obtener el ID generado
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);  // El primer campo es el ID generado
                }
                rs.close();
            }

            pstmt.close();
            connection.CloseConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;  // Devuelve el ID generado o -1 si no se generó ningún ID
    }

    /**
     * Elimina un préstamo de la base de datos.
     *
     * @param p El objeto {@link Prestamo} que se desea eliminar, identificado por su ID.
     * @return {@code true} si la eliminación fue exitosa, {@code false} si ocurrió un error.
     */
    public static boolean eliminarPrestamo(Prestamo p) {
        ConexionBBDD connection;
        int resul = 0;
        try {
            connection = new ConexionBBDD();
            String consulta = "DELETE FROM Prestamo WHERE id_prestamo = ?";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setInt(1, p.getId());

            resul = pstmt.executeUpdate();
            pstmt.close();
            connection.CloseConexion();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resul > 0;
    }
}
