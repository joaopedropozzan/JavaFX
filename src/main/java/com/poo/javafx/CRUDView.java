package com.poo.javafx;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public abstract class CRUDView<T extends Model<T>> extends BorderPane {
    protected TableView<T> tabela;
    protected Button btnAdicionar;
    protected Button btnAtualizar;
    protected Button btnDeletar;
    protected Button btnVoltar;
    protected HBox formulario;

    public CRUDView() {
        this.getStyleClass().add("crud");

        SVGPath voltarIcon = new SVGPath();
        voltarIcon.setContent(
                "m7.825 13l4.9 4.9q.3.3.288.7t-.313.7q-.3.275-.7.288t-.7-.288l-6.6-6.6q-.15-.15-.213-.325T4.426 12t.063-.375t.212-.325l6.6-6.6q.275-.275.688-.275t.712.275q.3.3.3.713t-.3.712L7.825 11H19q.425 0 .713.288T20 12t-.288.713T19 13z");
        voltarIcon.getStyleClass().add("crud-exit__icon");

        btnVoltar = new Button("Voltar ao Menu Principal");
        btnVoltar.setGraphic(voltarIcon);
        btnVoltar.getStyleClass().add("crud__exit");
        this.setTop(btnVoltar);

        VBox main = new VBox();
        main.getStyleClass().add("crud__main");
        VBox.setVgrow(main, Priority.ALWAYS);

        formulario = new HBox();
        formulario.getStyleClass().add("crud__form");

        btnAdicionar = new Button("Adicionar");

        btnAtualizar = new Button("Atualizar");
        btnAtualizar.getStyleClass().add("button--orange");

        btnDeletar = new Button("Deletar");
        btnDeletar.getStyleClass().add("button--red");

        HBox actions = new HBox();
        actions.getStyleClass().add("crud-toolbar__actions");
        actions.getChildren().addAll(btnAdicionar, btnAtualizar, btnDeletar);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("crud__toolbar");
        toolbar.getChildren().addAll(formulario, spacer, actions);

        tabela = new TableView<>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        VBox.setVgrow(tabela, Priority.ALWAYS);

        main.getChildren().addAll(toolbar, tabela);
        this.setCenter(main);

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

    public HBox getFormulario() {
        return formulario;
    }
}
