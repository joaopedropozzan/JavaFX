package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.ViewGenerica;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class VeiculoView extends ViewGenerica<VeiculoModel> {

    private TextField txtPlaca;
    private TextField txtModelo;
    private TextField txtAno;

    public VeiculoView() {
        super();

        // Inicializa os campos do formulário
        txtPlaca = new TextField();
        txtPlaca.setPromptText("Placa (ex: ABC1D23)");

        txtModelo = new TextField();
        txtModelo.setPromptText("Modelo (ex: HB20)");

        txtAno = new TextField();
        txtAno.setPromptText("Ano (ex: 2026)");

        HBox formulario = new HBox(10, txtPlaca, txtModelo, txtAno);

        // Adiciona o formulário na tela
        this.getChildren().add(0, formulario);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<VeiculoModel, String> colPlaca = new TableColumn<>("Placa");
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));

        TableColumn<VeiculoModel, String> colModelo = new TableColumn<>("Modelo");
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<VeiculoModel, Integer> colAno = new TableColumn<>("Ano");
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));

        tabela.getColumns().addAll(colPlaca, colModelo, colAno);
    }

    // Getters para o Controller
    public TextField getTxtPlaca() { return txtPlaca; }
    public TextField getTxtModelo() { return txtModelo; }
    public TextField getTxtAno() { return txtAno; }
}