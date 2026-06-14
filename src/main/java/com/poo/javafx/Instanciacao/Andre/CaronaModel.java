package com.poo.javafx.Instanciacao.Andre;

import java.io.Serial;

import com.poo.javafx.Model;

public class CaronaModel extends Model<CaronaModel> {
    @Serial
    private static final long serialVersionUID = 1L;

    private String motorista;
    private String origem;
    private String destino;
    private int vagas;
    private String status;

    public CaronaModel(String motorista, String origem, String destino, int vagas, String status) {
        this.motorista = motorista;
        this.origem = origem;
        this.destino = destino;
        this.vagas = vagas;
        this.status = status;
    }

    @Override
    protected boolean checarColisao(CaronaModel objeto) {
        return this.motorista.equalsIgnoreCase(objeto.getMotorista())
                && this.origem.equalsIgnoreCase(objeto.getOrigem())
                && this.destino.equalsIgnoreCase(objeto.getDestino());
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
        return status;
    }
}
