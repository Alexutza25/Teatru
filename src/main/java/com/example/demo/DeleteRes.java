package com.example.demo;

import domain.Loc;
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

import static com.example.demo.AddRes.generateUniqueId;

public class DeleteRes {
    @FXML
    Button Done;

    @FXML
    TextField text;

    ServiceRezervare rezervare = new ServiceRezervare();
    ServiceLoc loc = new ServiceLoc();

    @FXML
    private void delete()
    {
        String txt = text.getText();
        ArrayList<Loc> rez = new ArrayList<>();
        String[] array = txt.split(";");
        String id = array[0];
        rezervare.removeRezervare(id);
        for(int i = 1; i< array.length; i++)
        {
            Loc l = loc.getById(array[i]);
            loc.updateLoc(l.getId(),l.getId(),l.getPret(),l.getStare(),l.getPret(),true);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done!");
        alert.setHeaderText("Your reservation has been canceled!");
        alert.setContentText("Thank you!");
        alert.showAndWait();
        text.clear();


    }
    @FXML
    private void handleDone() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Spectator.fxml"));
        Parent root = loader.load();
        Stage stg = (Stage) Done.getScene().getWindow();
        stg.getScene().setRoot(root);
    }
}
