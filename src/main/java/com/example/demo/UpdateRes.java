package com.example.demo;

import domain.Loc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceLoc;
import service.ServiceRezervare;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateRes {
    @FXML
    Button Done;

    @FXML
    TextField text1;

    @FXML
    TextField text2;


    ServiceRezervare rezervare = new ServiceRezervare();
    ServiceLoc loc = new ServiceLoc();

    public void update(){
        String txt1 = text2.getText();
        String txt2 = text1.getText();
        ArrayList<Loc> rez = new ArrayList<>();
        String[] array = txt1.split(";");
        String[] arraySters = txt2.split(";");
        for (int i = 1; i< arraySters.length ;i++) {
            loc.updateLoc(arraySters[i], arraySters[i], loc.getById(arraySters[i]).getPret(), false, loc.getById(arraySters[i]).getPret(), true);
        }
        String id = array[0];
        for (int i = 1; i< array.length ;i++) {
            double pret = loc.getById(array[i]).getPret();
            boolean stare = loc.getById(array[i]).getStare();
            if(stare == false)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wait!");
                alert.setHeaderText("The seats are already booked!");
                alert.setContentText("Please retry!");
                alert.showAndWait();
                text1.clear();
            }
            else {
                Loc l = new Loc(array[i], pret, false);

                loc.updateLoc(array[i], array[i], loc.getById(array[i]).getPret(), true, pret, false);
                rez.add(l);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done!");
                alert.setHeaderText("Your seats have been replaced!");
                alert.setContentText("Thank you!");
                alert.showAndWait();
                text1.clear();
                text2.clear();
                rezervare.updateRezervare(id,id, rezervare.getById(id).getLocuri(), rez);
            }
        }
    }
    @FXML
    void handleDone() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Spectator.fxml"));
        Parent root = loader.load();
        Stage stg = (Stage) Done.getScene().getWindow();
        stg.getScene().setRoot(root);
    }
}
