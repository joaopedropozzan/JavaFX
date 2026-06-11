package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroModel;
import com.poo.javafx.ViewGenerica;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.time.LocalDate;


public class EmpresaView extends ViewGenerica<EmpresaModel> {
    // Campos que o usuário vai digitar
    private TextField txtId;
    private TextField txtNome;
    private TextField dpQtaFuncionarios;

    public EmpresaView() {
        super(); // Herda a tabela e os botões

        // Inicializa os campos
        txtId = new TextField();
        txtId.setPromptText("ID");

        txtNome = new TextField();
        txtNome.setPromptText("Nome da Empresa");

        dpQtaFuncionarios = new TextField();
        dpQtaFuncionarios.setPromptText("Quantidade de Funcionários");

        // Agrupa tudo numa linha horizontal
        HBox formulario = new HBox(10, txtId, txtNome, dpQtaFuncionarios);

        // Adiciona o formulário no topo da tela (índice 0 do VBox)
        this.getChildren().add(0, formulario);
    }


    @Override
    protected void configurarColunas() {
        TableColumn<EmpresaModel, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id")); // Procura por getId() - OK

        TableColumn<EmpresaModel, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nomeEmpresa")); // AJUSTADO: Procura por getNomeEmpresa()

        TableColumn<EmpresaModel, Integer> colQtaFunc = new TableColumn<>("Qta. Funcionários");
        colQtaFunc.setCellValueFactory(new PropertyValueFactory<>("qtaFuncionarios")); // AJUSTADO: Procura por getQtaFuncionarios()

        tabela.getColumns().addAll(colId, colNome, colQtaFunc);
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    public TextField getTxtId() {
        return txtId;
    }

    public TextField getTxtNome() {
        return txtNome;
    }

    public TextField getDpQtaFuncionarios() {
        return dpQtaFuncionarios;
    }

    public void setTxtId(TextField txtId) {
        this.txtId = txtId;
    }

    public void setTxtNome(TextField txtNome) {
        this.txtNome = txtNome;
    }

    public void setDpQtaFuncionarios(TextField dpQtaFuncionarios) {
        this.dpQtaFuncionarios = dpQtaFuncionarios;
    }
}
