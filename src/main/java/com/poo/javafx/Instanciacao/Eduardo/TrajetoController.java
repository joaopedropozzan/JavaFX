package com.poo.javafx.Instanciacao.Eduardo;

import java.time.LocalDateTime;

import com.poo.javafx.CRUDController;

public class TrajetoController extends CRUDController<TrajetoModel, TrajetoView> {

    public TrajetoController() {
        super(new TrajetoView(), TrajetoModel.class);
    }

    @Override
    public TrajetoModel camposParaModel() {
        String origem = view.getOrigem().getText();
        String destino = view.getDestino().getText();
        LocalDateTime horarioSaida = view.getHorarioSaida().getDisplayedLocalDateTime();

        return new TrajetoModel(origem, destino, horarioSaida);
    }

    @Override
    public void modelParaCampos(TrajetoModel selecionado) {
        view.getOrigem().setText(selecionado.getOrigem());
        view.getDestino().setText(selecionado.getDestino());
        view.getHorarioSaida().setLocalDateTime(selecionado.getHorarioSaida());
    }

}
