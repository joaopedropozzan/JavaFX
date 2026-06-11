package com.poo.javafx.Instanciacao.Eduardo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.poo.javafx.Controller;

import javafx.scene.Scene;

public class TrajetoController extends Controller<TrajetoModel, TrajetoView> {

    public TrajetoController(Scene scene, TrajetoView view) {
        super(scene, view, TrajetoModel.class);
    }

    @Override
    public TrajetoModel camposParaObjeto() {
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

}
