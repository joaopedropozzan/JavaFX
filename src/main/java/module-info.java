module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.poo.javafx to javafx.fxml;
    exports com.poo.javafx;
    
    opens com.poo.javafx.Instanciacao.JoaoMosson to javafx.base, javafx.fxml;
    exports com.poo.javafx.Instanciacao.JoaoMosson;
}