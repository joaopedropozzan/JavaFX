package com.poo.javafx;

import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroController;
import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroView;
import com.poo.javafx.Instanciacao.JoaoMosson.VeiculoController;
import com.poo.javafx.Instanciacao.JoaoMosson.VeiculoView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private Stage mainStage;
    private Scene cenaMenu; // Guarda a cena do menu na memória para poder voltar para ela

    @Override
    public void start(Stage stage) {
        this.mainStage = stage;

        // 1. Cria a Tela de Menu
        MenuView menuView = new MenuView();
        cenaMenu = new Scene(menuView, 400, 300);

        // 2. Configura a ação dos botões do Menu
        menuView.getBtnPassageiros().setOnAction(e -> abrirTelaPassageiro());
        menuView.getBtnVeiculos().setOnAction(e -> abrirTelaVeiculo());

        // 3. Exibe o Menu inicial
        mainStage.setTitle("CoCar - Menu Principal");
        mainStage.setScene(cenaMenu);
        mainStage.show();
    }

    // --- MÉTODOS DE ROTEAMENTO ---

    private void abrirTelaPassageiro() {
        PassageiroView view = new PassageiroView();
        new PassageiroController(view); // Engata o cano da lógica

        // INJEÇÃO DINÂMICA DO BOTÃO VOLTAR
        Button btnVoltar = new Button("⬅ Voltar ao Menu Principal");
        btnVoltar.setOnAction(e -> mainStage.setScene(cenaMenu)); // Ação de voltar para a cena salva
        view.getChildren().add(0, btnVoltar); // Adiciona no topo da tela (índice 0)

        Scene cenaPassageiro = new Scene(view, 800, 600);
        mainStage.setTitle("Gerenciamento de Passageiros");
        mainStage.setScene(cenaPassageiro); // Troca a cena no palco principal
    }

    private void abrirTelaVeiculo() {
        VeiculoView view = new VeiculoView();
        new VeiculoController(view);

        Button btnVoltar = new Button("⬅ Voltar ao Menu Principal");
        btnVoltar.setOnAction(e -> mainStage.setScene(cenaMenu));
        view.getChildren().add(0, btnVoltar);

        Scene cenaVeiculo = new Scene(view, 800, 600);
        mainStage.setTitle("Gerenciamento de Veículos");
        mainStage.setScene(cenaVeiculo);
    }

    public static void main(String[] args) {
        launch();
    }
}