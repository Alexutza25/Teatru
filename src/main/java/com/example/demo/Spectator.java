package com.example.demo;

import domain.Loc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import service.ServiceLoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Spectator {
    @FXML
    Button logoutBut;

    ObservableList<String> list = FXCollections.observableList(new ArrayList<>());

    @FXML
    public ListView listMessage = new ListView<>();

    @FXML
    Button addRes;

    @FXML
    Button updRes;

    @FXML
    Button delRes;

    ServiceLoc loc = new ServiceLoc();

    @FXML
    void add(){
        addRes.setOnAction(loadWindow("AddRes.fxml"));
    }
    @FXML
    void update(){
        updRes.setOnAction(loadWindowUpd("UpdateRes.fxml"));
    }
    @FXML
    void delete(){
        delRes.setOnAction(loadWindowDel("DeleteRes.fxml"));

    }
    @FXML
    private EventHandler<ActionEvent> loadWindow(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stg = (Stage) addRes.getScene().getWindow();
            stg.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private EventHandler<ActionEvent> loadWindowUpd(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stg = (Stage) updRes.getScene().getWindow();
            stg.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private EventHandler<ActionEvent> loadWindowDel(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stg = (Stage) delRes.getScene().getWindow();
            stg.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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


    @FXML
    void handleLogout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Stage stg = (Stage) logoutBut.getScene().getWindow();
        stg.getScene().setRoot(root);
    }
}
