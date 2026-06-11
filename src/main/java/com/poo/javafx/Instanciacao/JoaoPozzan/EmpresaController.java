package com.poo.javafx.Instanciacao.JoaoPozzan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.poo.javafx.InterfaceController;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.*;


public class EmpresaController implements InterfaceController {
    private EmpresaView view;
    private ObservableList<EmpresaModel> listaEmpresas;
    private final String ARQUIVO_DAT = "Empresas.dat";

    public EmpresaController(EmpresaView view) {
        this.view = view;
        this.listaEmpresas = FXCollections.observableArrayList();
        this.view.getTabela().setItems(this.listaEmpresas);

        this.view.getBtnAdicionar().setOnAction(e -> adicionar());
        this.view.getBtnDeletar().setOnAction(e -> deletar());
        this.view.getBtnAtualizar().setOnAction(e -> atualizar());

        ler();
    }


    @Override
    public void adicionar() {
        try {
            // 1. Captura o ID
            int id = Integer.parseInt(view.getTxtId().getText());

            // 2. VALIDAÇÃO: IDs que já existem não passam
            boolean idExiste = listaEmpresas.stream().anyMatch(empresa -> empresa.getId() == id);
            if (idExiste) {
                throw new IllegalArgumentException("Este ID já está cadastrado para outra empresa!");
            }

            // 3. Captura o Nome (Ajustado o nome da variável para bater com o construtor abaixo)
            String nomeEmpresa = view.getTxtNome().getText();

            // 4. Captura a quantidade de funcionários
            int qtaFuncionarios = Integer.parseInt(view.getDpQtaFuncionarios().getText());

            //Quem colocou isso????
            if (qtaFuncionarios == 67) {
                throw new IllegalArgumentException("Por favor pessoal, esse é um trabalho acadêmico sério. Vamos respeitar a matéria, por favor!!");
            }

            // 5. Cria e adiciona a nova empresa (Variáveis corrigidas)
            EmpresaModel novaEmpresa = new EmpresaModel(id, nomeEmpresa, qtaFuncionarios);
            listaEmpresas.add(novaEmpresa);
            salvarEmArquivo(); // Grava no .dat

            // Limpa os campos após salvar
            view.getTxtId().clear();
            view.getTxtNome().clear();
            view.getDpQtaFuncionarios().clear();

        } catch (NumberFormatException ex) {
            System.err.println("Erro: O ID e a Quantidade de Funcionários devem ser números inteiros válidos.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }
    }
    @Override
    public void ler() {
        File arquivo = new File(ARQUIVO_DAT);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                // Recupera a lista normal e converte para ObservableList
                ArrayList<EmpresaModel> listaRecuperada = (ArrayList<EmpresaModel>) ois.readObject();
                listaEmpresas.setAll(listaRecuperada);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao ler arquivo: " + e.getMessage());
            }
        }
    }
    @Override
    public void atualizar() {
        // Para atualizar: o usuário seleciona na tabela e preenche os campos de cima novamente
        EmpresaModel selecionado = view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                selecionado.setId(Integer.parseInt(view.getTxtId().getText()));
                selecionado.setNomeEmpresa(view.getTxtNome().getText());
                selecionado.setQtaFuncionarios(Integer.parseInt(view.getDpQtaFuncionarios().getText()));

                view.getTabela().refresh(); // Atualiza a visão da tabela
                salvarEmArquivo(); // Salva a alteração
            } catch (Exception ex) {
                System.err.println("Erro ao atualizar: Verifique os dados.");
            }
        }
    }
    @Override
    public void deletar() {
        EmpresaModel selecionado = view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            listaEmpresas.remove(selecionado);
            salvarEmArquivo(); // Atualiza o .dat sem a empresa
        }
    }
    private void salvarEmArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DAT))) {
            // Converte a ObservableList do JavaFX para um ArrayList normal do Java para salvar
            oos.writeObject(new ArrayList<>(listaEmpresas));
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}
