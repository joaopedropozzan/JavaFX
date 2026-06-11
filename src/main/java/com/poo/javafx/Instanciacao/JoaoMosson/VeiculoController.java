package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class VeiculoController extends Controller<VeiculoModel, VeiculoView> {

    public VeiculoController(Scene scene, VeiculoView view) {
        super(scene, view, VeiculoModel.class);
    }

    @Override
    public VeiculoModel camposParaObjeto() {
        int ano;
        try {
            ano = Integer.parseInt(view.getTxtAno().getText());
        } catch (NumberFormatException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro de Validação");
            alerta.setHeaderText("Ano inválido");
            alerta.setContentText("Por favor, digite apenas números no campo Ano.");
            alerta.showAndWait();

            return null;
        }

        String placa = view.getTxtPlaca().getText();
        String modelo = view.getTxtModelo().getText();

        VeiculoModel veiculo = new VeiculoModel(placa, modelo, ano);

        view.getTxtPlaca().clear();
        view.getTxtModelo().clear();
        view.getTxtAno().clear();
        return veiculo;
    }
}
