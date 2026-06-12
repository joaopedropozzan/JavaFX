package com.poo.javafx.Instanciacao.JoaoPozzan;

import java.io.Serial;

import com.poo.javafx.Model;
import com.poo.javafx.Types.CNPJ;

public class EmpresaModel extends Model<EmpresaModel> {
    @Serial
    private static final long serialVersionUID = 1L;

    private CNPJ cnpj;
    private String nomeEmpresa;
    private int qtaFuncionarios;

    public EmpresaModel(String cnpj, String nomeEmpresa, int qtaFuncionarios) {
        this.cnpj = new CNPJ(cnpj);
        this.nomeEmpresa = nomeEmpresa;
        this.qtaFuncionarios = qtaFuncionarios;
    }

    protected boolean checarColisao(EmpresaModel objeto) {
        return this.cnpj.equals(objeto.getCNPJ());
    }

    public CNPJ getCNPJ() {
        return cnpj;
    }

    public void setCNPJ(String cnpj) {
        this.cnpj = new CNPJ(cnpj);
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
