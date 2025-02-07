package org.mikel.examen_dein_2.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.mikel.examen_dein_2.bbdd.ConexionBBDD;
import org.mikel.examen_dein_2.dao.DaoEstudiante;
import org.mikel.examen_dein_2.dao.DaoLibro;
import org.mikel.examen_dein_2.dao.DaoPrestamo;
import org.mikel.examen_dein_2.modelos.Estudiante;
import org.mikel.examen_dein_2.modelos.Libro;
import org.mikel.examen_dein_2.modelos.Prestamo;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador para la gestión de préstamos de libros en la aplicación.
 * Permite la creación de nuevos préstamos y la visualización de informes relacionados.
 */
public class ControllerPrestamo {

    /**
     * ComboBox que permite seleccionar un alumno para el préstamo.
     */
    @FXML
    private ComboBox<Estudiante> comboAlumnos;

    /**
     * ComboBox que permite seleccionar un libro para el préstamo.
     */
    @FXML
    private ComboBox<Libro> comboLibros;

    /**
     * DatePicker que permite seleccionar la fecha del préstamo.
     */
    @FXML
    private DatePicker fecha;

    @FXML
    private CheckBox checkOnline;

    /**
     * Acción de guardar un nuevo préstamo.
     * Valida los datos ingresados y crea un nuevo préstamo si los datos son correctos.
     * También muestra el informe del préstamo creado.
     *
     * @param event El evento de acción de guardar.
     */
    @FXML
    void accionGuardar(ActionEvent event) {
        String error=validadDatos();
        if (error.isEmpty()){
            boolean online=checkOnline.isSelected();
            Prestamo p=new Prestamo(0,comboAlumnos.getValue(),comboLibros.getValue(),online,fecha.getValue());
            int idPrestamo=DaoPrestamo.crearPrestamo(p);
            if (idPrestamo != -1) {
                mostrarInfo("Prestamo creado correctamente");
                mostrarInforme(idPrestamo);
                cerrarVentana();
            }else {
                mostrarInfo("Error al crear el Prestamo");
            }
        }else {
            mostrarError(error);
        }
    }

    /**
     * Acción de cancelar la operación.
     * Cierra la ventana actual sin realizar ninguna acción.
     *
     * @param event El evento de acción de cancelar.
     */
    @FXML
    void accionCancelar(ActionEvent event) {
        cerrarVentana();
    }

    /**
     * Cierra la ventana actual.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) fecha.getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra el informe relacionado con el préstamo creado.
     * Utiliza la ID del préstamo para generar el informe.
     *
     * @param id El ID del préstamo.
     */
    void mostrarInforme(int id){
        Map<String, Object> parameters = new HashMap<>();

        // Añadir la ruta de las imágenes
        String imagePath = getClass().getResource("/imagenes/").toString(); // Ruta de la carpeta de imágenes
        parameters.put("IMAGE_PATH", imagePath);
        parameters.put("ID_PRESTAMO", id);
        generarInforme("Informe1.jasper",parameters);
    }

    /**
     * Genera y muestra el informe Jasper basado en el archivo y parámetros proporcionados.
     *
     * @param archivoJasper El archivo Jasper que contiene el informe.
     * @param parameters   Los parámetros a utilizar en el informe.
     */
    private void generarInforme(String archivoJasper, Map<String, Object> parameters) {
        ConexionBBDD db;
        try {
            // Crear una nueva conexión a la base de datos
            db = new ConexionBBDD();

            // Cargar el archivo Jasper del informe
            InputStream reportStream = db.getClass().getResourceAsStream("/jasper/" + archivoJasper);

            // Verificar si el archivo fue encontrado
            if (reportStream == null) {
                System.out.println("Archivo NO encontrado");
                return;
            }

            // Cargar el informe Jasper
            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);

            // Verificar si el mapa de parámetros es nulo o vacío
            if (parameters == null) {
                parameters = new HashMap<>();
            }

            // Añadir la ruta de las imágenes si no está ya en los parámetros
            if (!parameters.containsKey("IMAGE_PATH")) {
                String imagePath = db.getClass().getResource("/imagenes/").toString(); // Ruta de la carpeta de imágenes
                parameters.put("IMAGE_PATH", imagePath);
            }

            // Llenar el informe con datos
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, db.getConnection());

            // Mostrar el informe
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);

        } catch (SQLException e) {
            mostrarError("No se ha podido establecer conexión con la Base de Datos");
            e.printStackTrace();
        } catch (JRException e) {
            mostrarError("Error al procesar el informe Jasper");
            e.printStackTrace();
        }
    }



    /**
     * Metodo que valida los datos ingresados en los campos del formulario.
     * Verifica si los campos están vacíos o si el formato de los valores es incorrecto.
     *
     * @return Un mensaje de error si los datos son inválidos, o una cadena vacía si los datos son válidos.
     */
    public String validadDatos() {
        String error = "";

        // Verificar si el ComboBox tiene un valor seleccionado
        if (comboLibros.getValue() == null) {
            error += "Debe seleccionar un Libro en el campo 'Libro'\n";
        }

        if (comboAlumnos.getValue() == null) {
            error += "Debe seleccionar un alumno en el campo 'Alumno'\n";
        }

        // Verificar si la fecha es nula o posterior al día de hoy
        if (fecha.getValue() == null) {  // Asumiendo que fechaPrestamo es tu DatePicker
            error += "Debe seleccionar una fecha en el campo 'Fecha'\n";
        } else if (fecha.getValue().isAfter(LocalDate.now())) {
            error += "La fecha no puede ser posterior al día de hoy\n";
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
     * Inicializa el controlador, cargando los valores de los ComboBox con los datos de los alumnos y libros disponibles.
     */
    @FXML
    public void initialize() {
        // Cargar los valores a los ComboBox
        comboAlumnos.getItems().addAll(DaoEstudiante.todosEstudiantes());
        comboLibros.getItems().addAll(DaoLibro.todosLibrosParaPrestar());
        //Elegir el primer item
        comboAlumnos.getSelectionModel().selectFirst();
        comboLibros.getSelectionModel().selectFirst();

    }
}
