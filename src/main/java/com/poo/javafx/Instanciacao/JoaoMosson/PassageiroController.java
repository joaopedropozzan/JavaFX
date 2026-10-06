package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.CRUDController;
import com.poo.javafx.Types.CPF;
import com.poo.javafx.validacao.RegraTipoDominio;

public class PassageiroController extends CRUDController<PassageiroModel, PassageiroView> {
    public PassageiroController() {
        super(PassageiroModel.class);
        adicionarRegra(new RegraTipoDominio(view.getTxtCPF(), CPF::new));
    }

    @Override
    public PassageiroModel camposParaModel() {
        return new PassageiroModel(
                view.getTxtCPF().getText(),
                view.getTxtNome().getText(),
                view.getDpDataNascimento().getValue());
    }

    @Override
    public void modelParaCampos(PassageiroModel selecionado) {
        view.getTxtCPF().setText(selecionado.getCPF().getValor());
        view.getTxtNome().setText(selecionado.getNome());
        view.getDpDataNascimento().setValue(selecionado.getDataNascimento());
    }

    @Override
    protected PassageiroView criarView() {
        return new PassageiroView();
    }
}
