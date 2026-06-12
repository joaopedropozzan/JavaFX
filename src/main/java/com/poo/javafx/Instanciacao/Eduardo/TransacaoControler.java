package com.poo.javafx.Instanciacao.Eduardo;

import com.poo.javafx.CRUDController;

public class TransacaoControler extends CRUDController<TransacaoModel, TransacaoView> {
    public TransacaoControler() {
        super(new TransacaoView(), TransacaoModel.class);
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
