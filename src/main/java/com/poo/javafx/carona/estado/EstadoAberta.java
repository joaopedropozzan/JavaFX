package com.poo.javafx.carona.estado;

public class EstadoAberta implements EstadoCarona {
    @Override
    public String nome() {
        return "Aberta";
    }

    @Override
    public EstadoCarona confirmar() {
        return new EstadoConfirmada();
    }

    @Override
    public EstadoCarona concluir() {
        throw new IllegalStateException("Uma carona Aberta não pode ser concluída.");
    }

    @Override
    public EstadoCarona cancelar() {
        return new EstadoCancelada();
    }
}
