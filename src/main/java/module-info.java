module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.poo.javafx to javafx.fxml;
    exports com.poo.javafx;
}