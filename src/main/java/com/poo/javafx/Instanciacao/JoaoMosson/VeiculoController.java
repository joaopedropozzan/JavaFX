package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.CRUDController;

public class VeiculoController extends CRUDController<VeiculoModel, VeiculoView> {

    public VeiculoController() {
        super(new VeiculoView(), VeiculoModel.class);
    }

    @Override
    public VeiculoModel CamposParaModel() {
        int ano;
        try {
            ano = Integer.parseInt(view.getTxtAno().getText());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Por favor, digite apenas números no campo Ano.");
        }

        String placa = view.getTxtPlaca().getText();
        String modelo = view.getTxtModelo().getText();

        return new VeiculoModel(placa, modelo, ano);
    }

    @Override
    public void ModelParaCampos(VeiculoModel selecionado) {
        view.getTxtPlaca().setText(selecionado.getPlaca().getValor());
        view.getTxtModelo().setText(selecionado.getModelo());
        view.getTxtAno().setText(String.valueOf(selecionado.getAno()));
    }
}
