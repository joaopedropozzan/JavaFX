package com.poo.javafx.Instanciacao.Andre;

import java.io.Serial;

import com.poo.javafx.Instanciacao.Andre.estado.EstadoAberta;
import com.poo.javafx.Instanciacao.Andre.estado.EstadoCarona;
import com.poo.javafx.Model;

public class CaronaModel extends Model<CaronaModel> {
    @Serial
    private static final long serialVersionUID = 2L;

    private String motorista;
    private String origem;
    private String destino;
    private int vagas;
    private EstadoCarona estado = new EstadoAberta();

    public CaronaModel(String motorista, String origem, String destino, int vagas) {
        this.motorista = motorista;
        this.origem = origem;
        this.destino = destino;
        this.vagas = vagas;
    }

    @Override
    protected boolean checarColisao(CaronaModel objeto) {
        return this.motorista.equalsIgnoreCase(objeto.getMotorista())
                && this.origem.equalsIgnoreCase(objeto.getOrigem())
                && this.destino.equalsIgnoreCase(objeto.getDestino());
    }

    /**
     * Mantém o estado da carona original ao atualizar pelo formulário. Se este objeto já
     * saiu do estado inicial (uma transição foi aplicada antes de persistir), o novo
     * estado é preservado, pois nenhuma transição volta para Aberta.
     */
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
