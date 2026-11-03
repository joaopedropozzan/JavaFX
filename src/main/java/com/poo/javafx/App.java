package com.poo.javafx;

import com.poo.javafx.beneficio.BeneficioController;
import com.poo.javafx.carona.CaronaController;
import com.poo.javafx.carteira.CarteiraController;
import com.poo.javafx.empresa.EmpresaController;
import com.poo.javafx.passageiro.PassageiroController;
import com.poo.javafx.trajeto.TrajetoController;
import com.poo.javafx.transacao.TransacaoController;
import com.poo.javafx.veiculo.VeiculoController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        MenuView menuView = new MenuView();
        Router.getInstance().inicializar(stage, menuView);

        menuView.getBtnPassageiros().setOnAction(e -> Router.getInstance().navegarPara(new PassageiroController()));
        menuView.getBtnVeiculos().setOnAction(e -> Router.getInstance().navegarPara(new VeiculoController()));
        menuView.getBtnTrajetos().setOnAction(e -> Router.getInstance().navegarPara(new TrajetoController()));
        menuView.getBtnEmpresas().setOnAction(e -> Router.getInstance().navegarPara(new EmpresaController()));
        menuView.getBtnTransacoes().setOnAction(e -> Router.getInstance().navegarPara(new TransacaoController()));
        menuView.getBtnBeneficios().setOnAction(e -> Router.getInstance().navegarPara(new BeneficioController()));
        menuView.getBtnCaronas().setOnAction(e -> Router.getInstance().navegarPara(new CaronaController()));
        menuView.getBtnCarteiras().setOnAction(e -> Router.getInstance().navegarPara(new CarteiraController()));
        menuView.getBtnSair().setOnAction(e -> {
            javafx.application.Platform.exit();
            System.exit(0);
        });

        Scene scene = new Scene(menuView);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png"), 32, 32, true, true));
        stage.setScene(scene);
        stage.setTitle(menuView.getTitulo());
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
