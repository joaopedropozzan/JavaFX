package com.poo.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public abstract class CRUDView<T extends Model<T>> extends BorderPane {
    protected TableView<T> tabela;
    protected Button btnAdicionar;
    protected Button btnAtualizar;
    protected Button btnDeletar;
    protected Button btnVoltar;
    protected HBox containerFormulario;

    public CRUDView() {
        this.setPadding(new Insets(20));

        btnVoltar = new Button("⬅ Voltar ao Menu Principal");
        BorderPane.setAlignment(btnVoltar, Pos.TOP_LEFT);
        this.setTop(btnVoltar);

        VBox centroLayout = new VBox(15);
        centroLayout.setAlignment(Pos.CENTER);
        VBox.setVgrow(centroLayout, Priority.ALWAYS);

        containerFormulario = new HBox(10);
        containerFormulario.setAlignment(Pos.CENTER_LEFT);

        btnAdicionar = new Button("Adicionar");
        btnAtualizar = new Button("Atualizar");
        btnDeletar = new Button("Deletar");

        HBox painelBotoes = new HBox(10);
        painelBotoes.setAlignment(Pos.CENTER_RIGHT);
        painelBotoes.getChildren().addAll(btnAdicionar, btnAtualizar, btnDeletar);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox linhaFormBotoes = new HBox();
        linhaFormBotoes.setAlignment(Pos.CENTER);
        linhaFormBotoes.getChildren().addAll(containerFormulario, spacer, painelBotoes);

        tabela = new TableView<>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        VBox.setVgrow(tabela, Priority.ALWAYS);

        centroLayout.getChildren().addAll(linhaFormBotoes, tabela);
        this.setCenter(centroLayout);
        BorderPane.setMargin(centroLayout, new Insets(15, 0, 0, 0));

        configurarColunas();
    }

    /**
     * Metodo abstrato.
     * Cada classe que herdar de ViewGenerica será OBRIGADA a implementar
     * este método para criar suas próprias colunas e vinculá-las ao Model.
     */
    protected abstract void configurarColunas();

    // Método abstrato para definr titulo do stage.
    public abstract String getTitulo();

    // Getters para que o Controller possa capturar os cliques nos botões depois
    public Button getBtnVoltar() {
        return btnVoltar;
    }

    public Button getBtnAdicionar() {
        return btnAdicionar;
    }

    public Button getBtnAtualizar() {
        return btnAtualizar;
    }

    public Button getBtnDeletar() {
        return btnDeletar;
    }

    public TableView<T> getTabela() {
        return tabela;
    }

    public HBox getContainerFormulario() {
        return containerFormulario;
    }
}
