package com.poo.javafx.carona.estado;

public class EstadoConfirmada implements EstadoCarona {
    @Override
    public String nome() {
        return "Confirmada";
    }

    @Override
    public EstadoCarona confirmar() {
        throw new IllegalStateException("Uma carona Confirmada não pode ser confirmada.");
    }

    @Override
    public EstadoCarona concluir() {
        return new EstadoConcluida();
    }

    @Override
    public EstadoCarona cancelar() {
        return new EstadoCancelada();
    }
}
