package com.poo.javafx.Instanciacao.Andre;

import com.poo.javafx.CRUDController;
import com.poo.javafx.validacao.RegraNumeroInteiro;

public class CaronaController extends CRUDController<CaronaModel, CaronaView> {
    public CaronaController() {
        super(CaronaModel.class);
        adicionarRegra(new RegraNumeroInteiro(view.getVagas(), "Vagas", 1));
    }

    @Override
    public CaronaModel camposParaModel() {
        return new CaronaModel(
                view.getMotorista().getText(),
                view.getOrigem().getText(),
                view.getDestino().getText(),
                Integer.parseInt(view.getVagas().getText().trim()),
                view.getStatus().getValue());
    }

    @Override
    public void modelParaCampos(CaronaModel selecionado) {
        view.getMotorista().setText(selecionado.getMotorista());
        view.getOrigem().setText(selecionado.getOrigem());
        view.getDestino().setText(selecionado.getDestino());
        view.getVagas().setText(String.valueOf(selecionado.getVagas()));
        view.getStatus().setValue(selecionado.getStatus());
    }

    @Override
    protected CaronaView criarView() {
        return new CaronaView();
    }
}
