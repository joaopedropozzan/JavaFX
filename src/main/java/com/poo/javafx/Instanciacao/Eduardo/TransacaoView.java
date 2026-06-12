
package com.poo.javafx.Instanciacao.Eduardo;

import java.time.LocalDateTime;

import com.poo.javafx.CRUDView;
import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class TransacaoView extends CRUDView<TransacaoModel> {
    private ComboBox<PassageiroModel> userOrigem;
    private ComboBox<PassageiroModel> userDestino;
    private TextField valor;

    public TransacaoView() {
        super();

        userOrigem = new ComboBox<>();
        userOrigem.setPromptText("Usuário Origem");

        userDestino = new ComboBox<>();
        userDestino.setPromptText("Usuário Destino");

        valor = new TextField();
        valor.setPromptText("Valor");

        this.formulario.getChildren().addAll(userOrigem, userDestino, valor);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<TransacaoModel, String> colUserOrigem = new TableColumn<>("Usuário Origem");
        colUserOrigem
                .setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserOrigem().toString()));

        TableColumn<TransacaoModel, String> colUserDestino = new TableColumn<>("Usuario Destino");
        colUserDestino
                .setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserDestino().toString()));

        TableColumn<TransacaoModel, Double> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getValor()).asObject());

        TableColumn<TransacaoModel, LocalDateTime> colTimestamp = new TableColumn<>("Timestamp");
        colTimestamp.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getTimestamp()));

        tabela.getColumns().addAll(colUserOrigem, colUserDestino, colValor, colTimestamp);
    }

    @Override
    public String getTitulo() {
        return "Gerenciamento de Transações";
    }

    public ComboBox<PassageiroModel> getUserOrigem() {
        return userOrigem;
    }

    public ComboBox<PassageiroModel> getUserDestino() {
        return userDestino;
    }

    public TextField getValor() {
        return valor;
    }
}
