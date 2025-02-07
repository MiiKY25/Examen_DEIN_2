package org.mikel.examen_dein_2.modelos;

/**
 * Representa un Estudiante con su información personal básica.
 */
public class Estudiante {

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;

    /**
     * Constructor con todos los atributos.
     *
     * @param dni       Documento Nacional de Identidad del Estudiante.
     * @param nombre    Nombre del Estudiante.
     * @param apellido1 Primer apellido del Estudiante.
     * @param apellido2 Segundo apellido del Estudiante.
     */
    public Estudiante(String dni, String nombre, String apellido1, String apellido2) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    /**
     * Constructor vacío.
     */
    public Estudiante() {
    }

    /**
     * Obtiene el DNI del Estudiante.
     *
     * @return DNI del Estudiante.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del Estudiante.
     *
     * @param dni DNI del Estudiante.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre del Estudiante.
     *
     * @return Nombre del Estudiante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del Estudiante.
     *
     * @param nombre Nombre del Estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el primer apellido del Estudiante.
     *
     * @return Primer apellido del Estudiante.
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Establece el primer apellido del Estudiante.
     *
     * @param apellido1 Primer apellido del Estudiante.
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Obtiene el segundo apellido del Estudiante.
     *
     * @return Segundo apellido del Estudiante.
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Establece el segundo apellido del Estudiante.
     *
     * @param apellido2 Segundo apellido del Estudiante.
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Devuelve una representación en cadena del Estudiante.
     *
     * @return Cadena con el formato "DNI - Nombre".
     */
    @Override
    public String toString() {
        return dni + " - " + nombre;
    }
}
