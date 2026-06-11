package com.poo.javafx.Instanciacao.JoaoPozzan;

import java.io.Serial;

import com.poo.javafx.Model;

public class EmpresaModel extends Model {
    @Serial
    private static final long serialVersionUID = 1L;

    private String cnpj;
    private String nomeEmpresa;
    private int qtaFuncionarios;

    public EmpresaModel(String cnpj, String nomeEmpresa, int qtaFuncionarios) {
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.qtaFuncionarios = qtaFuncionarios;
    }

    public String getCNPJ() {
        return cnpj;
    }

    public void setCNPJ(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public int getQtaFuncionarios() {
        return qtaFuncionarios;
    }

    public void setQtaFuncionarios(int qtaFuncionarios) {
        this.qtaFuncionarios = qtaFuncionarios;
    }
}
