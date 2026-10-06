package com.poo.javafx;

import java.util.ArrayList;
import java.util.List;

import com.poo.javafx.validacao.RegraComboBox;
import com.poo.javafx.validacao.RegraDatePicker;
import com.poo.javafx.validacao.RegraLocalDateTime;
import com.poo.javafx.validacao.RegraTextField;
import com.poo.javafx.validacao.RegraValidacao;
import com.poo.javafx.validacao.ResultadoValidacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class CRUDController<T extends Model<T>, V extends CRUDView<T>> {
    protected V view;
    protected Scene scene;
    protected ObservableList<T> listaTabela;
    protected Repository<T> repositorio;

    protected final List<RegraValidacao> regrasValidacao = new ArrayList<>();

    public CRUDController(Class<T> clazz) {
        this.view = criarView();
        this.repositorio = new Repository<>(clazz);

        this.listaTabela = FXCollections.observableArrayList(repositorio.objetos());
        this.view.getTabela().setItems(this.listaTabela);

        registrarRegrasPadrao();
        setupActions();
    }

    private void registrarRegrasPadrao() {
        regrasValidacao.add(new RegraTextField());
        regrasValidacao.add(new RegraComboBox());
        regrasValidacao.add(new RegraLocalDateTime());
        regrasValidacao.add(new RegraDatePicker());
    }

    public void adicionarRegra(RegraValidacao regra) {
        this.regrasValidacao.add(0, regra);
    }

    private void setupActions() {
        this.view.getBtnAdicionar().setOnAction(e -> adicionar());
        this.view.getBtnDeletar().setOnAction(e -> deletar());
        this.view.getBtnAtualizar().setOnAction(e -> atualizar());

        this.view.getTabela().setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                T selecionado = view.getTabela().getSelectionModel().getSelectedItem();
                if (selecionado != null) {
                    modelParaCampos(selecionado);
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

    private void limparDestaquesErro() {
        for (Node node : view.getFormulario().getChildren()) {
            node.getStyleClass().remove("field--error");
        }
    }

    private void validarCamposPreenchidos() throws Exception {
        limparDestaquesErro();

        for (Node componente : view.getFormulario().getChildren()) {
            for (RegraValidacao regra : regrasValidacao) {
                if (regra.suporta(componente)) {
                    ResultadoValidacao resultado = regra.executar(componente);

                    if (!resultado.valido()) {
                        componente.getStyleClass().add("field--error");
                        componente.requestFocus();

                        throw new IllegalArgumentException(resultado.mensagemErro());
                    }
                    break;
                }
            }
        }
    }

    private void limparCampos() {
        limparDestaquesErro();

        for (Node componente : view.getFormulario().getChildren()) {
            for (RegraValidacao regra : regrasValidacao) {
                if (regra.suporta(componente)) {
                    regra.limpar(componente);
                    break;
                }
            }
        }
    }

    protected abstract V criarView();

    public abstract T camposParaModel() throws Exception;

    public abstract void modelParaCampos(T selecionado);

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
            T objeto = camposParaModel();
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
            T objeto = camposParaModel();
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
