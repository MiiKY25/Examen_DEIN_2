package org.mikel.examen_dein_2.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.mikel.examen_dein_2.dao.DaoLibro;
import org.mikel.examen_dein_2.modelos.Libro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Controlador para la gestión de libros. Permite crear y modificar libros,
 * gestionar las imágenes asociadas a los libros y validar los datos ingresados.
 */
public class ControllerLibro {

    /**
     * ComboBox para seleccionar el estado del libro.
     */
    @FXML
    private ComboBox<String> comboEstado;

    /**
     * Campo de texto para ingresar el autor del libro.
     */
    @FXML
    private TextField txtAutor;

    /**
     * Campo de texto para ingresar la editorial del libro.
     */
    @FXML
    private TextField txtEditorial;

    /**
     * Campo de texto para ingresar el título del libro.
     */
    @FXML
    private TextField txtTitulo;

    /**
     * Vista de imagen para mostrar la foto del libro.
     */
    @FXML
    private ImageView imgFoto;

    /**
     * Botón para borrar la imagen seleccionada del libro.
     */
    @FXML
    private Button btnBorrarFoto;

    /**
     * Objeto Libro que representa el libro que se está editando o creando.
     */
    private Libro libro;


    /**
     * Acción que guarda el libro. Crea un nuevo libro si no existe o lo modifica si ya existe.
     * Valida los datos antes de realizar la operación.
     *
     * @param event El evento que dispara la acción.
     */
    @FXML
    void accionGuardar(ActionEvent event) {
        String error=validadDatos();
        if (error.isEmpty()){
            if (libro==null){
                //Crear Libro
                Libro l =new Libro(0,txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),comboEstado.getValue(),0);
                if (DaoLibro.crearLibro(l)){
                    mostrarInfo("Libro creado correctamente");
                    cerrarVentana();
                }else {
                    mostrarInfo("Error al crear el Libro");
                }
            }else {
                //Modificar Libro
                Libro l=new Libro(libro.getIsbn(),txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),comboEstado.getValue(),libro.getBaja());
                if (DaoLibro.editarLibro(l)){
                    mostrarInfo("Libro editado correctamente");
                    cerrarVentana();
                }else {
                    mostrarInfo("Error al editar el Libro");
                }
            }
        }else {
            mostrarError(error);
        }
    }

    /**
     * Acción que cancela la operación y cierra la ventana.
     *
     * @param event El evento que dispara la acción.
     */
    @FXML
    void accionCancelar(ActionEvent event) {
        cerrarVentana();
    }

    /**
     * Cierra la ventana actual.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) txtAutor.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo que valida los datos ingresados en los campos del formulario.
     * Verifica si los campos están vacíos o si el formato de los valores es incorrecto.
     *
     * @return Un mensaje de error si los datos son inválidos, o una cadena vacía si los datos son válidos.
     */
    public String validadDatos() {
        String error = "";

        // Verificar si cada campo está vacío o cumple con las restricciones de formato
        if (txtTitulo.getText().isEmpty()) {
            error += "El campo 'Titulo' no puede estar vacio\n";
        }

        if (txtEditorial.getText().isEmpty()) {
            error += "El campo 'Editorial' no puede estar vacio\n";
        }

        if (txtAutor.getText().isEmpty()) {
            error += "El campo 'Autor' no puede estar vacio\n";
        }

        // Verificar si el ComboBox tiene un valor seleccionado
        if (comboEstado.getValue() == null || comboEstado.getValue().isEmpty()) {
            error += "Debe seleccionar un estado en el campo 'Estado'\n";
        }

        return error;
    }

    /**
     * Muestra un mensaje de error en una ventana de alerta.
     *
     * @param error El mensaje de error a mostrar en el cuadro de diálogo.
     */
    void mostrarError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }

    /**
     * Metodo que muestra un mensaje de información en una ventana emergente.
     *
     * @param info El mensaje de información que se mostrará en la ventana emergente.
     */
    void mostrarInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText(info);
        alert.showAndWait();
    }

    /**
     * Establece un libro para editar sus datos en el formulario.
     *
     * @param l El libro a editar.
     */
    public void setLibro(Libro l) {
        this.libro=l;

        // TextFields
        txtTitulo.setText(libro.getTitulo());
        txtAutor.setText(libro.getAutor());
        txtEditorial.setText(libro.getEditorial());

        //ComboBox
        comboEstado.setValue(libro.getEstado());
    }

    /**
     * Inicializa el controlador y carga los valores iniciales del ComboBox.
     */
    @FXML
    public void initialize() {
        // Cargar los valores en el ComboBox
        comboEstado.getItems().addAll(
                "Nuevo",
                "Usado nuevo",
                "Usado seminuevo",
                "Usado estropeado",
                "Restaurado"
        );
        comboEstado.setValue("Nuevo");
    }
}
