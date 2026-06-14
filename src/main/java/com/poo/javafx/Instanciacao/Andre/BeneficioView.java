package com.poo.javafx.Instanciacao.Andre;

import com.poo.javafx.CRUDView;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class BeneficioView extends CRUDView<BeneficioModel> {
    private TextField nome;
    private TextField tipo;
    private TextField valor;
    private TextField descricao;

    public BeneficioView() {
        super();

        nome = new TextField();
        nome.setPromptText("Nome do Beneficio");

        tipo = new TextField();
        tipo.setPromptText("Tipo");

        valor = new TextField();
        valor.setPromptText("Valor");

        descricao = new TextField();
        descricao.setPromptText("Descricao");

        this.formulario.getChildren().addAll(nome, tipo, valor, descricao);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<BeneficioModel, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));

        TableColumn<BeneficioModel, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTipo()));

        TableColumn<BeneficioModel, Double> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getValor()).asObject());

        TableColumn<BeneficioModel, String> colDescricao = new TableColumn<>("Descricao");
        colDescricao.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescricao()));

        tabela.getColumns().addAll(colNome, colTipo, colValor, colDescricao);
    }

    @Override
    public String getTitulo() {
        return "Gerenciamento de Beneficios";
    }

    public TextField getNome() {
        return nome;
    }

    public TextField getTipo() {
        return tipo;
    }

    public TextField getValor() {
        return valor;
    }

    public TextField getDescricao() {
        return descricao;
    }
}
