package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.CRUDController;
import com.poo.javafx.Types.Placa;
import com.poo.javafx.validacao.RegraNumeroInteiro;
import com.poo.javafx.validacao.RegraTipoDominio;

public class VeiculoController extends CRUDController<VeiculoModel, VeiculoView> {

    public VeiculoController() {
        super(VeiculoModel.class);
        adicionarRegra(new RegraTipoDominio(view.getTxtPlaca(), Placa::new));
        adicionarRegra(new RegraNumeroInteiro(view.getTxtAno(), "Ano", 1900));
    }

    @Override
    public VeiculoModel camposParaModel() {
        return new VeiculoModel(
                view.getTxtPlaca().getText(),
                view.getTxtModelo().getText(),
                Integer.parseInt(view.getTxtAno().getText().trim()));
    }

    @Override
    public void modelParaCampos(VeiculoModel selecionado) {
        view.getTxtPlaca().setText(selecionado.getPlaca().getValor());
        view.getTxtModelo().setText(selecionado.getModelo());
        view.getTxtAno().setText(String.valueOf(selecionado.getAno()));
    }

    @Override
    protected VeiculoView criarView() {
        return new VeiculoView();
    }
}
