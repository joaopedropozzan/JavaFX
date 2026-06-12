package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.CRUDView;
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
