package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.InterfaceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;

public class VeiculoController implements InterfaceController {

    private VeiculoView view;
    private ObservableList<VeiculoModel> listaVeiculos;
    private final String ARQUIVO_DAT = "veiculos.dat";

    public VeiculoController(VeiculoView view) {
        this.view = view;
        this.listaVeiculos = FXCollections.observableArrayList();
        this.view.getTabela().setItems(this.listaVeiculos);

        // Engata o "cano" dos botões
        this.view.getBtnAdicionar().setOnAction(e -> adicionar());
        this.view.getBtnDeletar().setOnAction(e -> deletar());
        this.view.getBtnAtualizar().setOnAction(e -> atualizar());

        ler(); // Carrega os dados salvos ao abrir
    }

    @Override
    public void adicionar() {
        try {
            // Validação numérica obrigatória
            String placa = view.getTxtPlaca().getText();
            String modelo = view.getTxtModelo().getText();
            int ano = Integer.parseInt(view.getTxtAno().getText());

            VeiculoModel novoVeiculo = new VeiculoModel(placa, modelo, ano);
            listaVeiculos.add(novoVeiculo);
            salvarEmArquivo();

            view.getTxtPlaca().clear();
            view.getTxtModelo().clear();
            view.getTxtAno().clear();

        } catch (NumberFormatException ex) {
            // Mostra um alerta se o usuário digitar texto no campo de Ano
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro de Validação");
            alerta.setHeaderText("Ano inválido");
            alerta.setContentText("Por favor, digite apenas números no campo Ano.");
            alerta.showAndWait();
        }
    }

    @Override
    public void deletar() {
        VeiculoModel selecionado = view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            listaVeiculos.remove(selecionado);
            salvarEmArquivo();
        }
    }

    @Override
    public void atualizar() {
        VeiculoModel selecionado = view.getTabela().getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                selecionado.setPlaca(view.getTxtPlaca().getText());
                selecionado.setModelo(view.getTxtModelo().getText());
                selecionado.setAno(Integer.parseInt(view.getTxtAno().getText()));

                view.getTabela().refresh();
                salvarEmArquivo();
            } catch (NumberFormatException ex) {
                System.err.println("O ano deve ser um número inteiro.");
            }
        }
    }

    @Override
    public void ler() {
        File arquivo = new File(ARQUIVO_DAT);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                ArrayList<VeiculoModel> listaRecuperada = (ArrayList<VeiculoModel>) ois.readObject();
                listaVeiculos.setAll(listaRecuperada);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao ler arquivo: " + e.getMessage());
            }
        }
    }

    private void salvarEmArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DAT))) {
            oos.writeObject(new ArrayList<>(listaVeiculos));
            System.out.println("Veículo salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}