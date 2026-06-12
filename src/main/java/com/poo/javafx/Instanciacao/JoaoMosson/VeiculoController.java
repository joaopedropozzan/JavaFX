package com.poo.javafx.Instanciacao.JoaoMosson;

import com.poo.javafx.CRUDController;
import javafx.scene.control.Alert;

public class VeiculoController extends CRUDController<VeiculoModel, VeiculoView> {

    public VeiculoController() {
        super(new VeiculoView(), VeiculoModel.class);
    }

    @Override
    public VeiculoModel CamposParaModel() {
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

    @Override
    public void ModelParaCampos(VeiculoModel selecionado) {
        view.getTxtPlaca().setText(selecionado.getPlaca());
        view.getTxtModelo().setText(selecionado.getModelo());
        view.getTxtAno().setText(String.valueOf(selecionado.getAno()));
    }
}
