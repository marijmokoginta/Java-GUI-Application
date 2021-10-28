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
            String ID = "B" + ID();
            tfID.setText(ID);
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
            tfTanggal.setText("");
            tfJumlah.setText("");
            tfHargaSatuan.setText("");
            tfKategori.setText("");
            tfNamaBarang.setText("");

            FileWriter fileWriter = new FileWriter("data-tabel.dat");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Barang dataTabel : data){
                bufferedWriter.write(dataTabel.getID()  + "," + dataTabel.getNamaBarang() + "," + dataTabel.getKategori() + "," + dataTabel.getHargaSatuan() + "," + dataTabel.getJumlah() + "," + dataTabel.getCreatedAt());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
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

            // hapus file data-tabel
            File dataTabel = new File("data-tabel.dat");
            dataTabel.delete();
        } else if(event.getTarget().equals(btnBatal)){
            File dataTabel = new File("data-tabel.dat");
            dataTabel.delete();
            ((Stage)rootPane.getScene().getWindow()).close();
        }
    }

    @FXML
    private void handleClose(javafx.scene.input.MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == btnClose){
            File dataTabel = new File("data-tabel.dat");
            dataTabel.delete();
            ((Stage)rootPane.getScene().getWindow()).close();
        }
    }

    private String ID(){
        int number = 1;
        int jumlahData = 0;
        String autoNum = Integer.toString(number);
        boolean isExist;
        String[] keywords = autoNum.split("\\s");

        try{
            FileReader fileReader = new FileReader("data-barang.dat");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String data = bufferedReader.readLine();
            while(data != null){
                number++;

//                isExist = true;
//                for(String keyword : keywords){
//                    isExist = isExist && data.toLowerCase().contains(keyword.toLowerCase());
//                }
//
//                // jika keyword cocok maka tampilkan
//                if(isExist){
//                    number++;
//                }

                data = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (IOException ignored){

        }

        // membaca file data-tabel.dat
        try{
            FileReader readDataTabel = new FileReader("data-tabel.dat");
            BufferedReader reader = new BufferedReader(readDataTabel);

            String cekData = reader.readLine();
            while (cekData != null){
                jumlahData++;
                cekData = reader.readLine();
            }
            reader.close();
        } catch (IOException ignored){

        }

        number += jumlahData;

        autoNum = Integer.toString(number);

        return autoNum;
    }
}
