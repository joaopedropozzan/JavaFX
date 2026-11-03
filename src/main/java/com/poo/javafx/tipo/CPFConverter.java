package com.poo.javafx.tipo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CPFConverter implements AttributeConverter<CPF, String> {
    @Override
    public String convertToDatabaseColumn(CPF attribute) {
        return attribute != null ? attribute.getValor() : null;
    }

    @Override
    public CPF convertToEntityAttribute(String dbData) {
        return (dbData != null && !dbData.isBlank()) ? new CPF(dbData) : null;
    }
}
