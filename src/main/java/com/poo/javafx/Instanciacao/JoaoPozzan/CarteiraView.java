package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.CRUDView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class CarteiraView extends CRUDView<CarteiraModel> {
    // Campos que o usuário vai digitar
    private TextField txtSaldo;
    private TextField txtNumeroCartao;
    private TextField txtTitularConta;

    public CarteiraView() {
        super(); // Herda a tabela e os botões

        // Inicializa os campos
        txtSaldo = new TextField();
        txtSaldo.setPromptText("Saldo da Conta");

        txtNumeroCartao = new TextField();
        txtNumeroCartao.setPromptText("Número do Cartão");

        txtTitularConta = new TextField();
        txtTitularConta.setPromptText("Titular da Conta");

        this.formulario.getChildren().addAll(txtSaldo, txtNumeroCartao, txtTitularConta);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<CarteiraModel, Integer> colSaldo = new TableColumn<>("Saldo");
        colSaldo.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getSaldo()));

        TableColumn<CarteiraModel, Integer> colNumero = new TableColumn<>("Nº do Cartão");
        colNumero.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getNumeroCartao()));

        TableColumn<CarteiraModel, String> colTitular = new TableColumn<>("Titular");
        colTitular.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitularConta()));

        tabela.getColumns().addAll(colSaldo, colNumero, colTitular);
    }

    @Override
    public String getTitulo() {
        return "Gerenciamento de Carteiras";
    }

    public TextField getTxtSaldo() {
        return txtSaldo;
    }

    public TextField getTxtNumeroCartao() {
        return txtNumeroCartao;
    }

    public TextField getTxtTitularConta() {
        return txtTitularConta;
    }
}