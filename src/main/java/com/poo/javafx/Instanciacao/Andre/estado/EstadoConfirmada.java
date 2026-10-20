package com.poo.javafx.Instanciacao.Andre.estado;

import java.io.Serial;

public class EstadoConfirmada implements EstadoCarona {
    @Serial
    private static final long serialVersionUID = 1L;

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
