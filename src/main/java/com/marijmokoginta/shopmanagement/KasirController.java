package com.marijmokoginta.shopmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class KasirController<FontAwesomeIcon> {
    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private void handleClose(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btnClose){
            ((Stage)rootPane.getScene().getWindow()).close();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
            Stage stage = new Stage();
            stage.setTitle("MShop");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
