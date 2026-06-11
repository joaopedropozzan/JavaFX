package com.poo.javafx.Instanciacao.JoaoMosson;

import java.io.Serial;
import java.time.LocalDate;

import com.poo.javafx.Model;

public class PassageiroModel extends Model {
    @Serial
    private static final long serialVersionUID = 1L;

    private String cpf;
    private String nome;
    private LocalDate dataNascimento;

    public PassageiroModel(String cpf, String nome, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
