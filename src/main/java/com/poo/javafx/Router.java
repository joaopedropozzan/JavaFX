package com.poo.javafx;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Router {
    private static Router instance;

    private Stage stage;
    private MenuView menuPrincipal;

    private Router() {
    }

    public static Router getInstance() {
        if (instance == null) {
            instance = new Router();
        }
        return instance;
    }

    public void inicializar(Stage stage, MenuView menuPrincipal) {
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
