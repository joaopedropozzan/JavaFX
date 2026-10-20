package com.poo.javafx.Instanciacao.Andre.estado;

import java.io.Serial;

public class EstadoAberta implements EstadoCarona {
    @Serial
    private static final long serialVersionUID = 1L;

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
