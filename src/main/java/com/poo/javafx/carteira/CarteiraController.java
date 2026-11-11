package com.poo.javafx.carteira;

import com.poo.javafx.CRUDController;
import com.poo.javafx.Repository;
import com.poo.javafx.passageiro.PassageiroModel;
import com.poo.javafx.validacao.RegraCartaoCredito;
import com.poo.javafx.validacao.RegraNumeroDecimal;

public class CarteiraController extends CRUDController<CarteiraModel, CarteiraView> {

    public CarteiraController() {
        super(CarteiraModel.class);
        adicionarRegra(new RegraNumeroDecimal(view.getTxtSaldo(), "Saldo", 0.0));
        adicionarRegra(new RegraCartaoCredito(view.getTxtNumeroCartao()));
        view.getCbTitular().getItems().addAll(new Repository<>(PassageiroModel.class).objetos());
    }

    @Override
    public CarteiraModel camposParaModel() {
        return new CarteiraModel(
                Double.parseDouble(view.getTxtSaldo().getText().trim().replace(",", ".")),
                view.getTxtNumeroCartao().getText().replaceAll("\\s+", ""),
                view.getCbTitular().getValue());
    }

    @Override
    public void modelParaCampos(CarteiraModel selecionado) {
        view.getTxtSaldo().setText(String.valueOf(selecionado.getSaldo()));
        view.getTxtNumeroCartao().setText(selecionado.getNumeroCartao());
        view.getCbTitular().setValue(selecionado.getTitular());
    }

    @Override
    protected CarteiraView criarView() {
        return new CarteiraView();
    }
}
