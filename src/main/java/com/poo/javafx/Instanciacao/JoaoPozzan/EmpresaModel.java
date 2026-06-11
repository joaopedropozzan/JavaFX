package com.poo.javafx.Instanciacao.JoaoPozzan;

import java.io.Serial;
import java.io.Serializable;

public class EmpresaModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String nomeEmpresa;
    private int qtaFuncionarios;

    public EmpresaModel(int id, String nomeEmpresa, int qtaFuncionarios) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.qtaFuncionarios = qtaFuncionarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
