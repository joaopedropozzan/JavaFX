package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.CRUDController;

public class EmpresaController extends CRUDController<EmpresaModel, EmpresaView> {

    public EmpresaController() {
        super(new EmpresaView(), EmpresaModel.class);
    }

    @Override
    public EmpresaModel CamposParaModel() {
        // 1. Captura o CNPJ
        String cnpj = view.getTxtCNPJ().getText();

        // 2. VALIDAÇÃO: CNPJs que já existem não passam
        boolean idExiste = repositorio.objetos().stream().anyMatch(empresa -> empresa.getCNPJ() == cnpj);
        if (idExiste) {
            throw new IllegalArgumentException("Este CNPJ já está cadastrado para outra empresa!");
        }

        // 3. Captura o Nome (Ajustado o nome da variável para bater com o construtor
        // abaixo)
        String nomeEmpresa = view.getTxtNome().getText();

        int qtaFuncionarios;
        try {
            // 4. Captura a quantidade de funcionários
            qtaFuncionarios = Integer.parseInt(view.getDpQtaFuncionarios().getText());

            // Quem colocou isso????
            if (qtaFuncionarios == 69) {
                throw new IllegalArgumentException(
                        "Por favor pessoal, esse é um trabalho acadêmico sério. Vamos respeitar a matéria, por favor!!");
            }
        } catch (NumberFormatException ex) {
            System.err.println("Erro: A Quantidade de Funcionários devem ser números inteiros válidos.");
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
            return null;
        }

        // 5. Cria e adiciona a nova empresa (Variáveis corrigidas)
        EmpresaModel empresa = new EmpresaModel(cnpj, nomeEmpresa, qtaFuncionarios);

        // Limpa os campos após salvar
        view.getTxtCNPJ().clear();
        view.getTxtNome().clear();
        view.getDpQtaFuncionarios().clear();
        return empresa;
    }

    @Override
    public void ModelParaCampos(EmpresaModel selecionado) {
        view.getTxtCNPJ().setText(selecionado.getCNPJ());
        view.getTxtNome().setText(selecionado.getNomeEmpresa());
        view.getDpQtaFuncionarios().setText(String.valueOf(selecionado.getQtaFuncionarios()));
    }

}
