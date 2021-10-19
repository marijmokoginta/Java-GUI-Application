package com.marijmokoginta.shopmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.Objects;

public class MShopApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Object root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        stage.setTitle("Mshop");

        File file = new File("C:\\Dev\\JavaFX\\ShopManagement\\image\\Logo.png");
        Image image = new Image(file.toURI().toString());
        stage.getIcons().add(image);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene((Parent) root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}