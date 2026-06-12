package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.CRUDController;

import java.time.LocalDate;

public class PassageiroController extends CRUDController<PassageiroModel, PassageiroView> {
    public PassageiroController() {
        super(new PassageiroView(), PassageiroModel.class);
    }

    @Override
    public PassageiroModel camposParaModel() {
        String cpf = view.getTxtCPF().getText();
        String nome = view.getTxtNome().getText();
        LocalDate data = view.getDpDataNascimento().getValue();

        return new PassageiroModel(cpf, nome, data);
    }

    @Override
    public void modelParaCampos(PassageiroModel selecionado) {
        view.getTxtCPF().setText(selecionado.getCPF().getValor());
        view.getTxtNome().setText(selecionado.getNome());
        view.getDpDataNascimento().setValue(selecionado.getDataNascimento());
    }
}
