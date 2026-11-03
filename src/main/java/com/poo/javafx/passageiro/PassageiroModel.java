package com.poo.javafx.passageiro;

import java.time.LocalDate;
import java.util.Objects;

import com.poo.javafx.Model;
import com.poo.javafx.tipo.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "passageiros")
public class PassageiroModel extends Model<PassageiroModel> {
    @Column(nullable = false, unique = true)
    private CPF cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    protected PassageiroModel() {
    }

    public PassageiroModel(String cpf, String nome, LocalDate dataNascimento) {
        this.cpf = new CPF(cpf);
        this.nome = nome;
        this.dataNascimento = dataNascimento;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PassageiroModel that = (PassageiroModel) o;
        if (this.getID() != 0 && that.getID() != 0) {
            return this.getID() == that.getID();
        }
        return Objects.equals(this.cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return getID() != 0 ? Objects.hash(getID()) : Objects.hash(cpf);
    }
}
