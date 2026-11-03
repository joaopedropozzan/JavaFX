package com.poo.javafx.empresa;

import com.poo.javafx.Model;
import com.poo.javafx.tipo.CNPJ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresas")
public class EmpresaModel extends Model<EmpresaModel> {
    @Column(nullable = false, unique = true)
    private CNPJ cnpj;

    @Column(nullable = false)
    private String nomeEmpresa;

    @Column(nullable = false)
    private int qtaFuncionarios;

    protected EmpresaModel() {
    }

    public EmpresaModel(String cnpj, String nomeEmpresa, int qtaFuncionarios) {
        this.cnpj = new CNPJ(cnpj);
        this.nomeEmpresa = nomeEmpresa;
        this.qtaFuncionarios = qtaFuncionarios;
    }

    public CNPJ getCNPJ() {
        return cnpj;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public int getQtaFuncionarios() {
        return qtaFuncionarios;
    }

}
