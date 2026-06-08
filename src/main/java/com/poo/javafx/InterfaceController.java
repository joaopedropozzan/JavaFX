package com.poo.javafx;

public interface InterfaceController {

    /**
     * C (Create): Captura os dados da View, cria um objeto e salva na lista/arquivo.
     */
    void adicionar();

    /**
     * R (Read): Lê os dados do arquivo .dat e atualiza a TableView na tela.
     */
    void ler();

    /**
     * U (Update): Captura o objeto selecionado na tabela, atualiza seus dados e salva.
     */
    void atualizar();

    /**
     * D (Delete): Remove o objeto selecionado da lista e atualiza o arquivo .dat.
     */
    void deletar();
}