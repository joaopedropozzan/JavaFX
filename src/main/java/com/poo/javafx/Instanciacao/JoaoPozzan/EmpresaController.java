package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.CRUDController;
import com.poo.javafx.validacao.RegraNumeroInteiro;

public class EmpresaController extends CRUDController<EmpresaModel, EmpresaView> {

    public EmpresaController() {
        super(EmpresaModel.class);
        adicionarRegra(new RegraNumeroInteiro(view.getTxtQtaFuncionarios(), "Quantidade de funcionários", 1));
    }

    @Override
    public EmpresaModel camposParaModel() {
        return new EmpresaModel(
                view.getTxtCNPJ().getText(),
                view.getTxtNome().getText(),
                Integer.parseInt(view.getTxtQtaFuncionarios().getText().trim()));
    }

    @Override
    public void modelParaCampos(EmpresaModel selecionado) {
        view.getTxtCNPJ().setText(selecionado.getCNPJ().getValor());
        view.getTxtNome().setText(selecionado.getNomeEmpresa());
        view.getTxtQtaFuncionarios().setText(String.valueOf(selecionado.getQtaFuncionarios()));
    }

    @Override
    protected EmpresaView criarView() {
        return new EmpresaView();
    }

}
