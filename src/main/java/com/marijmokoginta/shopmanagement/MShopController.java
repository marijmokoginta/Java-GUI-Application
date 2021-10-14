package com.marijmokoginta.shopmanagement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.util.Locale;
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
    private FontAwesomeIcon btnSearch;

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

    @FXML
    private TextField tfSearch;

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

    // menu home
    @FXML
    private void homeButtonAction(ActionEvent event) throws Exception{
        if(event.getTarget().equals(btnKasir)){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Kasir.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Kasir");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)rootPane.getScene().getWindow()).close();
        }
    }

    // menu tambah data
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception{
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

    // tombol close
    @FXML
    private void handleClose(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btnClose) {
            System.exit(0);
        } else if(mouseEvent.getTarget().equals(btnSearch)){
            // membaca file
            File file = new File("data-barang.dat");

            // mengambil input dari tfSearch
            String cari = tfSearch.getText();

            if(!Objects.equals(cari, "")) {
                String[] keywords = cari.split("\\s");

                // cari data
                cekDatadiDatabase(keywords);
            }
        }
    }

    // fungsi cari data di file data-barang.dat
    private void cekDatadiDatabase(String[] keywords) throws IOException {
        FileReader fileReader = new FileReader("data-barang.dat");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String data = bufferedReader.readLine();

        ObservableList<Barang> dataBarangItems = tvDataBarang.getItems();
        dataBarangItems.clear();

        boolean isExist;

        while(data != null){
            // cek keyword dalam database
            isExist = true;
            for(String keyword : keywords){
                isExist = isExist && data.toLowerCase().contains(keyword.toLowerCase());
            }

            // jika keyword cocok maka tampilkan
            if(isExist){

                String[] temp = data.split(",");
                Barang barang = new Barang(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
                dataBarangItems.add(barang);
            }
            data = bufferedReader.readLine();
        }
        bufferedReader.close();
    }
}