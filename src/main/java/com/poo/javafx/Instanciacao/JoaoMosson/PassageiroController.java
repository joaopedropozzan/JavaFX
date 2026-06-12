package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.CRUDController;

import java.time.LocalDate;

public class PassageiroController extends CRUDController<PassageiroModel, PassageiroView> {
    public PassageiroController() {
        super(new PassageiroView(), PassageiroModel.class);
    }

    @Override
    public PassageiroModel CamposParaModel() {
        String cpf = view.getTxtCPF().getText();
        String nome = view.getTxtNome().getText();
        LocalDate data = view.getDpDataNascimento().getValue();

        PassageiroModel passageiro = new PassageiroModel(cpf, nome, data);

        view.getTxtCPF().clear();
        view.getTxtNome().clear();
        view.getDpDataNascimento().setValue(null);

        return passageiro;
    }

    @Override
    public void ModelParaCampos(PassageiroModel selecionado) {
        view.getTxtCPF().setText(selecionado.getCPF());
        view.getTxtNome().setText(selecionado.getNome());
        view.getDpDataNascimento().setValue(selecionado.getDataNascimento());
    }
}
