package com.poo.javafx.trajeto;

import java.time.LocalDateTime;

import com.poo.javafx.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "trajetos")
public class TrajetoModel extends Model<TrajetoModel> {
    @Column(nullable = false)
    private String origem;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private LocalDateTime horarioSaida;

    protected TrajetoModel() {
    }

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
