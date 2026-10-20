package com.poo.javafx.Instanciacao.Andre;

import com.poo.javafx.CRUDView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class CaronaView extends CRUDView<CaronaModel> {
    private TextField motorista;
    private TextField origem;
    private TextField destino;
    private TextField vagas;
    private Button btnConfirmar;
    private Button btnConcluir;
    private Button btnCancelar;

    public CaronaView() {
        super();

        motorista = new TextField();
        motorista.setPromptText("Motorista");

        origem = new TextField();
        origem.setPromptText("Origem");

        destino = new TextField();
        destino.setPromptText("Destino");

        vagas = new TextField();
        vagas.setPromptText("Vagas");


        this.formulario.getChildren().addAll(motorista, origem, destino, vagas);

        btnConfirmar = new Button("Confirmar");
        btnConcluir = new Button("Concluir");
        btnCancelar = new Button("Cancelar");
        getAcoes().getChildren().addAll(btnConfirmar, btnConcluir, btnCancelar);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<CaronaModel, String> colMotorista = new TableColumn<>("Motorista");
        colMotorista.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotorista()));

        TableColumn<CaronaModel, String> colOrigem = new TableColumn<>("Origem");
        colOrigem.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrigem()));

        TableColumn<CaronaModel, String> colDestino = new TableColumn<>("Destino");
        colDestino.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDestino()));

        TableColumn<CaronaModel, Integer> colVagas = new TableColumn<>("Vagas");
        colVagas.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getVagas()).asObject());

        TableColumn<CaronaModel, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        tabela.getColumns().addAll(colMotorista, colOrigem, colDestino, colVagas, colStatus);
    }

    @Override
    public String getTitulo() {
        return "Gerenciamento de Caronas";
    }

    public TextField getMotorista() {
        return motorista;
    }

    public TextField getOrigem() {
        return origem;
    }

    public TextField getDestino() {
        return destino;
    }

    public TextField getVagas() {
        return vagas;
    }

    public Button getBtnConfirmar() {
        return btnConfirmar;
    }

    public Button getBtnConcluir() {
        return btnConcluir;
    }

    public Button getBtnCancelar() {
        return btnCancelar;
    }
}
