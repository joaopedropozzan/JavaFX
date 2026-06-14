package com.poo.javafx;

import com.poo.javafx.Instanciacao.Andre.BeneficioController;
import com.poo.javafx.Instanciacao.Andre.CaronaController;
import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroController;
import com.poo.javafx.Instanciacao.JoaoMosson.VeiculoController;
import com.poo.javafx.Instanciacao.Eduardo.TrajetoController;
import com.poo.javafx.Instanciacao.Eduardo.TransacaoController;
import com.poo.javafx.Instanciacao.JoaoPozzan.EmpresaController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private Router router;

    @Override
    public void start(Stage stage) {
        MenuView menuView = new MenuView();
        router = new Router(stage, menuView);

        menuView.getBtnPassageiros().setOnAction(e -> router.navegarPara(new PassageiroController()));
        menuView.getBtnVeiculos().setOnAction(e -> router.navegarPara(new VeiculoController()));
        menuView.getBtnTrajetos().setOnAction(e -> router.navegarPara(new TrajetoController()));
        menuView.getBtnEmpresas().setOnAction(e -> router.navegarPara(new EmpresaController()));
        menuView.getBtnTransacoes().setOnAction(e -> router.navegarPara(new TransacaoController()));
        menuView.getBtnBeneficios().setOnAction(e -> router.navegarPara(new BeneficioController()));
        menuView.getBtnCaronas().setOnAction(e -> router.navegarPara(new CaronaController()));
        menuView.getBtnSair().setOnAction(e -> {
            javafx.application.Platform.exit();
            System.exit(0);
        });

        Scene scene = new Scene(menuView);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle(menuView.getTitulo());
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
