package com.marijmokoginta.shopmanagement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Stack;

public class MShopController<FontAwesomeIcon> implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnDataBarang;

    @FXML
    private Pane pnlMenu;

    @FXML
    private Pane pnlDeskripsi;

    @FXML
    private Button btnKasir;

    @FXML
    private Button btnEvaluasiToko;

    @FXML
    private Button btnPengaturan;

    @FXML
    private Button btnStokBarang;

    @FXML
    private Button btnRiwayat;

    @FXML
    private Button btnAkun;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnTambahBarang;

    @FXML
    private Button btnUbahBarang;

    @FXML
    private Button btnHapusData;

    @FXML
    private Label lblHalaman;

    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    private Button btnLoad;

    @FXML
    private GridPane pnlPengaturan;

    @FXML
    private GridPane pnlEvaluasiToko;

    @FXML
    private GridPane pnlHome;

    @FXML
    private GridPane pnlDataBarang;

    @FXML
    private GridPane pnlDataBarang1;

    @FXML
    private TableView tvDataBarang;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource() == btnHome){
            lblHalaman.setText("Home");
            pnlHome.toFront();
        } else if(event.getSource() == btnDataBarang){
            lblHalaman.setText("Data Barang");
            pnlDataBarang.toFront();
            // membaca data yang ada di file data-barang.dat ke tableview
            try{
                ObservableList<Barang> data = tvDataBarang.getItems();
                data.clear();
                BufferedReader reader = new BufferedReader(new FileReader("data-barang.dat"));

                String line;
                while ((line = reader.readLine()) != null){
                    String[] temp = line.split(",");
                    Barang barang = new Barang(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
                    data.add(barang);
                }
                reader.close();
            } catch (IOException ex){

            }
        } else if(event.getSource() == btnEvaluasiToko){
            lblHalaman.setText("Evaluasi Toko");
            pnlEvaluasiToko.toFront();
        } else if(event.getSource() == btnPengaturan){
            lblHalaman.setText("Pengaturan");
            pnlPengaturan.toFront();
        }
    }
    @FXML
    void handleButtonAction(ActionEvent event) throws Exception{
        if(event.getSource() == btnTambahBarang) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TambahBarang.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Tambah Barang");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } else if(event.getTarget().equals(btnLoad)){
            // membaca data yang ada di file data-barang.dat ke tableview
            try{
                ObservableList<Barang> data = tvDataBarang.getItems();
                data.clear();
                BufferedReader reader = new BufferedReader(new FileReader("data-barang.dat"));

                String line;
                while ((line = reader.readLine()) != null){
                    String[] temp = line.split(",");
                    Barang barang = new Barang(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
                    data.add(barang);
                }
                reader.close();
            } catch (IOException ex){

            }
        } else if(event.getTarget().equals(btnUbahBarang)){

        } else if(event.getTarget().equals(btnHapusData)){
            pnlDataBarang1.toFront();
        }
    }

    public void handleClose(javafx.scene.input.MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == btnClose)
            System.exit(0);
    }
}