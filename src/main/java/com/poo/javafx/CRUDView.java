package com.poo.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class CRUDView<T> extends VBox {
    protected TableView<T> tabela;
    protected Button btnAdicionar;
    protected Button btnAtualizar;
    protected Button btnDeletar;
    protected Button btnVoltar;
    protected HBox containerFormulario;

    public CRUDView() {
        // 1. Configuração do Layout Principal (VBox)
        this.setPadding(new Insets(20)); // Espaçamento das bordas
        this.setSpacing(15); // Espaçamento entre a tabela e os botões
        this.setAlignment(Pos.CENTER);

        btnVoltar = new Button("⬅ Voltar ao Menu Principal");
        HBox painelVoltar = new HBox(btnVoltar);
        painelVoltar.setAlignment(Pos.TOP_LEFT);

        containerFormulario = new HBox(10);
        containerFormulario.setAlignment(Pos.CENTER);

        // 2. Inicializando a Tabela
        tabela = new TableView<>();
        // Faz as colunas ocuparem todo o espaco disponível da tabela
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // 3. Inicializando os Botões Genéricos
        btnAdicionar = new Button("Adicionar");
        btnAtualizar = new Button("Atualizar");
        btnDeletar = new Button("Deletar");

        // 4. Agrupando os botões em um HBox (linha horizontal)
        HBox painelBotoes = new HBox(10); // 10 é o espaçamento entre os botões
        painelBotoes.setAlignment(Pos.CENTER);
        painelBotoes.getChildren().addAll(btnAdicionar, btnAtualizar, btnDeletar);

        // 5. Adicionando os elementos à View (que é um VBox)
        this.getChildren().addAll(painelVoltar, containerFormulario, tabela, painelBotoes);

        // 6. Chamada para o metodo abstrato
        // Assim que a View mãe for criada, ela obriga a filha a montar as colunas
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
