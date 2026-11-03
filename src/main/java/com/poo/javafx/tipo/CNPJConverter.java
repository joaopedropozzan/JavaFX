package com.poo.javafx.tipo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CNPJConverter implements AttributeConverter<CNPJ, String> {
    @Override
    public String convertToDatabaseColumn(CNPJ attribute) {
        return attribute != null ? attribute.getValor() : null;
    }

    @Override
    public CNPJ convertToEntityAttribute(String dbData) {
        return (dbData != null && !dbData.isBlank()) ? new CNPJ(dbData) : null;
    }
}
