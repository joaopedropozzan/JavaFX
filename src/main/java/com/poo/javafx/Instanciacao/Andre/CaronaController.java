package com.poo.javafx.Instanciacao.Andre;

import java.util.function.Consumer;

import com.poo.javafx.CRUDController;
import com.poo.javafx.validacao.RegraNumeroInteiro;

import javafx.scene.control.Button;

public class CaronaController extends CRUDController<CaronaModel, CaronaView> {
    public CaronaController() {
        super(CaronaModel.class);
        adicionarRegra(new RegraNumeroInteiro(view.getVagas(), "Vagas", 1));

        ligarTransicao(view.getBtnConfirmar(), CaronaModel::confirmar, "confirmar");
        ligarTransicao(view.getBtnConcluir(), CaronaModel::concluir, "concluir");
        ligarTransicao(view.getBtnCancelar(), CaronaModel::cancelar, "cancelar");
    }

    private void ligarTransicao(Button botao, Consumer<CaronaModel> transicao, String acao) {
        botao.setOnAction(e -> {
            CaronaModel selecionado = view.getTabela().getSelectionModel().getSelectedItem();
            if (selecionado == null) {
                mostrarAvisoSemSelecao(acao);
                return;
            }

            try {
                transicao.accept(selecionado);
                repositorio.atualizar(selecionado.getID(), selecionado);
            } catch (IllegalStateException ex) {
                mostrarErroValidacao(ex.getMessage());
            }
            ler();
        });
    }

    @Override
    public CaronaModel camposParaModel() {
        return new CaronaModel(
                view.getMotorista().getText(),
                view.getOrigem().getText(),
                view.getDestino().getText(),
                Integer.parseInt(view.getVagas().getText().trim()));
    }

    @Override
    public void modelParaCampos(CaronaModel selecionado) {
        view.getMotorista().setText(selecionado.getMotorista());
        view.getOrigem().setText(selecionado.getOrigem());
        view.getDestino().setText(selecionado.getDestino());
        view.getVagas().setText(String.valueOf(selecionado.getVagas()));
    }

    @Override
    protected CaronaView criarView() {
        return new CaronaView();
    }
}
