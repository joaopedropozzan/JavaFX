package com.poo.javafx.transacao;

import java.util.ArrayList;

import com.poo.javafx.CRUDController;
import com.poo.javafx.Repository;
import com.poo.javafx.passageiro.PassageiroModel;
import com.poo.javafx.validacao.RegraNumeroDecimal;

public class TransacaoController extends CRUDController<TransacaoModel, TransacaoView> {
    public TransacaoController() {
        super(TransacaoModel.class);
        adicionarRegra(new RegraNumeroDecimal(view.getValor(), "Valor", 0.01));
        ArrayList<PassageiroModel> users = new Repository<>(PassageiroModel.class).objetos();
        view.getUserOrigem().getItems().addAll(users);
        view.getUserDestino().getItems().addAll(users);
    }

    @Override
    public TransacaoModel camposParaModel() throws Exception {
        PassageiroModel origem = view.getUserOrigem().getValue();
        PassageiroModel destino = view.getUserDestino().getValue();
        double valor = Double.parseDouble(view.getValor().getText().trim().replace(",", "."));

        if (origem != null && destino != null && origem.getID() == destino.getID()) {
            throw new IllegalArgumentException("Origem e destino devem ser diferentes.");
        }

        return new TransacaoModel(origem, destino, valor);
    }

    @Override
    public void modelParaCampos(TransacaoModel selecionado) {
        view.getUserOrigem().setValue(selecionado.getUserOrigem());
        view.getUserDestino().setValue(selecionado.getUserDestino());
        view.getValor().setText((String.valueOf(selecionado.getValor())));
    }

    @Override
    protected TransacaoView criarView() {
        return new TransacaoView();
    }
}
