package com.example.demo;

import domain.Loc;
import domain.Rezervare;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.SeatOccupiedException;
import service.ServiceLoc;
import service.ServiceRezervare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


public class AddRes {
    @FXML
    Button Done;

    @FXML
    TextField text;

    ServiceRezervare rezervare = new ServiceRezervare();
    ServiceLoc loc = new ServiceLoc();

    public static int generateUniqueId() {
        // Generăm un UUID random
        UUID uuid = UUID.randomUUID();
        // Extragem primii 32 de biți din UUID pentru a obține un int
        int id = (int) (uuid.getMostSignificantBits() & Integer.MAX_VALUE);
        return id;
    }
    @FXML
    public void add() throws SeatOccupiedException {
        String txt = text.getText();
        ArrayList<Loc> rez = new ArrayList<>();
        String[] array = txt.split(";");
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
                 text.clear();
             }
             else {
                 loc.updateLoc(array[i], array[i], loc.getById(array[i]).getPret(), true, pret, false);
                 Loc l = new Loc(array[i], pret, false);
                 rez.add(l);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Done!");
                 alert.setHeaderText("Your seats have been booked!");
                 alert.setContentText("Thank you!");
                 alert.showAndWait();
                 text.clear();
             }
        }
        rezervare.addRezervare(id, rez);

    }
    @FXML
     private void handleDone() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Spectator.fxml"));
        Parent root = loader.load();
        Stage stg = (Stage) Done.getScene().getWindow();
        stg.getScene().setRoot(root);
    }


}
