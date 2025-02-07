package org.mikel.examen_dein_2.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mikel.examen_dein_2.bbdd.ConexionBBDD;
import org.mikel.examen_dein_2.modelos.Estudiante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Clase DaoEstudiante para gestionar el acceso a los datos de los Estudiantes.
 */
public class DaoEstudiante {


    /**
     * LOGGER para registrar eventos y errores.
     */
    private static final Logger LOGGER = Logger.getLogger(DaoEstudiante.class.getName());

    /**
     * Obtiene todos los Estudiantes de la base de datos.
     * @return Lista de Estudiantes en un ObservableList.
     */
    public static ObservableList<Estudiante> todosEstudiantes() {
        ConexionBBDD connection;
        ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
        try {
            connection = new ConexionBBDD();
            String consulta = "SELECT dni_estudiante,nombre,primerApellido,segundApellido FROM Estudiante";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String dni = rs.getString(1);
                String nombre = rs.getString(2);
                String apellido1 = rs.getString(3);
                String apellido2 = rs.getString(4);
                Estudiante a =new Estudiante(dni,nombre,apellido1,apellido2);
                estudiantes.add(a);
                System.out.println("Cargando Estudiante: " + dni + ", " + nombre + ", " + apellido1 + ", " + apellido2);
            }
            rs.close();
            connection.CloseConexion();

            // Log de éxito
            LOGGER.info("Lista de estudiantes cargada correctamente.");
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener la lista de estudiantes: " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return estudiantes;
    }

    /**
     * Obtiene un Estudiante por su DNI.
     * @param dni_estudiante DNI del Estudiante a buscar.
     * @return Estudiante encontrado o null si no existe.
     */
    public static Estudiante EstudianteID(String dni_estudiante) {
        ConexionBBDD connection;
        Estudiante estudiante = null;
        try {
            connection = new ConexionBBDD();
            String consulta = "SELECT dni_estudiante,nombre,primerApellido,segundApellido FROM Estudiante WHERE dni_estudiante = ?";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setString(1, dni_estudiante);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String dni = rs.getString(1);
                String nombre = rs.getString(2);
                String apellido1 = rs.getString(3);
                String apellido2 = rs.getString(4);
                estudiante = new Estudiante(dni,nombre,apellido1,apellido2);
            }
            rs.close();
            connection.CloseConexion();
            // Log de éxito
            LOGGER.info("Estudiante encontrado con DNI: " + dni_estudiante);
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener el Estudiante con DNI " + dni_estudiante + ": " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return estudiante;
    }

    /**
     * Crea un nuevo Estudiante en la base de datos.
     * @param a Estudiante a insertar.
     * @return true si se insertó correctamente, false en caso contrario.
     */
    public static boolean crearEstudiante(Estudiante a) {
        ConexionBBDD connection;
        int resul = 0;
        try {
            connection = new ConexionBBDD();
            String consulta = "INSERT INTO Estudiante (dni_estudiante,nombre,primerApellido,segundApellido) VALUES (?,?,?,?) ";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setString(1, a.getDni());
            pstmt.setString(2, a.getNombre());
            pstmt.setString(3, a.getApellido1());
            pstmt.setString(4, a.getApellido2());

            resul = pstmt.executeUpdate();
            pstmt.close();
            connection.CloseConexion();
            LOGGER.info("Estudiante insertado correctamente: " + a.getDni());
        } catch (SQLException e) {
            LOGGER.severe("Error al insertar el Estudiante " + a.getDni() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return resul > 0;
    }

    /**
     * Modifica los datos de un Estudiante en la base de datos.
     * @param a Estudiante con los datos actualizados.
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    public static boolean editarEstudiante(Estudiante a) {
        ConexionBBDD connection;
        int resul = 0;
        try {
            connection = new ConexionBBDD();
            String consulta = "UPDATE Estudiante SET nombre = ?,primerApellido = ?,segundApellido = ? WHERE dni_estudiante = ?";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setString(1, a.getNombre());
            pstmt.setString(2, a.getApellido1());
            pstmt.setString(3, a.getApellido2());
            pstmt.setString(4, a.getDni());

            resul = pstmt.executeUpdate();
            pstmt.close();
            connection.CloseConexion();
            LOGGER.info("Estudiante actualizado correctamente: " + a.getDni());
        } catch (SQLException e) {
            LOGGER.severe("Error al actualizar el Estudiante " + a.getDni() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return resul > 0;
    }

    /**
     * Verifica si un DNI ya existe en la base de datos.
     * @param dni DNI a comprobar.
     * @return true si existe, false si no.
     */
    public static boolean existeDNI(String dni) {
        ConexionBBDD connection;
        boolean existe = false;
        try {
            connection = new ConexionBBDD();
            String consulta = "SELECT COUNT(*) FROM Estudiante WHERE dni_estudiante = ?";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
            rs.close();
            connection.CloseConexion();
            LOGGER.info("Estudiante con DNI " + dni + " existe:");
        } catch (SQLException e) {
            LOGGER.severe("Error al verificar existencia del Estudiante: " + e.getMessage());
            e.printStackTrace();
        }
        return existe;
    }
}
