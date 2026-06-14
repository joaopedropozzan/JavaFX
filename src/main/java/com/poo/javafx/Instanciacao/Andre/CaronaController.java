package com.poo.javafx.Instanciacao.Andre;

import com.poo.javafx.CRUDController;

public class CaronaController extends CRUDController<CaronaModel, CaronaView> {
    public CaronaController() {
        super(new CaronaView(), CaronaModel.class);
    }

    @Override
    public CaronaModel camposParaModel() {
        String motorista = view.getMotorista().getText();
        String origem = view.getOrigem().getText();
        String destino = view.getDestino().getText();
        int vagas;
        String status = view.getStatus().getValue();

        try {
            vagas = Integer.parseInt(view.getVagas().getText());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Vagas deve ser um numero inteiro valido.");
        }

        if (vagas <= 0) {
            throw new IllegalArgumentException("Vagas deve ser maior que zero.");
        }

        return new CaronaModel(motorista, origem, destino, vagas, status);
    }

    @Override
    public void modelParaCampos(CaronaModel selecionado) {
        view.getMotorista().setText(selecionado.getMotorista());
        view.getOrigem().setText(selecionado.getOrigem());
        view.getDestino().setText(selecionado.getDestino());
        view.getVagas().setText(String.valueOf(selecionado.getVagas()));
        view.getStatus().setValue(selecionado.getStatus());
    }
}
