package org.mikel.examen_dein_2.modelos;

import java.time.LocalDate;

/**
 * Representa un registro histórico de un préstamo de libro realizado por un Estudiante.
 */
public class Historico {

    private int id;
    private Estudiante estudiante;
    private Libro libro;
    private boolean prestamo_online;
    private LocalDate fecha_prestamo;
    private LocalDate fecha_devolucion;

    /**
     * Constructor con todos los atributos.
     *
     * @param id              Identificador del registro histórico.
     * @param alumno          Alumno que realizó el préstamo.
     * @param libro           Libro prestado.
     * @param prestamo_online Verifica si es un prestamo online.
     * @param fecha_prestamo  Fecha en que se realizó el préstamo.
     * @param fecha_devolucion Fecha en que se devolvió el libro.
     */
    public Historico(int id, Estudiante alumno, Libro libro, Boolean prestamo_online,LocalDate fecha_prestamo, LocalDate fecha_devolucion) {
        this.id = id;
        this.estudiante = alumno;
        this.libro = libro;
        this.prestamo_online=prestamo_online;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
    }

    /**
     * Constructor vacío.
     */
    public Historico() {
    }

    /**
     * Obtiene el identificador del registro histórico.
     *
     * @return ID del registro histórico.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del registro histórico.
     *
     * @param id ID del registro histórico.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el estudiante que realizó el préstamo.
     *
     * @return Alumno que realizó el préstamo.
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * Establece el estudiante que realizó el préstamo.
     *
     * @param estudiante Alumno que realizó el préstamo.
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * Obtiene el libro prestado.
     *
     * @return Libro prestado.
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Establece el libro prestado.
     *
     * @param libro Libro prestado.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene si el prestamo es online.
     *
     * @return prestamo_online.
     */
    public boolean getPrestamo_online() {
        return prestamo_online;
    }

    /**
     * Establece si el prestamo es online.
     *
     * @param prestamo_online Libro prestado.
     */
    public void setPrestamo_online(boolean prestamo_online) {
        this.prestamo_online = prestamo_online;
    }

    /**
     * Obtiene la fecha en que se realizó el préstamo.
     *
     * @return Fecha del préstamo.
     */
    public LocalDate getFecha_prestamo() {
        return fecha_prestamo;
    }

    /**
     * Establece la fecha en que se realizó el préstamo.
     *
     * @param fecha_prestamo Fecha del préstamo.
     */
    public void setFecha_prestamo(LocalDate fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    /**
     * Obtiene la fecha en que se devolvió el libro.
     *
     * @return Fecha de devolución del libro.
     */
    public LocalDate getFecha_devolucion() {
        return fecha_devolucion;
    }

    /**
     * Establece la fecha en que se devolvió el libro.
     *
     * @param fecha_devolucion Fecha de devolución del libro.
     */
    public void setFecha_devolucion(LocalDate fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    /**
     * Devuelve una representación en cadena del registro histórico.
     *
     * @return Cadena con el formato "ID - Alumno: Nombre - Libro: Título".
     */
    @Override
    public String toString() {
        return id + " - Alumno: " + estudiante.getNombre() + " - Libro: " + libro.getTitulo();
    }
}
