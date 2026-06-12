
package com.poo.javafx.Instanciacao.Eduardo;

import java.time.LocalDateTime;

import com.poo.javafx.CRUDView;
import com.poo.javafx.Repository;
import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class TransacaoView extends CRUDView<TransacaoModel> {
    private ComboBox<PassageiroModel> userOrigem;
    private ComboBox<PassageiroModel> userDestino;
    private TextField valor;

    public TransacaoView() {
        super();

        StringConverter<PassageiroModel> passageiroParaString = new StringConverter<>() {
            @Override
            public PassageiroModel fromString(String arg0) {
                throw new UnsupportedOperationException("Unimplemented method 'fromString'");
            }

            @Override
            public String toString(PassageiroModel user) {
                return user.getNome();
            }

        };

        userOrigem = new ComboBox<>();
        userOrigem.setConverter(passageiroParaString);
        userOrigem.setPromptText("Origem do Trajeto");
        userOrigem.getItems().addAll(new Repository<>(PassageiroModel.class).objetos());

        userDestino = new ComboBox<>();
        userDestino.setConverter(passageiroParaString);
        userDestino.setPromptText("Destino do Trajeto");
        userDestino.getItems().addAll(new Repository<>(PassageiroModel.class).objetos());

        valor = new TextField();
        valor.setPromptText("Valor");

        this.containerFormulario.getChildren().addAll(userOrigem, userDestino, valor);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<TransacaoModel, String> colUserOrigem = new TableColumn<>("Usuário Origem");
        colUserOrigem
                .setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserOrigem().getNome()));

        TableColumn<TransacaoModel, String> colUserDestino = new TableColumn<>("Usuario Destino");
        colUserDestino
                .setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserDestino().getNome()));

        TableColumn<TransacaoModel, Double> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getValor()).asObject());

        TableColumn<TransacaoModel, LocalDateTime> colTimestamp = new TableColumn<>("Timestamp");
        colTimestamp.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getTimestamp()));

        tabela.getColumns().addAll(colUserOrigem, colUserDestino, colValor, colTimestamp);
    }

    @Override
    public String getTitulo() {
        return "Gerenciamento de Trajetos";
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
