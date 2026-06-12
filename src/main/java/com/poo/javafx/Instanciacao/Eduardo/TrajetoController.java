package com.poo.javafx.Instanciacao.Eduardo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.poo.javafx.CRUDController;

public class TrajetoController extends CRUDController<TrajetoModel, TrajetoView> {

    public TrajetoController() {
        super(new TrajetoView(), TrajetoModel.class);
    }

    @Override
    public TrajetoModel CamposParaModel() {
        String origem = view.getOrigem().getText();
        String destino = view.getDestino().getText();
        String horarioSaidaTxt;
        LocalDateTime horarioSaida;
        try {
            horarioSaidaTxt = view.getHorarioSaida().getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy, h:mm:ss a", Locale.US);
            horarioSaida = LocalDateTime.parse(horarioSaidaTxt, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("digita certo ae namoral");
            return null;
        }

        TrajetoModel trajeto = new TrajetoModel(origem, destino, horarioSaida);

        view.getOrigem().clear();
        view.getDestino().clear();
        view.getHorarioSaida().setText(null);
        return trajeto;
    }

    @Override
    public void ModelParaCampos(TrajetoModel selecionado) {
        view.getOrigem().setText(selecionado.getOrigem());
        view.getDestino().setText(selecionado.getDestino());
        view.getHorarioSaida().setLocalDateTime(selecionado.getHorarioSaida());
    }

}
