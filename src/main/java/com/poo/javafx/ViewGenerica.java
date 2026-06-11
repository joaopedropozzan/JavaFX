package com.poo.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class ViewGenerica<T> extends VBox {
    protected TableView<T> tabela;
    protected Button btnAdicionar;
    protected Button btnAtualizar;
    protected Button btnDeletar;

    public ViewGenerica() {
        // 1. Configuração do Layout Principal (VBox)
        this.setPadding(new Insets(20)); // Espaçamento das bordas
        this.setSpacing(15); // Espaçamento entre a tabela e os botões
        this.setAlignment(Pos.CENTER);

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
        this.getChildren().addAll(tabela, painelBotoes);

        // 6. Chamada para o metodo abstrato
        // Assim que a View mãe for criada, ela obriga a filha a montar as colunas
        configurarColunas();
    }

    /**
     * Metodo abstrato.
     * Cada classe que herdar de ViewGenerica será OBRIGADA a implementar
     * este método para criar suas próprias 3 colunas e vinculá-las ao Model.
     */
    protected abstract void configurarColunas();

    // Getters para que o Controller possa capturar os cliques nos botões depois
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
}
