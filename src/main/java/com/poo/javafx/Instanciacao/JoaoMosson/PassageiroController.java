package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.Controller;
import javafx.scene.Scene;

import java.time.LocalDate;

public class PassageiroController extends Controller<PassageiroModel, PassageiroView> {
    public PassageiroController(Scene scene, PassageiroView view) {
        super(scene, view, PassageiroModel.class);
    }

    @Override
    public PassageiroModel camposParaObjeto() {
        String cpf = view.getTxtCPF().getText();
        String nome = view.getTxtNome().getText();
        LocalDate data = view.getDpDataNascimento().getValue();

        PassageiroModel passageiro = new PassageiroModel(cpf, nome, data);

        view.getTxtCPF().clear();
        view.getTxtNome().clear();
        view.getDpDataNascimento().setValue(null);

        return passageiro;
    }
}
