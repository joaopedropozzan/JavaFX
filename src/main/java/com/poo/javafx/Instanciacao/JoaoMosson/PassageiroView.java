package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.CRUDView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PassageiroView extends CRUDView<PassageiroModel> {

    // Campos que o usuário vai digitar
    private TextField txtCPF;
    private TextField txtNome;
    private DatePicker dpDataNascimento;

    public PassageiroView() {
        super(); // Herda a tabela e os botões

        // Inicializa os campos
        txtCPF = new TextField();
        txtCPF.setPromptText("CPF");

        txtNome = new TextField();
        txtNome.setPromptText("Nome do Passageiro");

        String pattern = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        dpDataNascimento = new DatePicker();
        dpDataNascimento.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                }
                return "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()) {
                    try {
                        return LocalDate.parse(string, formatter);
                    } catch (Exception e) {
                        return null;
                    }
                }
                return null;
            }
        });
        dpDataNascimento.setPromptText("Data de Nascimento");

        this.formulario.getChildren().addAll(txtCPF, txtNome, dpDataNascimento);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<PassageiroModel, String> colId = new TableColumn<>("CPF");
        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCPF().getValor()));

        TableColumn<PassageiroModel, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));

        TableColumn<PassageiroModel, LocalDate> colData = new TableColumn<>("Data Nasc.");
        colData.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDataNascimento()));

        // Adiciona as colunas na tabela genérica
        tabela.getColumns().addAll(colId, colNome, colData);
    }

    @Override
    public String getTitulo() {
        return "Gerenciamento de Passageiros";
    }

    // Getters para o Controller conseguir capturar o que foi digitado
    public TextField getTxtCPF() {
        return txtCPF;
    }

    public TextField getTxtNome() {
        return txtNome;
    }

    public DatePicker getDpDataNascimento() {
        return dpDataNascimento;
    }
}
