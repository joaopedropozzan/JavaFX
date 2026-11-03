package com.poo.javafx.carona;

import com.poo.javafx.Model;
import com.poo.javafx.carona.estado.EstadoAberta;
import com.poo.javafx.carona.estado.EstadoCarona;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "caronas", uniqueConstraints = {
        @UniqueConstraint(name = "uk_carona_motorista_origem_destino", columnNames = { "motorista", "origem",
                "destino" })
})

public class CaronaModel extends Model<CaronaModel> {
    @Column(nullable = false)
    private String motorista;

    @Column(nullable = false)
    private String origem;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private int vagas;

    @Column(nullable = false)
    private EstadoCarona estado = new EstadoAberta();

    protected CaronaModel() {
    }

    public CaronaModel(String motorista, String origem, String destino, int vagas) {
        this.motorista = motorista;
        this.origem = origem;
        this.destino = destino;
        this.vagas = vagas;
    }

    public void confirmar() {
        estado = estado.confirmar();
    }

    public void concluir() {
        estado = estado.concluir();
    }

    public void cancelar() {
        estado = estado.cancelar();
    }

    public String getMotorista() {
        return motorista;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public int getVagas() {
        return vagas;
    }

    public String getStatus() {
        return estado.nome();
    }
}
