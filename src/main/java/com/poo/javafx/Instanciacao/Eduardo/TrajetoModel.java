package com.poo.javafx.Instanciacao.Eduardo;

import java.time.LocalDateTime;

import com.poo.javafx.Model;

public class TrajetoModel extends Model {
    private String origem;
    private String destino;
    private LocalDateTime horarioSaida;

    public TrajetoModel(String origem, String destino, LocalDateTime horarioSaida) {
        this.origem = origem;
        this.destino = destino;
        this.horarioSaida = horarioSaida;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

}
