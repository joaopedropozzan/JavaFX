package com.poo.javafx.Instanciacao.Eduardo;

import java.util.ArrayList;

import com.poo.javafx.CRUDController;
import com.poo.javafx.Repository;
import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroModel;

public class TransacaoControler extends CRUDController<TransacaoModel, TransacaoView> {
    public TransacaoControler() {
        super(new TransacaoView(), TransacaoModel.class);
        ArrayList<PassageiroModel> users = new Repository<>(PassageiroModel.class).objetos();
        view.getUserOrigem().getItems().addAll(users);
        view.getUserDestino().getItems().addAll(users);
    }

    @Override
    public TransacaoModel CamposParaModel() throws Exception {
        int userOrigemID = view.getUserOrigem().getValue().getID();
        int userDestinoID = view.getUserDestino().getValue().getID();
        double valor;
        try {
            valor = Double.parseDouble(view.getValor().getText());
        } catch (Exception e) {
            throw new IllegalArgumentException("Valor deve ser um número decimal válido");
        }

        return new TransacaoModel(userOrigemID, userDestinoID, valor);
    }

    @Override
    public void ModelParaCampos(TransacaoModel selecionado) {
        view.getUserOrigem().setValue(selecionado.getUserOrigem());
        view.getUserDestino().setValue(selecionado.getUserDestino());
        view.getValor().setText((String.valueOf(selecionado.getValor())));
    }
}
