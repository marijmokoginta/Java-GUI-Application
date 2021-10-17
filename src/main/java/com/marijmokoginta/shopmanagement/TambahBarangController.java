package com.marijmokoginta.shopmanagement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;

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

    @FXML
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
                for (Barang datum : data) {
                    writer.write(datum.getID() + "," + datum.getNamaBarang() + "," + datum.getKategori() + "," + datum.getHargaSatuan() + "," + datum.getJumlah() + "," + datum.getCreatedAt());
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

    @FXML
    private void handleClose(javafx.scene.input.MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == btnClose){
            ((Stage)rootPane.getScene().getWindow()).close();
        }
    }
}
