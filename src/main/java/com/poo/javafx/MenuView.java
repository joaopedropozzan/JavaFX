package com.poo.javafx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuView extends VBox {

    private Button btnPassageiros;
    private Button btnVeiculos;
    private Button btnTrajetos;
    private Button btnEmpresa;

    public MenuView() {
        // Centraliza tudo e dá um espaçamento
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        Label titulo = new Label("Painel Administrativo CoCar");
        titulo.setFont(new Font(24));

        btnPassageiros = new Button("Gerenciar Passageiros");
        btnVeiculos = new Button("Gerenciar Veículos");
        btnTrajetos = new Button("Gerenciar Trajetos");
        btnEmpresa = new Button("Gerenciar Empresas");

        // Deixa os botões com o mesmo tamanho para ficar bonito
        btnPassageiros.setPrefWidth(200);
        btnVeiculos.setPrefWidth(200);
        btnTrajetos.setPrefWidth(200);
        btnEmpresa.setPrefWidth(200);

        // Adiciona tudo na tela
        this.getChildren().addAll(titulo, btnPassageiros, btnVeiculos, btnTrajetos, btnEmpresa);
    }

    public Button getBtnPassageiros() {
        return btnPassageiros;
    }

    public Button getBtnVeiculos() {
        return btnVeiculos;
    }

    public Button getBtnTrajetos() {
        return btnTrajetos;
    }

    public Button getBtnEmpresa() {
        return btnEmpresa;
    }

}
