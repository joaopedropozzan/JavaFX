package com.poo.javafx.carteira;

import com.poo.javafx.CRUDView;
import com.poo.javafx.passageiro.PassageiroModel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class CarteiraView extends CRUDView<CarteiraModel> {
    // Campos que o usuário vai digitar
    private TextField txtSaldo;
    private TextField txtNumeroCartao;
    private ComboBox<PassageiroModel> cbTitular;

    public CarteiraView() {
        super(); // Herda a tabela e os botões

        // Inicializa os campos
        txtSaldo = new TextField();
        txtSaldo.setPromptText("Saldo da Conta");

        txtNumeroCartao = new TextField();
        txtNumeroCartao.setPromptText("Número do Cartão");

        cbTitular = new ComboBox<>();
        cbTitular.setPromptText("Titular");
        mostrarPromptQuandoVazio(cbTitular);

        this.formulario.getChildren().addAll(txtSaldo, txtNumeroCartao, cbTitular);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<CarteiraModel, Double> colSaldo = new TableColumn<>("Saldo");
        colSaldo.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getSaldo()));

        // Mudou de Integer / SimpleObjectProperty para String / SimpleStringProperty
        TableColumn<CarteiraModel, String> colNumero = new TableColumn<>("Nº do Cartão");
        colNumero.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumeroCartao()));

        TableColumn<CarteiraModel, String> colTitular = new TableColumn<>("Titular");
        colTitular.setCellValueFactory(
                data -> new SimpleStringProperty(String.valueOf(data.getValue().getTitular())));

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

    public ComboBox<PassageiroModel> getCbTitular() {
        return cbTitular;
    }
}
