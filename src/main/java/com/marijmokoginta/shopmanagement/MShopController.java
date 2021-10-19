package com.marijmokoginta.shopmanagement;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName.PENCIL;
import static de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName.TRASH;

public class  MShopController implements Initializable {
    @FXML
    public FontAwesomeIcon btnSearch1;

    @FXML
    public TextField tfSearch1;

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
    private FontAwesomeIcon btnBack;

    @FXML
    private Button btnRiwayat;

    @FXML
    private Button btnAkun;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnTambahBarang;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSelesai;

    @FXML
    private Label lblHalaman;

    @FXML
    private FontAwesomeIcon btnClose;

    @FXML
    private FontAwesomeIcon btnMin;

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
    private GridPane pnlKasir;

    @FXML
    private GridPane pnlStokBarang;

    @FXML
    private GridPane pnlDataBarang;

    @FXML
    private GridPane pnlEdit;

    @FXML
    private TableView<Barang> tvDataBarang;

    @FXML
    private TableView tvEditDataBarang;

    @FXML
    private TableView tvStokBarang;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableColumn<Barang, String> colID;

    @FXML
    private TableColumn<Barang, String> colNamaBarang;

    @FXML
    private TableColumn<Barang, String> colKategori;

    @FXML
    private TableColumn<Barang, String> colHargaSatuan;

    @FXML
    private TableColumn<Barang, String> colStokBarang;

    @FXML
    private TableColumn<Barang, String> colCreatedAt;

    @FXML
    private TableColumn<Barang, String> colEdit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    Barang barang = null;


    // fungsi untuk memilih menu utama
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
            pnlKasir.toFront();
        } else if(event.getTarget().equals(btnStokBarang)){
            pnlStokBarang.toFront();

            // membaca data yang ada di file data-barang.dat ke tableview
            try{
                ObservableList<Barang> data = tvStokBarang.getItems();
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
        }
    }

    // menu tambah data
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception{
        if(event.getSource() == btnTambahBarang) {  // tambah data
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TambahBarang.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Tambah Barang");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } else if(event.getTarget().equals(btnLoad)){ // load data (membaca data) dari file data-barang.dat

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
        } else if(event.getTarget().equals(btnEdit)){  // edit data barang
            pnlEdit.toFront();
            // membaca data yang ada di file data-barang.dat ke tableview
            try{
                ObservableList<Barang> data = tvEditDataBarang.getItems();
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.showAndWait();
            }
            // add cell of button edit
            Callback<TableColumn<Barang, String>, TableCell<Barang, String>> cellFoctory = (TableColumn<Barang, String> param) -> {
                // make cell containing buttons
                final TableCell<Barang, String> cell = new TableCell<Barang, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                        } else {
                            FontAwesomeIcon deleteIcon = new FontAwesomeIcon();
                            FontAwesomeIcon editIcon = new FontAwesomeIcon();

                            deleteIcon.setIcon(TRASH);
                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#ff1744;"
                            );
                            editIcon.setIcon(PENCIL);
                            editIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#00E676;"
                            );
                            deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            });
                            editIcon.setOnMouseClicked((MouseEvent event) -> {

                                barang = (Barang) tvDataBarang.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("EditDataBarang.fxml"));

                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();
                            });

                            HBox managebtn = new HBox(editIcon, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                            setGraphic(managebtn);

                        }
                        setText(null);
                    }

                };

                return cell;
            };

        } else if(event.getTarget().equals(btnSelesai)){ // selesai dan kembali ke halaman data barang
            pnlDataBarang.toFront();
        }
    }

    // fungsi untuk button icon (fontawesomeicon)
    @FXML
    private void handleIconClick(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btnClose) { // button close
            ((Stage)rootPane.getScene().getWindow()).toBack();
            System.exit(0);
        } else if(mouseEvent.getSource() == btnMin) { // button minimize
            ((Stage) rootPane.getScene().getWindow()).toBack();
        } else if(mouseEvent.getTarget().equals(btnSearch)){  // button search
           // mengambil input dari tfSearch
            String cari = tfSearch.getText();

            if(!Objects.equals(cari, "")) {
                String[] keywords = cari.split("\\s");

                // cari data
                cekDatadiDatabase(keywords,tvDataBarang);
            }
        } else if(mouseEvent.getTarget().equals(btnSearch1)){ // button search pada menu edit
            // mengambil input dari tfSearch
            String cari = tfSearch1.getText();

            if(!Objects.equals(cari, "")) {
                String[] keywords = cari.split("\\s");

                // cari data
                cekDatadiDatabase(keywords,tvEditDataBarang);
            }
        } else if(mouseEvent.getTarget().equals(btnBack)){
            pnlHome.toFront();
        }
    }

    // fungsi cari data di file data-barang.dat
    private void cekDatadiDatabase(String[] keywords, TableView tableView) throws IOException {
        FileReader fileReader = new FileReader("data-barang.dat");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String data = bufferedReader.readLine();

        ObservableList<Barang> dataBarangItems = tableView.getItems();
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