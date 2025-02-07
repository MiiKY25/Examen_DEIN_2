module org.mikel.examen_dein_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.mikel.examen_dein_2 to javafx.fxml;
    exports org.mikel.examen_dein_2;
}