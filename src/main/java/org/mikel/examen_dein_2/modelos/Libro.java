package org.mikel.examen_dein_2.modelos;

import java.sql.Blob;

/**
 * Representa un libro en la biblioteca.
 */
public class Libro {

    private int isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private String estado;
    private int baja;

    /**
     * Constructor con todos los atributos.
     *
     * @param codigo    Código único del libro.
     * @param titulo    Título del libro.
     * @param autor     Autor del libro.
     * @param editorial Editorial del libro.
     * @param estado    Estado del libro (ej. disponible, prestado, deteriorado).
     * @param baja      Indica si el libro está dado de baja (1) o activo (0).
     */
    public Libro(int codigo, String titulo, String autor, String editorial, String estado, int baja) {
        this.isbn = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.estado = estado;
        this.baja = baja;
    }

    /**
     * Constructor vacío.
     */
    public Libro() {
    }

    /**
     * Obtiene el código del libro.
     *
     * @return Código único del libro.
     */
    public int getIsbn() {
        return isbn;
    }

    /**
     * Establece el código del libro.
     *
     * @param isbn Código único del libro.
     */
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return Título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     *
     * @param titulo Título del libro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return Autor del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     *
     * @param autor Autor del libro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene la editorial del libro.
     *
     * @return Editorial del libro.
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Establece la editorial del libro.
     *
     * @param editorial Editorial del libro.
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Obtiene el estado del libro.
     *
     * @return Estado del libro (ej. disponible, prestado, deteriorado).
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del libro.
     *
     * @param estado Estado del libro.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el estado de baja del libro.
     *
     * @return 1 si el libro está dado de baja, 0 si está activo.
     */
    public int getBaja() {
        return baja;
    }

    /**
     * Establece el estado de baja del libro.
     *
     * @param baja 1 si el libro está dado de baja, 0 si está activo.
     */
    public void setBaja(int baja) {
        this.baja = baja;
    }

    /**
     * Devuelve una representación en cadena del libro.
     *
     * @return Cadena con el formato "Código - Título".
     */
    @Override
    public String toString() {
        return isbn + " - " + titulo;
    }
}
