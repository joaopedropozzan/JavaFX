package com.poo.javafx.tipo;

public class Placa extends Tipo {

    public Placa(String valor) {
        super(formatar(valor));
    }

    private static String formatar(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Placa não pode ser nula.");
        }
        String limpo = valor.toUpperCase().replaceAll("[^A-Z0-9]", "");
        if (!limpo.matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")) {
            throw new IllegalArgumentException("Placa formato inválido. Use ABC1234 ou ABC1D23.");
        }
        return limpo;
    }
}
