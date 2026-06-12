package com.poo.javafx;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class CRUDController<T extends Model<T>, V extends CRUDView<T>> {
    protected V view;
    protected Scene scene;
    protected ObservableList<T> listaTabela;
    protected Repository<T> repositorio;

    public CRUDController(V view, Class<T> clazz) {
        this.view = view;
        this.repositorio = new Repository<>(clazz);

        this.listaTabela = FXCollections.observableArrayList(repositorio.objetos());
        this.view.getTabela().setItems(this.listaTabela);

        setupActions();
    }

    private void setupActions() {
        this.view.getBtnAdicionar().setOnAction(e -> adicionar());
        this.view.getBtnDeletar().setOnAction(e -> deletar());
        this.view.getBtnAtualizar().setOnAction(e -> atualizar());

        this.view.getTabela().setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                T selecionado = view.getTabela().getSelectionModel().getSelectedItem();
                if (selecionado != null) {
                    ModelParaCampos(selecionado);
                }
            }
        });
    }

    private void mostrarErroValidacao(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Validação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem != null ? mensagem : "Dados inválidos nos campos.");
        alert.showAndWait();
    }

    private void validarCamposPreenchidos() throws Exception {
        for (javafx.scene.Node node : view.getContainerFormulario().getChildren()) {
            boolean vazio = switch (node) {
                case javafx.scene.control.TextField tf -> tf.getText().isBlank();
                case javafx.scene.control.DatePicker dp -> dp.getValue() == null;
                case jfxtras.scene.control.LocalDateTimeTextField ldtf -> ldtf.getLocalDateTime() == null;
                case javafx.scene.control.ComboBox<?> cb -> cb.getValue() == null;
                default -> false;
            };

            if (vazio) {
                throw new IllegalArgumentException("Todos os campos do formulário são obrigatórios.");
            }
        }
    }

    private void limparCampos() {
        for (javafx.scene.Node node : view.getContainerFormulario().getChildren()) {
            if (node instanceof javafx.scene.control.Control control) {
                switch (control) {
                    case javafx.scene.control.TextField tf -> tf.clear();
                    case javafx.scene.control.DatePicker dp -> dp.setValue(null);
                    case jfxtras.scene.control.LocalDateTimeTextField ldtf -> ldtf.setLocalDateTime(null);
                    case javafx.scene.control.ComboBox<?> cb -> cb.setValue(null);
                    default -> {
                    }
                }
            }
        }
    }

    public abstract T CamposParaModel() throws Exception;

    public abstract void ModelParaCampos(T selecionado);

    public CRUDView<T> getView() {
        return view;
    }

    /**
     * C (Create): Captura os dados da View, cria um objeto e salva na
     * lista/arquivo.
     */
    public void adicionar() {
        try {
            validarCamposPreenchidos();
            T objeto = CamposParaModel();
            if (objeto != null) {
                this.repositorio.adicionar(objeto);
                this.ler();
                limparCampos();
            }
        } catch (Exception e) {
            mostrarErroValidacao(e.getMessage());
        }
    };

    /**
     * R (Read): Lê os dados do arquivo .dat e atualiza a TableView na tela.
     */
    public void ler() {
        ArrayList<T> objetosAtuais = this.repositorio.objetos();
        this.listaTabela.setAll(objetosAtuais);
    };

    /**
     * U (Update): Captura o objeto selecionado na tabela, atualiza seus dados e
     * salva.
     */
    public void atualizar() {
        T selecionado = view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Nenhum item selecionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um item na lista para atualizar.");
            alert.showAndWait();
            return;
        }

        try {
            validarCamposPreenchidos();
            T objeto = CamposParaModel();
            if (objeto != null) {
                repositorio.atualizar(selecionado.getID(), objeto);
                this.ler();
                limparCampos();

            }
        } catch (Exception e) {
            mostrarErroValidacao(e.getMessage());
        }

    }

    /**
     * D (Delete): Remove o objeto selecionado da lista e atualiza o arquivo .dat.
     */
    public void deletar() {
        T selecionado = this.view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Nenhum item selecionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um item na lista para excluir.");
            alert.showAndWait();
            return;
        }

        this.repositorio.remover(selecionado);
        this.ler();
    };
}
