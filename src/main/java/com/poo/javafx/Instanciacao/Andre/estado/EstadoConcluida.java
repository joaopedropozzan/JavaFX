package com.poo.javafx.Instanciacao.Andre.estado;

import java.io.Serial;

public class EstadoConcluida implements EstadoCarona {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public String nome() {
        return "Concluída";
    }

    @Override
    public EstadoCarona confirmar() {
        throw new IllegalStateException("Uma carona Concluída não pode ser confirmada.");
    }

    @Override
    public EstadoCarona concluir() {
        throw new IllegalStateException("Uma carona Concluída não pode ser concluída.");
    }

    @Override
    public EstadoCarona cancelar() {
        throw new IllegalStateException("Uma carona Concluída não pode ser cancelada.");
    }
}
