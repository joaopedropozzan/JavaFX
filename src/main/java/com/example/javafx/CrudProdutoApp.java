package com.example.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.Serializable;

public class CrudProdutoApp extends Application{

    // Nossa lista reativa. O coração do nosso CRUD.
    private ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();
    @Override
    public void start(Stage palcoPrincipal) {
        palcoPrincipal.setTitle("Sistema de Gestão - CRUD Produtos");

        // 1. CRIANDO A TABELA (Read)
        TableView<Produto> tabela = new TableView<>();
        tabela.setItems(listaProdutos);

        // Configurando as 3 colunas
        TableColumn<Produto, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id")); // O nome tem que ser igual ao atributo da classe

        TableColumn<Produto, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Produto, Double> colPreco = new TableColumn<>("Preço");
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        tabela.getColumns().addAll(colId, colNome, colPreco);

        // 2. CRIANDO O FORMULÁRIO DE ENTRADA
        TextField txtId = new TextField();
        txtId.setPromptText("ID");

        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome do Produto");

        TextField txtPreco = new TextField();
        txtPreco.setPromptText("Preço");

        HBox formBox = new HBox(10); // 10 é o espaçamento entre os itens
        formBox.getChildren().addAll(txtId, txtNome, txtPreco);

        // 3. CRIANDO OS BOTÕES E SUAS AÇÕES (Create, Update, Delete)
        Button btnAdicionar = new Button("Adicionar");
        Button btnAtualizar = new Button("Atualizar Selecionado");
        Button btnDeletar = new Button("Deletar Selecionado");

        // Ação de Criar (Create)
        btnAdicionar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nome = txtNome.getText();
                double preco = Double.parseDouble(txtPreco.getText());

                listaProdutos.add(new Produto(id, nome, preco));
                limparCampos(txtId, txtNome, txtPreco);
            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro", "Por favor, insira números válidos para ID e Preço.");
            }
        });

        // Ação de Deletar (Delete)
        btnDeletar.setOnAction(e -> {
            Produto produtoSelecionado = tabela.getSelectionModel().getSelectedItem();
            if (produtoSelecionado != null) {
                listaProdutos.remove(produtoSelecionado);
            } else {
                mostrarAlerta("Aviso", "Selecione um produto na tabela para deletar.");
            }
        });

        // Ação de Atualizar (Update)
        btnAtualizar.setOnAction(e -> {
            Produto produtoSelecionado = tabela.getSelectionModel().getSelectedItem();
            if (produtoSelecionado != null) {
                try {
                    produtoSelecionado.setId(Integer.parseInt(txtId.getText()));
                    produtoSelecionado.setNome(txtNome.getText());
                    produtoSelecionado.setPreco(Double.parseDouble(txtPreco.getText()));

                    tabela.refresh(); // Força a tabela a redesenhar com os novos dados
                    limparCampos(txtId, txtNome, txtPreco);
                } catch (NumberFormatException ex) {
                    mostrarAlerta("Erro", "Valores inválidos!");
                }
            }
        });

        // Preencher os campos do formulário ao clicar em uma linha da tabela
        tabela.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                txtId.setText(String.valueOf(novo.getId()));
                txtNome.setText(novo.getNome());
                txtPreco.setText(String.valueOf(novo.getPreco()));
            }
        });

        HBox botoesBox = new HBox(10);
        botoesBox.getChildren().addAll(btnAdicionar, btnAtualizar, btnDeletar);

        // 4. MONTANDO O LAYOUT FINAL
        VBox layoutPrincipal = new VBox(15); // Espaçamento vertical de 15px
        layoutPrincipal.setPadding(new Insets(20)); // Margem das bordas
        layoutPrincipal.getChildren().addAll(tabela, formBox, botoesBox);

        // 5. EXIBINDO A TELA
        Scene cena = new Scene(layoutPrincipal, 600, 450);
        palcoPrincipal.setScene(cena);
        palcoPrincipal.show();
    }

    // Métodos Auxiliares
    private void limparCampos(TextField... campos) {
        for (TextField campo : campos) {
            campo.clear();
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}