package com.poo.javafx.Instanciacao.Eduardo;

import java.time.LocalDateTime;

import com.poo.javafx.Model;
import com.poo.javafx.Repository;
import com.poo.javafx.Instanciacao.JoaoMosson.PassageiroModel;

public class TransacaoModel extends Model<TransacaoModel> {
    private int userOrigemID;

    private int userDestinoID;
    private double valor;
    private LocalDateTime timestamp;

    public TransacaoModel(int userOrigemID, int userDestinoID, double valor) {
        this.userOrigemID = userOrigemID;
        this.userDestinoID = userDestinoID;
        this.valor = valor;
        this.timestamp = LocalDateTime.now();
    }

    public int getUserOrigemID() {
        return userOrigemID;
    }

    public int getUserDestinoID() {
        return userDestinoID;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public PassageiroModel getUserOrigem() {
        return new Repository<>(PassageiroModel.class).comID(userOrigemID);
    }

    public PassageiroModel getUserDestino() {
        return new Repository<>(PassageiroModel.class).comID(userDestinoID);
    }

}
