package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.CRUDView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmpresaView extends CRUDView<EmpresaModel> {
    // Campos que o usuário vai digitar
    private TextField txtCNPJ;
    private TextField txtNome;
    private TextField dpQtaFuncionarios;

    public EmpresaView() {
        super(); // Herda a tabela e os botões

        // Inicializa os campos
        txtCNPJ = new TextField();
        txtCNPJ.setPromptText("CNPJ");

        txtNome = new TextField();
        txtNome.setPromptText("Nome da Empresa");

        dpQtaFuncionarios = new TextField();
        dpQtaFuncionarios.setPromptText("Quantidade de Funcionários");

        this.containerFormulario.getChildren().addAll(txtCNPJ, txtNome, dpQtaFuncionarios);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<EmpresaModel, String> colId = new TableColumn<>("CNPJ");
        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCNPJ().getValor()));

        TableColumn<EmpresaModel, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomeEmpresa()));

        TableColumn<EmpresaModel, Integer> colQtaFunc = new TableColumn<>("Qta. Funcionários");
        colQtaFunc.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getQtaFuncionarios()));

        tabela.getColumns().addAll(colId, colNome, colQtaFunc);
    }

    @Override
    public String getTitulo() {
        return "Gerenciamento de Empresas";
    }

    public TextField getTxtCNPJ() {
        return txtCNPJ;
    }

    public TextField getTxtNome() {
        return txtNome;
    }

    public TextField getDpQtaFuncionarios() {
        return dpQtaFuncionarios;
    }
}
