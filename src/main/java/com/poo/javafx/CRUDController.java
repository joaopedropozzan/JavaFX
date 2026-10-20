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

/**
 * Padrão Template Method: o CRUD (adicionar, atualizar, deletar) é fixo (final); os passos
 * abstratos camposParaModel e modelParaCampos são fornecidos pelas subclasses.
 * criarView é o Factory Method que cria a view de cada tela.
 */
public abstract class CRUDController<T extends Model<T>, V extends CRUDView<T>> {
    protected V view;
    protected Scene scene;
    protected ObservableList<T> listaTabela;
    protected Repository<T> repositorio;
    /** Item carregado no formulário pelo duplo clique; é o alvo de atualizar(). */
    private T itemCarregado;

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
                    itemCarregado = selecionado;
                    modelParaCampos(selecionado);
                }
            }
        });
    }

    protected void mostrarErroValidacao(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Validação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem != null ? mensagem : "Dados inválidos nos campos.");
        alert.showAndWait();
    }

    protected void mostrarAvisoSemSelecao(String acao) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Nenhum item selecionado");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, selecione um item na lista para " + acao + ".");
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
        itemCarregado = null;
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

    /**
     * Padrão Factory Method: o CRUDController é o Creator e cada controller concreto
     * cria a CRUDView concreta da sua tela. Atenção: é chamado dentro do construtor da
     * superclasse, então a implementação não pode depender de campos do próprio controller.
     */
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
    public final void adicionar() {
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
     * U (Update): Captura o objeto carregado pelo duplo clique (ou, na falta dele, o selecionado na tabela), atualiza seus dados e
     * salva.
     */
    public final void atualizar() {
        T selecionado = itemCarregado != null ? itemCarregado
                : view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAvisoSemSelecao("atualizar");
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
    public final void deletar() {
        T selecionado = this.view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAvisoSemSelecao("excluir");
            return;
        }

        try {
            this.repositorio.remover(selecionado);
            if (itemCarregado != null && itemCarregado.getID() == selecionado.getID()) {
                itemCarregado = null;
            }
            this.ler();
        } catch (Exception e) {
            mostrarErroValidacao(e.getMessage());
        }
    };
}
