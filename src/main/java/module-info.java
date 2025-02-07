module org.mikel.examen_dein_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires java.desktop;

    opens org.mikel.dein_proyecto_2.controladores to javafx.fxml;
    opens org.mikel.dein_proyecto_2.modelos to javafx.base;
    opens org.mikel.examen_dein_2 to javafx.fxml;
    exports org.mikel.examen_dein_2;
}