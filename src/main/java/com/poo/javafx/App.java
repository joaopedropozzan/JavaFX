package com.poo.javafx;

import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroController;
import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroView;
import com.poo.javafx.Instanciacao.JoaoMosson.VeiculoController;
import com.poo.javafx.Instanciacao.JoaoMosson.VeiculoView;
import com.poo.javafx.Instanciacao.Eduardo.TrajetoView;
import com.poo.javafx.Instanciacao.Eduardo.TrajetoController;
import com.poo.javafx.Instanciacao.JoaoPozzan.EmpresaController;
import com.poo.javafx.Instanciacao.JoaoPozzan.EmpresaView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class App extends Application {

    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // 1. Cria a Tela de Menu
        MenuView menuView = new MenuView();
        scene = new Scene(menuView, 400, 300);

        // 2. Configura a ação dos botões do Menu
        menuView.getBtnPassageiros().setOnAction(e -> abrirTelaPassageiro());
        menuView.getBtnVeiculos().setOnAction(e -> abrirTelaVeiculo());
        menuView.getBtnTrajetos().setOnAction(e -> abrirTelaTrajeto());
        menuView.getBtnEmpresa().setOnAction(actionEvent -> abrirTelaEmpresa());

        // 3. Exibe o Menu inicial
        stage.setTitle("CoCar - Menu Principal");
        stage.setScene(scene);
        stage.show();
    }

    // --- MÉTODOS DE ROTEAMENTO ---
    private void abrirTelaPassageiro() {
        PassageiroView view = new PassageiroView();
        new PassageiroController(view);

        Button btnVoltar = new Button("⬅ Voltar ao Menu Principal");
        btnVoltar.setOnAction(e -> stage.setScene(scene));
        view.getChildren().add(0, btnVoltar);

        Scene cenaPassageiro = new Scene(view, 800, 600);
        stage.setTitle("Gerenciamento de Passageiros");
        stage.setScene(cenaPassageiro);
    }

    private void abrirTelaVeiculo() {
        VeiculoView view = new VeiculoView();
        new VeiculoController(view);

        Button btnVoltar = new Button("⬅ Voltar ao Menu Principal");
        btnVoltar.setOnAction(e -> stage.setScene(scene));
        view.getChildren().add(0, btnVoltar);

        Scene cenaVeiculo = new Scene(view, 800, 600);
        stage.setTitle("Gerenciamento de Veículos");
        stage.setScene(cenaVeiculo);
    }

    private void abrirTelaTrajeto() {
        TrajetoView view = new TrajetoView();
        new TrajetoController(view);

        Button btnVoltar = new Button("⬅ Voltar ao Menu Principal");
        btnVoltar.setOnAction(e -> stage.setScene(scene));
        view.getChildren().add(0, btnVoltar);

        Scene cenaTrajeto = new Scene(view, 800, 600);
        stage.setTitle("Gerenciamento de Trajetos");
        stage.setScene(cenaTrajeto);
    }

    private void abrirTelaEmpresa() {
        EmpresaView view = new EmpresaView();
        new EmpresaController(view);

        Button btnVoltar = new Button("⬅ Voltar ao Menu Principal");
        btnVoltar.setOnAction(e -> stage.setScene(scene));
        view.getChildren().add(0, btnVoltar);

        Scene cenaEmpresa = new Scene(view, 800, 600);
        stage.setTitle("Gerenciamento de Empresas");
        stage.setScene(cenaEmpresa);
    };

    public static void main(String[] args) {
        launch();
    }
}
