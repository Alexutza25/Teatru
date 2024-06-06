module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.demo to javafx.fxml;
    opens domain to javafx.base;
    exports com.example.demo;
}