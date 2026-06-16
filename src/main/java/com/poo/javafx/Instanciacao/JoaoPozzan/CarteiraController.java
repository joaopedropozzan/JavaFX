package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.CRUDController;

public class CarteiraController extends CRUDController<CarteiraModel, CarteiraView> {

    public CarteiraController() {
        super(new CarteiraView(), CarteiraModel.class);
    }

    @Override
    public CarteiraModel camposParaModel() {
        int saldo;
        int numeroCartao;

        try {
            // 1. Captura e converte o Saldo
            saldo = Integer.parseInt(view.getTxtSaldo().getText());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "Erro: O Saldo deve ser um número inteiro válido.");
        }

        try {
            // 2. Captura e converte o Número do Cartão
            numeroCartao = Integer.parseInt(view.getTxtNumeroCartao().getText());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "Erro: O Número do Cartão deve ser um número inteiro válido.");
        }

        // 3. Captura o Titular da Conta
        String titularConta = view.getTxtTitularConta().getText();

        // 4. Cria e adiciona a nova carteira
        return new CarteiraModel(saldo, numeroCartao, titularConta);
    }

    @Override
    public void modelParaCampos(CarteiraModel selecionado) {
        view.getTxtSaldo().setText(String.valueOf(selecionado.getSaldo()));
        view.getTxtNumeroCartao().setText(String.valueOf(selecionado.getNumeroCartao()));
        view.getTxtTitularConta().setText(selecionado.getTitularConta());
    }
}