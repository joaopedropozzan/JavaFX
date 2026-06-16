package com.poo.javafx.Instanciacao.JoaoPozzan;

import com.poo.javafx.CRUDController;

public class CarteiraController extends CRUDController<CarteiraModel, CarteiraView> {

    public CarteiraController() {
        super(new CarteiraView(), CarteiraModel.class);
    }

    @Override
    public CarteiraModel camposParaModel() {
        double saldo;

        // 1. Validação do Saldo (INT)
        try {
            saldo = Double.parseDouble(view.getTxtSaldo().getText());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Erro: O Saldo deve ser um número inteiro válido.");
        }

        // 2. Validação do Número do Cartão (16 dígitos numéricos)
        // Removemos espaços vazios antes de validar
        String numeroCartao = view.getTxtNumeroCartao().getText().replaceAll("\\s+", "");
        if (!numeroCartao.matches("\\d{16}")) {
            throw new IllegalArgumentException("Erro: O número do cartão deve conter exatamente 16 dígitos numéricos.");
        }

        // 3. Validação do Titular (Não pode ser vazio)
        String titularConta = view.getTxtTitularConta().getText().trim();
        if (titularConta.isEmpty()) {
            throw new IllegalArgumentException("Erro: O nome do titular da conta não pode estar vazio.");
        }

        return new CarteiraModel(saldo, numeroCartao, titularConta);
    }

    @Override
    public void modelParaCampos(CarteiraModel selecionado) {
        view.getTxtSaldo().setText(String.valueOf(selecionado.getSaldo()));
        view.getTxtNumeroCartao().setText(selecionado.getNumeroCartao());
        view.getTxtTitularConta().setText(selecionado.getTitularConta());
    }
}
