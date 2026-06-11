package com.poo.javafx.Instanciacao.Eduardo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.poo.javafx.InterfaceController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TrajetoController implements InterfaceController {
    private TrajetoView view;

    private ObservableList<TrajetoModel> listaTrajetos;
    private final String ARQUIVO_DAT = "trajetos.dat";

    public TrajetoController(TrajetoView view) {
        this.view = view;
        this.listaTrajetos = FXCollections.observableArrayList();
        this.view.getTabela().setItems(this.listaTrajetos);

        this.view.getBtnAdicionar().setOnAction(e -> adicionar());
        this.view.getBtnDeletar().setOnAction(e -> deletar());
        this.view.getBtnAtualizar().setOnAction(e -> atualizar());

        ler();
    }

    @Override
    public void adicionar() {
        String origem = view.getOrigem().getText();
        String destino = view.getDestino().getText();
        LocalDateTime horarioSaida = LocalDateTime.parse(view.getHorarioSaida().getText());
        TrajetoModel trajeto = new TrajetoModel(origem, destino, horarioSaida);
        listaTrajetos.add(trajeto);
        salvarEmArquivo();

        view.getOrigem().clear();
        view.getDestino().clear();
        view.getHorarioSaida().setText(null);
    }

    @Override
    public void ler() {
        File arquivo = new File(ARQUIVO_DAT);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                ArrayList<TrajetoModel> listaRecuperada = (ArrayList<TrajetoModel>) ois.readObject();
                listaTrajetos.setAll(listaRecuperada);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao ler arquivo: " + e.getMessage());
            }
        }
    }

    @Override
    public void atualizar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    private void salvarEmArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DAT))) {
            oos.writeObject(new ArrayList<>(listaTrajetos));
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

}
