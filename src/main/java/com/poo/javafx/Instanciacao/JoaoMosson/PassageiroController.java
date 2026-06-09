package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.InterfaceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PassageiroController implements InterfaceController {

    private PassageiroView view;
    private ObservableList<PassageiroModel> listaPassageiros;
    private final String ARQUIVO_DAT = "passageiros.dat";

    // O Construtor é onde ligamos o "Cano"
    public PassageiroController(PassageiroView view) {
        this.view = view;
        this.listaPassageiros = FXCollections.observableArrayList();
        this.view.getTabela().setItems(this.listaPassageiros);

        // LIGANDO OS BOTÕES AOS MÉTODOS (O CANO)
        this.view.getBtnAdicionar().setOnAction(e -> adicionar());
        this.view.getBtnDeletar().setOnAction(e -> deletar());
        this.view.getBtnAtualizar().setOnAction(e -> atualizar());

        // Quando a tela abre, já lê o arquivo
        ler();
    }

    @Override
    public void adicionar() {
        try {
            int id = Integer.parseInt(view.getTxtId().getText());
            String nome = view.getTxtNome().getText();
            LocalDate data = view.getDpDataNascimento().getValue();

            PassageiroModel novoPassageiro = new PassageiroModel(id, nome, data);
            listaPassageiros.add(novoPassageiro);
            salvarEmArquivo(); // Grava no .dat

            // Limpa os campos após salvar
            view.getTxtId().clear();
            view.getTxtNome().clear();
            view.getDpDataNascimento().setValue(null);

        } catch (NumberFormatException ex) {
            System.err.println("Erro: O ID deve ser um número inteiro.");
        }
    }

    @Override
    public void deletar() {
        PassageiroModel selecionado = view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            listaPassageiros.remove(selecionado);
            salvarEmArquivo(); // Atualiza o .dat sem o passageiro
        }
    }

    @Override
    public void atualizar() {
        // Para atualizar: o usuário seleciona na tabela e preenche os campos de cima novamente
        PassageiroModel selecionado = view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                selecionado.setId(Integer.parseInt(view.getTxtId().getText()));
                selecionado.setNome(view.getTxtNome().getText());
                selecionado.setDataNascimento(view.getDpDataNascimento().getValue());

                view.getTabela().refresh(); // Atualiza a visão da tabela
                salvarEmArquivo(); // Salva a alteração
            } catch (Exception ex) {
                System.err.println("Erro ao atualizar: Verifique os dados.");
            }
        }
    }

    @Override
    public void ler() {
        File arquivo = new File(ARQUIVO_DAT);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                // Recupera a lista normal e converte para ObservableList
                ArrayList<PassageiroModel> listaRecuperada = (ArrayList<PassageiroModel>) ois.readObject();
                listaPassageiros.setAll(listaRecuperada);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao ler arquivo: " + e.getMessage());
            }
        }
    }

    // Método auxiliar exigido para Persistência
    private void salvarEmArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DAT))) {
            // Converte a ObservableList do JavaFX para um ArrayList normal do Java para salvar
            oos.writeObject(new ArrayList<>(listaPassageiros));
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}