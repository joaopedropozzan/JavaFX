package com.poo.javafx.Instanciacao.Eduardo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TrajetoModel implements Serializable {
    private String origem;
    private String destino;
    private LocalDateTime horarioSaida;

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public TrajetoModel(String origem, String destino, LocalDateTime horarioSaida) {
        this.origem = origem;
        this.destino = destino;
        this.horarioSaida = horarioSaida;
    }

}
