package com.poo.javafx.transacao;

import java.time.LocalDateTime;

import com.poo.javafx.Model;
import com.poo.javafx.passageiro.PassageiroModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacoes")
public class TransacaoModel extends Model<TransacaoModel> {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_origem_id", nullable = false)
    private PassageiroModel userOrigem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_destino_id", nullable = false)
    private PassageiroModel userDestino;

    @Column(nullable = false)
    private double valor;

    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    protected TransacaoModel() {
    }

    public TransacaoModel(PassageiroModel userOrigem, PassageiroModel userDestino, double valor) {
        this.userOrigem = userOrigem;
        this.userDestino = userDestino;
        this.valor = valor;
        this.timestamp = LocalDateTime.now();
    }

    public PassageiroModel getUserOrigem() {
        return userOrigem;
    }

    public PassageiroModel getUserDestino() {
        return userDestino;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
