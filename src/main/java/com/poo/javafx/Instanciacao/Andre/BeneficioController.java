package com.poo.javafx.Instanciacao.Andre;

import com.poo.javafx.CRUDController;

public class BeneficioController extends CRUDController<BeneficioModel, BeneficioView> {
    public BeneficioController() {
        super(new BeneficioView(), BeneficioModel.class);
    }

    @Override
    public BeneficioModel camposParaModel() {
        String nome = view.getNome().getText();
        String tipo = view.getTipo().getText();
        String descricao = view.getDescricao().getText();
        double valor;

        try {
            valor = Double.parseDouble(view.getValor().getText());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Valor deve ser um numero decimal valido.");
        }

        return new BeneficioModel(nome, tipo, valor, descricao);
    }

    @Override
    public void modelParaCampos(BeneficioModel selecionado) {
        view.getNome().setText(selecionado.getNome());
        view.getTipo().setText(selecionado.getTipo());
        view.getValor().setText(String.valueOf(selecionado.getValor()));
        view.getDescricao().setText(selecionado.getDescricao());
    }
}
