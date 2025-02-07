package org.mikel.examen_dein_2.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mikel.examen_dein_2.dao.DaoEstudiante;
import org.mikel.examen_dein_2.modelos.Estudiante;

/**
 * El controlador para gestionar las operaciones de los alumnos en la interfaz de usuario.
 * Permite crear y editar registros de alumnos en la base de datos.
 */
public class ControllerEstudiante {

    /**
     * Campo de texto para el primer apellido del alumno.
     */
    @FXML
    private TextField txtApellido1;

    /**
     * Campo de texto para el segundo apellido del alumno.
     */
    @FXML
    private TextField txtApellido2;

    /**
     * Campo de texto para el DNI del alumno.
     */
    @FXML
    private TextField txtDNI;

    /**
     * Campo de texto para el nombre del alumno.
     */
    @FXML
    private TextField txtNombre;

    /**
     * Objeto {@link Estudiante} que representa al alumno que se está creando o editando.
     */
    private Estudiante alumno;

    /**
     * Maneja la acción de guardar los datos de un alumno.
     * Si el alumno ya existe, muestra un mensaje de error.
     * Si el alumno es nuevo, lo crea en la base de datos, de lo contrario, edita el alumno existente.
     *
     * @param event El evento de acción que invoca este metodo.
     */
    @FXML
    void accionGuardar(ActionEvent event) {
        String error=validadDatos();
        if (error.isEmpty()){
            if (alumno==null){
                //Crear Alumno
                if (DaoEstudiante.existeDNI(txtDNI.getText())) {
                    mostrarError("El DNI ya existe en la base de datos.");
                    return;
                }

                Estudiante e =new Estudiante(txtDNI.getText(),txtNombre.getText(),txtApellido1.getText(),txtApellido2.getText());
                if (DaoEstudiante.crearEstudiante(e)){
                    mostrarInfo("Alumno creado correctamente");
                    cerrarVentana();
                }else {
                    mostrarInfo("Error al crear el Alumno");
                }
            }else {
                //Modificar Alumno
                Estudiante e =new Estudiante(txtDNI.getText(),txtNombre.getText(),txtApellido1.getText(),txtApellido2.getText());
                if (DaoEstudiante.editarEstudiante(e)){
                    mostrarInfo("Alumno editado correctamente");
                    cerrarVentana();
                }else {
                    mostrarInfo("Error al editar el Alumno");
                }
            }
        }else {
            mostrarError(error);
        }
    }

    /**
     * Maneja la acción de cancelar la operación y cerrar la ventana.
     *
     * @param event El evento de acción que invoca este metodo.
     */
    @FXML
    void accionCancelar(ActionEvent event) {
        cerrarVentana();
    }

    /**
     * Cierra la ventana actual del formulario.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
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
        if (txtNombre.getText().isEmpty()) {
            error += "El campo 'Nombre' no puede estar vacio\n";
        }

        if (txtDNI.getText().isEmpty()) {
            error += "El campo 'DNI' no puede estar vacío\n";
        } else if (txtDNI.getText().length() > 9) {
            error += "El campo 'DNI' no puede tener más de 9 caracteres\n";
        }

        if (txtApellido1.getText().isEmpty()) {
            error += "El campo 'Apellido 1' no puede estar vacio\n";
        }

        if (txtApellido2.getText().isEmpty()) {
            error += "El campo 'Apellido 2' no puede estar vacio\n";
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
     * Establece el alumno que se va a editar y carga sus datos en los campos del formulario.
     * Si se está editando, el DNI no se podrá modificar.
     *
     * @param a El objeto {@link Estudiante} que se va a editar.
     */
    public void setEstudiante(Estudiante a) {
        this.alumno=a;

        //Cargar datos
        txtDNI.setText(alumno.getDni());
        txtNombre.setText(alumno.getNombre());
        txtApellido1.setText(alumno.getApellido1());
        txtApellido2.setText(alumno.getApellido2());

        // Si se edita no se puede cambiar el DNI
        txtDNI.setDisable(true);
    }
}
