package com.poo.javafx.Instanciacao.JoaoMosson;

import java.io.Serial;
import java.time.LocalDate;

import com.poo.javafx.Model;
import com.poo.javafx.Types.CPF;

public class PassageiroModel extends Model<PassageiroModel> {
    @Serial
    private static final long serialVersionUID = 1L;

    private CPF cpf;
    private String nome;
    private LocalDate dataNascimento;

    public PassageiroModel(String cpf, String nome, LocalDate dataNascimento) {
        this.cpf = new CPF(cpf);
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    protected boolean checarColisao(PassageiroModel objeto) {
        return this.cpf.equals(objeto.getCPF());
    }

    public CPF getCPF() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
