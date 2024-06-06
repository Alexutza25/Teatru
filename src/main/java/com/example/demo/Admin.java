package com.example.demo;

import domain.Loc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceLoc;
import service.ServiceSpectator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Admin {
    @FXML
    public ListView listMessage;

    @FXML
    public Button show;

    @FXML
    public TableColumn<Spectator, String> tabelemail = new TableColumn<Spectator, String>("email");

    @FXML
    public TableColumn<Spectator, String> tabelparola = new TableColumn<Spectator, String>("password");

    @FXML
    public TableColumn<Spectator, String> tabelnume = new TableColumn<Spectator, String>("name");

    @FXML
    public TableView table;

    @FXML
    Button logoutBut;

    @FXML
    TextField email;

    @FXML
    TextField password;

    @FXML
    TextField firstName;

    @FXML
    TextField lastName;

    @FXML
    RadioButton spectatorRadio;

    @FXML
    RadioButton adminRadio;

    @FXML
    TextField rowNumber;

    @FXML
    TextField price;

    @FXML
    TextField status;

    ObservableList<String> list = FXCollections.observableList(new ArrayList<>());

    ObservableList<domain.Spectator> accounts = FXCollections.observableArrayList();

    ServiceSpectator spectator = new ServiceSpectator();
    ServiceLoc loc = new ServiceLoc();

    @FXML
    private void addSpectator(){
        String eml = email.getText();
        String prenume = firstName.getText();
        String nume = lastName.getText();
        String parola = password.getText();
        ToggleGroup toggleGroup = new ToggleGroup();
        adminRadio.setToggleGroup(toggleGroup);
        spectatorRadio.setToggleGroup(toggleGroup);
        if(!spectator.findById(eml)) {

            if (toggleGroup.getSelectedToggle() == adminRadio) {
                spectator.addSpectator(eml, prenume, nume, parola, true);
            }
        else {
            if (toggleGroup.getSelectedToggle() == spectatorRadio) {
                spectator.addSpectator(eml,prenume,nume, parola,false);
            }
            alertSpectator();
        }
        }

    }

    private void alertSpectator() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done");
        alert.setHeaderText("Done!");
        alert.setContentText("Done!");
        alert.showAndWait();
        email.clear();
        firstName.clear();
        lastName.clear();
        password.clear();
    }

    @FXML
    private void updateSpectator() {
        String eml = email.getText();
        String prenume_nou = firstName.getText();
        String nume_nou = lastName.getText();
        String parola_noua = password.getText();
        String prenume_vechi = spectator.getById(eml).getPrenume();
        String nume_vechi = spectator.getById(eml).getNume();
        String parola_veche = spectator.getById(eml).getParola();
        boolean admin = spectator.getById(eml).getAdmin();
        ToggleGroup toggleGroup = new ToggleGroup();
        adminRadio.setToggleGroup(toggleGroup);
        spectatorRadio.setToggleGroup(toggleGroup);
        if (spectator.findById(eml)) {

            if (toggleGroup.getSelectedToggle() == adminRadio) {
                spectator.updateSpectator(eml, nume_vechi, prenume_vechi, parola_veche, admin,
                        nume_nou, prenume_nou, parola_noua, true);
            }
         else {
            if (toggleGroup.getSelectedToggle() == spectatorRadio) {
                spectator.updateSpectator(eml, nume_vechi, prenume_vechi, parola_veche, admin,
                        nume_nou, prenume_nou, parola_noua, false);
            }
                alertSpectator();
        }
        }


    }

    @FXML
    private void deleteSpectator()
    {
        String eml = email.getText();
        if(spectator.findById(eml)){
            spectator.removeSpectator(eml);
        }
        alertSpectator();
    }

    @FXML
    private void findSpectator()
    {
        String eml = email.getText();
        if(spectator.findById(eml))
        {
            firstName.setText(spectator.getById(eml).getPrenume());
            lastName.setText(spectator.getById(eml).getNume());
            password.setText(spectator.getById(eml).getParola());
        }
    }

    @FXML
    private void addLoc()
    {
        String id = rowNumber.getText();
        double pret = Double.parseDouble(price.getText());
        boolean stare = Boolean.parseBoolean(status.getText());
        if(!loc.findById(id))
        {
            loc.addLoc(id,pret,stare);
            alertLoc();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wait");
            alert.setHeaderText("Ups!");
            alert.setContentText("Already exists!");
            alert.showAndWait();
            rowNumber.clear();
            price.clear();
            status.clear();
        }
    }
    @FXML
    private void deleteLoc()
    {
        String id = rowNumber.getText();
        if(loc.findById(id))
        {
            loc.removeLoc(id);
            alertLoc();
        }
    }

    private void alertLoc() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done");
        alert.setHeaderText("Done!");
        alert.setContentText("Done!");
        alert.showAndWait();
        rowNumber.clear();
        price.clear();
        status.clear();
    }

    @FXML
    private void updateLoc(){
        String id = rowNumber.getText();
        double pret_nou = Double.parseDouble(price.getText());
        boolean stare_noua = Boolean.parseBoolean(status.getText());
        double pret_vechi = loc.getById(id).getPret();
        boolean stare_veche = loc.getById(id).getStare();
        if(loc.findById(id))
        {
           loc.updateLoc(id,id,pret_vechi,stare_veche,pret_nou,stare_noua);
            alertLoc();
        }
    }

    @FXML
    private void findLoc()
    {
        String id = rowNumber.getText();
        if(loc.findById(id))
        {
            price.setText(Double.toString(loc.getById(id).getPret()));
            status.setText(Boolean.toString(loc.getById(id).getStare()));
        }
    }

    @FXML
    private void clearLoc()
    {
        Collection<Loc> locuri = loc.getAll();
        for(Loc l:locuri)
        {
            loc.updateLoc(l.getId(),l.getId(),l.getPret(),l.getStare(),l.getPret(),true);
        }
        alertLoc();
    }

    @FXML
    private void showAllLocuri()
    {
        list.clear();
        Collection<Loc> locuri = loc.getAll();
        for(Loc l:locuri)
        {
            list.add(l.getId() + " " + Double.toString(l.getPret()) + " " + Boolean.toString(l.getStare()));
        }
        listMessage.setItems(list);
    }


    public void coloumnView(){
        tabelemail.setCellValueFactory(new PropertyValueFactory<Spectator,String>("id"));
        tabelparola.setCellValueFactory(new PropertyValueFactory<Spectator,String>("parola"));
        tabelnume.setCellValueFactory(new PropertyValueFactory<Spectator,String>("nume"));

    }
    @FXML
    public void showAllSpectator(ActionEvent actionEvent)
    {
        coloumnView();
            accounts.clear();
            table.refresh();
            Collection<domain.Spectator> conturi = spectator.getAllS();
            for(domain.Spectator c:conturi){
                accounts.add(c);
            }
            table.setItems(accounts);



        }
    @FXML
    void handleLogout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Stage stg = (Stage) logoutBut.getScene().getWindow();
        stg.getScene().setRoot(root);


    }
}
