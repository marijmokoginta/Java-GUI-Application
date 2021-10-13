package com.marijmokoginta.shopmanagement;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.*;
import java.util.Objects;

public class TambahBarangController<FontAwesomeIcon> {
    @FXML
    public Button btnBatal;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfTanggal;

    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    private TextField tfNamaBarang;

    @FXML
    private TextField tfHargaSatuan;

    @FXML
    private Button btnSimpan;

    @FXML
    private TextField tfKategori;

    @FXML
    private TextField tfJumlah;

    @FXML
    private Button btnSubmit;

    @FXML
    private TableView tvDataBarang;

    private void onButtonClicked(ActionEvent event) throws Exception{
        if(event.getTarget().equals(btnSubmit)){
            // menampilkan data ke dalam tabel tvDataBarang saat btnSubmit ditekan
            ObservableList<Barang> data = tvDataBarang.getItems();
            data.add(new Barang(
                    tfID.getText(),
                    tfNamaBarang.getText(),
                    tfKategori.getText(),
                    tfHargaSatuan.getText(),
                    tfJumlah.getText(),
                    tfTanggal.getText()
            ));
            tfID.setText("");
            tfTanggal.setText("");
            tfJumlah.setText("");
            tfHargaSatuan.setText("");
            tfKategori.setText("");
            tfNamaBarang.setText("");
        } else if(event.getTarget().equals(btnSimpan)){
            // menulis data yang ada di tabelview ke file bernama data-barang.dat
            try{
                ObservableList<Barang> data = tvDataBarang.getItems();
                FileWriter fileOutput = new FileWriter("data-barang.dat",true);
                BufferedWriter writer = new BufferedWriter(fileOutput);
                for(int i = 0; i < data.size(); i++){
                    writer.write(String.valueOf(data.get(i).getID()) + "," + String.valueOf(data.get(i).getNamaBarang() + "," + String.valueOf(data.get(i).getKategori()) + "," + String.valueOf(data.get(i).getHargaSatuan()) + "," + String.valueOf(data.get(i).getJumlah()) + "," + String.valueOf(data.get(i).getCreatedAt())));
                    writer.newLine();
                }
                writer.close();
                ((Stage)rootPane.getScene().getWindow()).close();
            } catch (IOException ex){
                // menampilkan dialog jika gagal menyimpan data
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Gagal menyimpan data");
                alert.setContentText("Error IO Exeption: " + ex.getMessage());
                alert.showAndWait();
            }
        } else if(event.getTarget().equals(btnBatal)){
            ((Stage)rootPane.getScene().getWindow()).close();

        }else if(event.getTarget().equals(btnClose)){
            ((Stage)rootPane.getScene().getWindow()).close();
        }
    }

    private void handleClose(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btnClose){
            ((Stage)rootPane.getScene().getWindow()).close();
        }
    }
}
