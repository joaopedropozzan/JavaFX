package com.poo.javafx.carona;

import com.poo.javafx.Model;
import com.poo.javafx.carona.estado.EstadoAberta;
import com.poo.javafx.carona.estado.EstadoCarona;
import com.poo.javafx.passageiro.PassageiroModel;
import com.poo.javafx.veiculo.VeiculoModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "caronas", uniqueConstraints = {
        @UniqueConstraint(name = "uk_carona_motorista_origem_destino", columnNames = { "motorista_id", "origem",
                "destino" })
})

public class CaronaModel extends Model<CaronaModel> {
    @ManyToOne(optional = false)
    @JoinColumn(name = "motorista_id", nullable = false)
    private PassageiroModel motorista;

    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private VeiculoModel veiculo;

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

    public CaronaModel(PassageiroModel motorista, VeiculoModel veiculo, String origem, String destino, int vagas) {
        this.motorista = motorista;
        this.veiculo = veiculo;
        this.origem = origem;
        this.destino = destino;
        this.vagas = vagas;
    }

    @Override
    protected void herdarDe(CaronaModel original) {
        if (this.estado instanceof EstadoAberta) {
            this.estado = original.estado;
        }
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

    public PassageiroModel getMotorista() {
        return motorista;
    }

    public VeiculoModel getVeiculo() {
        return veiculo;
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
