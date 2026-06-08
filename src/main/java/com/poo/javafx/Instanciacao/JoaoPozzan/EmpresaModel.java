package com.poo.javafx.Instanciacao.JoaoPozzan;

import java.io.Serial;
import java.io.Serializable;

public class EmpresaModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int idEmpresa;
    private String nomeEmpresa;
    private int qtaFuncionarios;

    public EmpresaModel(int idEmpresa, String nomeEmpresa, int qtaFuncionarios) {
        this.idEmpresa = idEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.qtaFuncionarios = qtaFuncionarios;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
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
