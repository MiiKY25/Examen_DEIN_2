package org.mikel.examen_dein_2.modelos;

import java.time.LocalDate;

/**
 * Representa un préstamo de un libro a un estudiante.
 */
public class Prestamo {

    private int id;
    private Estudiante estudiante;
    private Libro libro;
    private boolean prestamo_online;
    private LocalDate fecha_prestamo;

    /**
     * Constructor con todos los atributos.
     *
     * @param id              Identificador único del préstamo.
     * @param estudiante      Estudiante al que se le presta el libro.
     * @param libro           Libro prestado.
     * @param prestamo_online Verifica si es un prestamo online.
     * @param fecha_prestamo  Fecha en que se realiza el préstamo.
     */
    public Prestamo(int id, Estudiante estudiante, Libro libro,Boolean prestamo_online ,LocalDate fecha_prestamo) {
        this.id = id;
        this.estudiante = estudiante;
        this.libro = libro;
        this.prestamo_online=prestamo_online;
        this.fecha_prestamo = fecha_prestamo;
    }

    /**
     * Constructor vacío.
     */
    public Prestamo() {
    }

    /**
     * Obtiene el identificador del préstamo.
     *
     * @return Identificador del préstamo.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del préstamo.
     *
     * @param id Identificador del préstamo.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el estudiante al que se le ha prestado el libro.
     *
     * @return Estudiante del préstamo.
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * Establece el estudiante al que se le ha prestado el libro.
     *
     * @param estudiante Estudiante del préstamo.
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
     * Establece la fecha del préstamo.
     *
     * @param fecha_prestamo Fecha del préstamo.
     */
    public void setFecha_prestamo(LocalDate fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    /**
     * Devuelve una representación en cadena del préstamo.
     *
     * @return Cadena con el formato "ID - Estudiante: Nombre - Libro: Título".
     */
    @Override
    public String toString() {
        return id + " - Estudiante: " + estudiante.getNombre() + " - Libro: " + libro.getTitulo();
    }
}
