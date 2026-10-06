package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.CRUDController;
import com.poo.javafx.validacao.RegraCartaoCredito;
import com.poo.javafx.validacao.RegraNumeroDecimal;

public class CarteiraController extends CRUDController<CarteiraModel, CarteiraView> {

    public CarteiraController() {
        super(CarteiraModel.class);
        adicionarRegra(new RegraNumeroDecimal(view.getTxtSaldo(), "Saldo"));
        adicionarRegra(new RegraCartaoCredito(view.getTxtNumeroCartao()));
    }

    @Override
    public CarteiraModel camposParaModel() {
        return new CarteiraModel(
                Double.parseDouble(view.getTxtSaldo().getText().trim().replace(",", ".")),
                view.getTxtNumeroCartao().getText().replaceAll("\\s+", ""),
                view.getTxtTitularConta().getText().trim());
    }

    @Override
    public void modelParaCampos(CarteiraModel selecionado) {
        view.getTxtSaldo().setText(String.valueOf(selecionado.getSaldo()));
        view.getTxtNumeroCartao().setText(selecionado.getNumeroCartao());
        view.getTxtTitularConta().setText(selecionado.getTitularConta());
    }

    @Override
    protected CarteiraView criarView() {
        return new CarteiraView();
    }
}
