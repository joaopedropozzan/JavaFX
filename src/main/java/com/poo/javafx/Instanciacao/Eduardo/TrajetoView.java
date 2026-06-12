package com.poo.javafx.Instanciacao.Eduardo;

import java.time.LocalDateTime;

import com.poo.javafx.CRUDView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import jfxtras.scene.control.LocalDateTimeTextField;

public class TrajetoView extends CRUDView<TrajetoModel> {
    private TextField origem;
    private TextField destino;
    private LocalDateTimeTextField horarioSaida;

    public TrajetoView() {
        super();

        origem = new TextField();
        origem.setPromptText("Origem do Trajeto");
        destino = new TextField();
        destino.setPromptText("Destino do Trajeto");

        horarioSaida = new LocalDateTimeTextField();
        horarioSaida.setPromptText("Data e hora de Sáida");

        this.containerFormulario.getChildren().addAll(origem, destino, horarioSaida);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<TrajetoModel, String> colOrigem = new TableColumn<>("Origem");
        colOrigem.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrigem()));

        TableColumn<TrajetoModel, String> colDestino = new TableColumn<>("Destino");
        colDestino.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDestino()));

        TableColumn<TrajetoModel, LocalDateTime> colHorarioSaida = new TableColumn<>("Horario Saída");
        colHorarioSaida.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getHorarioSaida()));

        tabela.getColumns().addAll(colOrigem, colDestino, colHorarioSaida);
    }

    @Override
    public String getTitulo() {
        return "Gerenciamento de Trajetos";
    }

    public TextField getOrigem() {
        return origem;
    }

    public TextField getDestino() {
        return destino;
    }

    public LocalDateTimeTextField getHorarioSaida() {
        return horarioSaida;
    }
}
