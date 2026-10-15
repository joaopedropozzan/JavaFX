package com.poo.javafx.Instanciacao.Andre;

import com.poo.javafx.CRUDController;
import com.poo.javafx.validacao.RegraNumeroDecimal;

public class BeneficioController extends CRUDController<BeneficioModel, BeneficioView> {
    public BeneficioController() {
        super(BeneficioModel.class);
        adicionarRegra(new RegraNumeroDecimal(view.getValor(), "Valor", 0.0));
    }

    @Override
    public BeneficioModel camposParaModel() {
        String nome = view.getNome().getText();
        String tipo = view.getTipo().getText();
        String descricao = view.getDescricao().getText();
        double valor = Double.parseDouble(view.getValor().getText().trim().replace(",", "."));

        return new BeneficioModel(nome, tipo, valor, descricao);
    }

    @Override
    public void modelParaCampos(BeneficioModel selecionado) {
        view.getNome().setText(selecionado.getNome());
        view.getTipo().setText(selecionado.getTipo());
        view.getValor().setText(String.valueOf(selecionado.getValor()));
        view.getDescricao().setText(selecionado.getDescricao());
    }

    @Override
    protected BeneficioView criarView() {
        return new BeneficioView();
    }
}
