package com.poo.javafx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Repository<T extends Model<T>> {
    private static final String DATA_DIR = "data/";
    private final String dataFilePath;

    public Repository(Class<T> clazz) {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        this.dataFilePath = DATA_DIR + clazz.getSimpleName().toLowerCase() + ".dat";
    }

    private void gravarArquivo(ArrayList<T> objetos) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            out.writeObject(objetos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    private void checarUnicidade(ArrayList<T> objetos, T objeto) {
        boolean duplicado = objetos.stream()
                .anyMatch(e -> e.getID() != objeto.getID() && objeto.colideCom(e));
        if (duplicado) {
            throw new IllegalArgumentException("Registro já cadastrado no sistema.");
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> objetos() {
        File arquivo = new File(dataFilePath);
        if (!arquivo.exists())
            return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<T>) in.readObject();
        } catch (Exception e) {
            System.err.println("Erro ao ler arquivo " + dataFilePath + ": " + e);
            return new ArrayList<>();
        }
    }

    public T comID(int id) {
        for (T objeto : objetos()) {
            if (objeto.getID() == id) {
                return objeto;
            }
        }
        return null;
    }

    public void adicionar(T objeto) {
        ArrayList<T> objetosAtuais = objetos();
        checarUnicidade(objetosAtuais, objeto);

        int maiorID = objetosAtuais.stream().mapToInt(T::getID).max().orElse(0);

        objeto.setID(maiorID + 1);
        objetosAtuais.add(objeto);

        gravarArquivo(objetosAtuais);
    }

    public void remover(T objetoRemover) {
        ArrayList<T> objetosAtuais = objetos();
        objetosAtuais.removeIf(e -> e.getID() == objetoRemover.getID());

        gravarArquivo(objetosAtuais);
    }

    public boolean atualizar(int id, T objetoAtualizado) {
        objetoAtualizado.setID(id);
        ArrayList<T> objetosAtuais = objetos();
        checarUnicidade(objetosAtuais, objetoAtualizado);

        boolean objetoEncontrado = false;
        for (int i = 0; i < objetosAtuais.size(); i++) {
            if (objetosAtuais.get(i).getID() == id) {
                objetoAtualizado.setID(id);
                objetoAtualizado.herdarDe(objetosAtuais.get(i));
                objetosAtuais.set(i, objetoAtualizado);
                objetoEncontrado = true;
                break;
            }
        }

        if (!objetoEncontrado) {
            return false;
        }

        gravarArquivo(objetosAtuais);
        return true;
    }
}
