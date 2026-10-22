
package com.poo.javafx.Instanciacao.Eduardo;

import java.time.format.DateTimeFormatter;

import com.poo.javafx.CRUDView;
import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class TransacaoView extends CRUDView<TransacaoModel> {
    private static final DateTimeFormatter FORMATO_TIMESTAMP = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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
                .setCellValueFactory(data -> new SimpleStringProperty(nomeOuRemovido(data.getValue().getUserOrigem())));

        TableColumn<TransacaoModel, String> colUserDestino = new TableColumn<>("Usuario Destino");
        colUserDestino
                .setCellValueFactory(data -> new SimpleStringProperty(nomeOuRemovido(data.getValue().getUserDestino())));

        TableColumn<TransacaoModel, Double> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getValor()).asObject());

        TableColumn<TransacaoModel, String> colTimestamp = new TableColumn<>("Timestamp");
        colTimestamp.setCellValueFactory(data -> new SimpleStringProperty(FORMATO_TIMESTAMP.format(data.getValue().getTimestamp())));

        tabela.getColumns().addAll(colUserOrigem, colUserDestino, colValor, colTimestamp);
    }

    private static String nomeOuRemovido(PassageiroModel passageiro) {
        return passageiro != null ? passageiro.toString() : "(passageiro removido)";
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
