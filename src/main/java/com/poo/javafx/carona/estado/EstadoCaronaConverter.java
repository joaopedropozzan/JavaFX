package com.poo.javafx.carona.estado;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoCaronaConverter implements AttributeConverter<EstadoCarona, String> {

    @Override
    public String convertToDatabaseColumn(EstadoCarona estado) {
        return estado != null ? estado.nome() : "Aberta";
    }

    @Override
    public EstadoCarona convertToEntityAttribute(String nome) {
        if (nome == null)
            return new EstadoAberta();

        return switch (nome) {
            case "Confirmada" -> new EstadoConfirmada();
            case "Concluída" -> new EstadoConcluida();
            case "Cancelada" -> new EstadoCancelada();
            default -> new EstadoAberta();
        };
    }
}
