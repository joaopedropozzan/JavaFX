package com.poo.javafx.Instanciacao.Andre.estado;

import java.io.Serial;

public class EstadoCancelada implements EstadoCarona {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public String nome() {
        return "Cancelada";
    }

    @Override
    public EstadoCarona confirmar() {
        throw new IllegalStateException("Uma carona Cancelada não pode ser confirmada.");
    }

    @Override
    public EstadoCarona concluir() {
        throw new IllegalStateException("Uma carona Cancelada não pode ser concluída.");
    }

    @Override
    public EstadoCarona cancelar() {
        throw new IllegalStateException("Uma carona Cancelada não pode ser cancelada.");
    }
}
