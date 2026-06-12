package com.poo.javafx;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class MenuView extends StackPane {

    private Button btnPassageiros;
    private Button btnVeiculos;
    private Button btnTrajetos;
    private Button btnEmpresas;
    private Button btnTransacoes;
    private Button btnSair;

    public MenuView() {
        this.getStyleClass().add("menu");

        SVGPath iconeSair = new SVGPath();
        iconeSair.setContent(
                "M10 22H5C3.89543 22 3 21.1046 3 20V4C3 2.89543 3.89543 2 5 2H10V4H5V20H10V22ZM15.0858 16.9142L13.6716 15.5L16.1716 13H8V11H16.1716L13.6716 8.5L15.0858 7.08579L20 12L15.0858 16.9142Z");
        iconeSair.getStyleClass().add("menu__exit-icon");

        btnSair = new Button();
        btnSair.setGraphic(iconeSair);
        btnSair.getStyleClass().addAll("menu__btn-exit");

        HBox btnSairWrapper = new HBox(btnSair);
        btnSairWrapper.getStyleClass().add("menu-btn-exit__wrapper");
        btnSairWrapper.setPickOnBounds(false);

        ImageView logo = new ImageView(getClass().getResource("logo.png").toExternalForm());
        logo.getStyleClass().add("menu__logo");

        btnPassageiros = new Button("Gerenciar Passageiros");
        btnVeiculos = new Button("Gerenciar Veículos");
        btnTrajetos = new Button("Gerenciar Trajetos");
        btnEmpresas = new Button("Gerenciar Empresas");
        btnTransacoes = new Button("Gerenciar Transações");

        VBox main = new VBox(logo, btnPassageiros, btnVeiculos, btnTrajetos, btnEmpresas, btnTransacoes);
        main.getStyleClass().add("menu__main");

        this.getChildren().addAll(main, btnSairWrapper);
    }

    public String getTitulo() {
        return "CoCar - Menu Principal";
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

    public Button getBtnEmpresas() {
        return btnEmpresas;
    }

    public Button getBtnTransacoes() {
        return btnTransacoes;
    }

    public Button getBtnSair() {
        return btnSair;
    }
}
