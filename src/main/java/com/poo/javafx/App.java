package com.poo.javafx;

import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroController;
import com.poo.javafx.Instanciacao.JoaoMosson.VeiculoController;
import com.poo.javafx.Instanciacao.Eduardo.TrajetoController;
import com.poo.javafx.Instanciacao.JoaoPozzan.EmpresaController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private Router router;

    @Override
    public void start(Stage stage) {
        MenuView menuView = new MenuView();
        Scene scene = new Scene(menuView, 400, 300);

        router = new Router(stage, menuView);

        menuView.getBtnPassageiros().setOnAction(e -> router.navegarPara(new PassageiroController()));
        menuView.getBtnVeiculos().setOnAction(e -> router.navegarPara(new VeiculoController()));
        menuView.getBtnTrajetos().setOnAction(e -> router.navegarPara(new TrajetoController()));
        menuView.getBtnEmpresa().setOnAction(e -> router.navegarPara(new EmpresaController()));

        stage.setScene(scene);
        stage.setTitle(menuView.getTitulo());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
