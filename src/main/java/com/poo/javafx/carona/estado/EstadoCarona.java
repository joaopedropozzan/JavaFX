package com.poo.javafx.carona.estado;

/**
 * Padrão State: o comportamento do status da carona muda conforme o estado
 * atual.
 * Context = CaronaModel, State = EstadoCarona, ConcreteStates = EstadoAberta,
 * EstadoConfirmada, EstadoConcluida e EstadoCancelada. Cada transição devolve o
 * próximo
 * estado ou lança IllegalStateException se não for permitida.
 */
public interface EstadoCarona {
    String nome();

    EstadoCarona confirmar();

    EstadoCarona concluir();

    EstadoCarona cancelar();
}
