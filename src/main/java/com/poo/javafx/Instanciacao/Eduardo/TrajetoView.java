package com.poo.javafx.Instanciacao.Eduardo;

import java.time.LocalDateTime;

import com.poo.javafx.View;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import jfxtras.scene.control.LocalDateTimeTextField;

public class TrajetoView extends View<TrajetoModel> {
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

        HBox formulario = new HBox(10, origem, destino, horarioSaida);

        this.getChildren().add(0, formulario);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<TrajetoModel, String> colOrigem = new TableColumn<>("Origem");
        colOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));

        TableColumn<TrajetoModel, String> colDestino = new TableColumn<>("Destino");
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));

        TableColumn<TrajetoModel, LocalDateTime> colHorarioSaida = new TableColumn<>("Horario Saída");
        colHorarioSaida.setCellValueFactory(new PropertyValueFactory<>("horarioSaida"));

        tabela.getColumns().addAll(colOrigem, colDestino, colHorarioSaida);
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
