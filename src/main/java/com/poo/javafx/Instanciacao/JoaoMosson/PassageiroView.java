package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.ViewGenerica;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.time.LocalDate;

public class PassageiroView extends ViewGenerica<PassageiroModel> {

    // Campos que o usuário vai digitar
    private TextField txtId;
    private TextField txtNome;
    private DatePicker dpDataNascimento;

    public PassageiroView() {
        super(); // Herda a tabela e os botões

        // Inicializa os campos
        txtId = new TextField();
        txtId.setPromptText("ID");

        txtNome = new TextField();
        txtNome.setPromptText("Nome do Passageiro");

        dpDataNascimento = new DatePicker();
        dpDataNascimento.setPromptText("Data de Nascimento");

        // Agrupa tudo numa linha horizontal
        HBox formulario = new HBox(10, txtId, txtNome, dpDataNascimento);

        // Adiciona o formulário no topo da tela (índice 0 do VBox)
        this.getChildren().add(0, formulario);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<PassageiroModel, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<PassageiroModel, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<PassageiroModel, LocalDate> colData = new TableColumn<>("Data Nasc.");
        colData.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));

        // Adiciona as colunas na tabela genérica
        tabela.getColumns().addAll(colId, colNome, colData);
    }

    // Getters para o Controller conseguir capturar o que foi digitado
    public TextField getTxtId() {
        return txtId;
    }

    public TextField getTxtNome() {
        return txtNome;
    }

    public DatePicker getDpDataNascimento() {
        return dpDataNascimento;
    }
}
