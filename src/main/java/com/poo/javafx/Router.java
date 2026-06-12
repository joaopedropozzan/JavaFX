package com.poo.javafx;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Router {
    private MenuView menuPrincipal;
    private Stage stage;

    public Router(Stage stage, MenuView menuPrincipal) {
        this.stage = stage;
        this.menuPrincipal = menuPrincipal;
    }

    public void navegarPara(CRUDController<?, ?> controller) {
        CRUDView<?> view = controller.getView();
        Scene scene = stage.getScene();

        view.getBtnVoltar().setOnAction(e -> {
            stage.setTitle(menuPrincipal.getTitulo());
            scene.setRoot(menuPrincipal);
        });

        stage.setTitle(view.getTitulo());
        scene.setRoot(view);
    }
}
