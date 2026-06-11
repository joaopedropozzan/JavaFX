package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.View;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class EmpresaView extends View<EmpresaModel> {
    // Campos que o usuário vai digitar
    private TextField txtCNPJ;
    private TextField txtNome;
    private TextField dpQtaFuncionarios;

    public EmpresaView() {
        super(); // Herda a tabela e os botões

        // Inicializa os campos
        txtCNPJ = new TextField();
        txtCNPJ.setPromptText("ID");

        txtNome = new TextField();
        txtNome.setPromptText("Nome da Empresa");

        dpQtaFuncionarios = new TextField();
        dpQtaFuncionarios.setPromptText("Quantidade de Funcionários");

        // Agrupa tudo numa linha horizontal
        HBox formulario = new HBox(10, txtCNPJ, txtNome, dpQtaFuncionarios);

        // Adiciona o formulário no topo da tela (índice 0 do VBox)
        this.getChildren().add(0, formulario);
    }

    @Override
    protected void configurarColunas() {
        TableColumn<EmpresaModel, Integer> colId = new TableColumn<>("CNPJ");
        colId.setCellValueFactory(new PropertyValueFactory<>("CNPJ")); // Procura por getCNPJ() - OK

        TableColumn<EmpresaModel, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nomeEmpresa")); // AJUSTADO: Procura por
                                                                                // getNomeEmpresa()

        TableColumn<EmpresaModel, Integer> colQtaFunc = new TableColumn<>("Qta. Funcionários");
        colQtaFunc.setCellValueFactory(new PropertyValueFactory<>("qtaFuncionarios")); // AJUSTADO: Procura por
                                                                                       // getQtaFuncionarios()

        tabela.getColumns().addAll(colId, colNome, colQtaFunc);
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
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

    public void setTxtCNPJ(TextField txtId) {
        this.txtCNPJ = txtId;
    }

    public void setTxtNome(TextField txtNome) {
        this.txtNome = txtNome;
    }

    public void setDpQtaFuncionarios(TextField dpQtaFuncionarios) {
        this.dpQtaFuncionarios = dpQtaFuncionarios;
    }
}
