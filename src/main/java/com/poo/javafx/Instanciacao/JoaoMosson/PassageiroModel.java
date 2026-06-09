package com.poo.javafx.Instanciacao.JoaoMosson;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class PassageiroModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private LocalDate dataNascimento; // O PDF exige validação de data!

    public PassageiroModel(int id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
}