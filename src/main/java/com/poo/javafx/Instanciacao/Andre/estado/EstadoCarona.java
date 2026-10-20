package com.poo.javafx.Instanciacao.Andre.estado;

import java.io.Serializable;

/**
 * Padrão State: o comportamento do status da carona muda conforme o estado atual.
 * Context = CaronaModel, State = EstadoCarona, ConcreteStates = EstadoAberta,
 * EstadoConfirmada, EstadoConcluida e EstadoCancelada. Cada transição devolve o próximo
 * estado ou lança IllegalStateException se não for permitida.
 */
public interface EstadoCarona extends Serializable {
    String nome();

    EstadoCarona confirmar();

    EstadoCarona concluir();

    EstadoCarona cancelar();
}
