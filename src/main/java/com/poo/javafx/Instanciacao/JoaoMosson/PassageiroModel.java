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

    public void setCPF(String cpf) {
        this.cpf = new CPF(cpf);
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

    @Override
    public String toString() {
        return this.nome;
    }
}
