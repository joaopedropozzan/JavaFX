package com.poo.javafx.tipo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PlacaConverter implements AttributeConverter<Placa, String> {
    @Override
    public String convertToDatabaseColumn(Placa attribute) {
        return attribute != null ? attribute.getValor() : null;
    }

    @Override
    public Placa convertToEntityAttribute(String dbData) {
        return (dbData != null && !dbData.isBlank()) ? new Placa(dbData) : null;
    }
}
