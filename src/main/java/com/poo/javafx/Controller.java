package com.poo.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

public abstract class Controller<T extends Model, V extends View<T>> {
    protected V view;

    protected Scene scene;
    protected ObservableList<T> listaTabela;
    protected Repository<T> repositorio;

    public Controller(Scene scene, V view, Class<T> class1) {
        this.scene = scene;
        this.view = view;
        this.repositorio = new Repository<>(class1);

        this.listaTabela = FXCollections.observableArrayList(repositorio.objetos());
        this.view.getTabela().setItems(this.listaTabela);

        setupActions();
    }

    private void setupActions() {
        this.view.getBtnAdicionar().setOnAction(e -> adicionar());
        this.view.getBtnDeletar().setOnAction(e -> deletar());
        this.view.getBtnAtualizar().setOnAction(e -> atualizar());
    }

    public abstract T camposParaObjeto();

    /**
     * C (Create): Captura os dados da View, cria um objeto e salva na
     * lista/arquivo.
     */
    public void adicionar() {
        T objeto = camposParaObjeto();
        this.repositorio.adicionar(objeto);
        this.ler();
    };

    /**
     * R (Read): Lê os dados do arquivo .dat e atualiza a TableView na tela.
     */
    public void ler() {
        this.listaTabela.setAll(this.repositorio.objetos());
    };

    /**
     * U (Update): Captura o objeto selecionado na tabela, atualiza seus dados e
     * salva.
     */
    public void atualizar() {
        T selecionado = view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            return;
        }

        T objetoAtualizado = camposParaObjeto();
        repositorio.atualizar(selecionado.getID(), objetoAtualizado);
        this.ler();
    }

    /**
     * D (Delete): Remove o objeto selecionado da lista e atualiza o arquivo .dat.
     */
    public void deletar() {
        T selecionado = this.view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            return;
        }
        this.repositorio.remover(selecionado);
        this.ler();
    };
}
