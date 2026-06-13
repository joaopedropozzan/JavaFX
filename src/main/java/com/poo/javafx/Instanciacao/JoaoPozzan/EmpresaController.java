package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.CRUDController;

public class EmpresaController extends CRUDController<EmpresaModel, EmpresaView> {

    public EmpresaController() {
        super(new EmpresaView(), EmpresaModel.class);
    }

    @Override
    public EmpresaModel camposParaModel() {
        // 1. Captura o CNPJ
        String cnpj = view.getTxtCNPJ().getText();

        // 3. Captura o Nome (Ajustado o nome da variável para bater com o construtor
        // abaixo)
        String nomeEmpresa = view.getTxtNome().getText();

        int qtaFuncionarios;
        try {
            // 4. Captura a quantidade de funcionários
            qtaFuncionarios = Integer.parseInt(view.getTxtQtaFuncionarios().getText());

            // Quem colocou isso????
            if (qtaFuncionarios == 69) {
                throw new IllegalArgumentException(
                        "Por favor pessoal, esse é um trabalho acadêmico sério. Vamos respeitar a matéria, por favor!!");
            }
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "Erro: A Quantidade de Funcionários devem ser números inteiros válidos.");
        }

        // 5. Cria e adiciona a nova empresa (Variáveis corrigidas)
        return new EmpresaModel(cnpj, nomeEmpresa, qtaFuncionarios);
    }

    @Override
    public void modelParaCampos(EmpresaModel selecionado) {
        view.getTxtCNPJ().setText(selecionado.getCNPJ().getValor());
        view.getTxtNome().setText(selecionado.getNomeEmpresa());
        view.getTxtQtaFuncionarios().setText(String.valueOf(selecionado.getQtaFuncionarios()));
    }

}
