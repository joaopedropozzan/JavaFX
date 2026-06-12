package com.poo.javafx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Repository<T extends Model> {
    private final String fileName;

    public Repository(Class<T> clazz) {
        this.fileName = clazz.getSimpleName().toLowerCase() + "s.dat";
    }

    private void gravarArquivo(ArrayList<T> objetos) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(objetos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> objetos() {
        File arquivo = new File(fileName);
        if (!arquivo.exists())
            return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<T>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void adicionar(T objeto) {
        ArrayList<T> objetosAtuais = objetos();
        int ultimoID;
        try {
            T ultimoObjeto = objetosAtuais.getLast();
            ultimoID = ultimoObjeto.getID();
        } catch (NoSuchElementException e) {
            ultimoID = 0;
        }

        objeto.setID(ultimoID + 1);
        objetosAtuais.add(objeto);

        gravarArquivo(objetosAtuais);
    }

    public void remover(T objetoRemover) {
        ArrayList<T> objetosAtuais = objetos();
        objetosAtuais.removeIf(e -> e.getID() == objetoRemover.getID());

        gravarArquivo(objetosAtuais);
    }

    public boolean atualizar(int id, T novoObjeto) {
        ArrayList<T> objetosAtuais = objetos();
        boolean objetoEncontrado = false;

        for (int i = 0; i < objetosAtuais.size(); i++) {
            if (objetosAtuais.get(i).getID() == id) {
                novoObjeto.setID(id);
                objetosAtuais.set(i, novoObjeto);
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
