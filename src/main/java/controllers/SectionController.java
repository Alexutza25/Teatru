package controllers;

import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.w3c.dom.events.Event;
import service.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class SectionController implements Observer {

    Stage mainStage;
    Loc rootUser;
    ServiceLoc services;

    ObservableList<Loc> modelAllDrugs = FXCollections.observableArrayList();
    ObservableList<Rezervare> modelOrderItems = FXCollections.observableArrayList();

    @FXML
    TableView<Loc> tableViewDrugs;
    @FXML
    TableColumn<Loc, String> tableColumnName;
    @FXML
    TableColumn<Loc, Float> tableColumnPrice;
    @FXML
    TableColumn<Loc, String> tableColumnDescription;
    @FXML
    TextField quantityTextField;

    @FXML
    TableView<Rezervare> tableOrderItems;
    @FXML
    TableColumn<Rezervare, String> tableColumnOrderItemName;
    @FXML
    TableColumn<Rezervare, Integer> tableColumnOrderItemQuantity;



    public void setServices(Stage primaryStage, ServiceLoc service, Loc rootUser){
        this.mainStage = primaryStage;
        this.services = service;
        this.rootUser = rootUser;
        service.addLoc(rootUser.getId(), rootUser.getPret(), rootUser.getStare());
        initModelAllDrugs();
    }

    @FXML
    void initialize(){
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Loc, String >("name"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<Loc, Float>("parola"));
        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<Loc, String>("username"));
        tableViewDrugs.setItems(modelAllDrugs);

        tableColumnOrderItemName.setCellValueFactory(new PropertyValueFactory<Rezervare, String>("row, place"));
        tableColumnOrderItemQuantity.setCellValueFactory(new PropertyValueFactory<Rezervare, Integer>("price"));
        tableOrderItems.setItems(modelOrderItems);
    }

    private void initModelAllDrugs(){
        services.getAll().stream().forEach(System.out::println);
        modelAllDrugs.setAll(services.getAll());
    }



    @FXML
    private void handleAddOrderDrug(){
        Loc drug = tableViewDrugs.getSelectionModel().getSelectedItem();
        if(drug == null)
            return;
        ArrayList<Loc> drugs = new ArrayList<>();
        drugs.add(drug);
        try{
            Integer quantity = Integer.valueOf(quantityTextField.getText());
            Rezervare orderItem = new Rezervare(drug.getId(), drugs);
            modelOrderItems.add(orderItem);
        }catch (RuntimeException ex){
            //MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }

    @FXML
    private void handleAddOrder(){
        /*Integer totalQuantity = 0;
        for(OrderItem orderItem: modelOrderItems) {
            System.out.println("LLLLLLLLLLLLLPPPPPPPPPPPPP: " + orderItem);
            totalQuantity += orderItem.getQuantity();
        }
        try{
            Order order = new Order(totalQuantity, rootUser, Status.PENDING);
            services.addOrder(order, modelOrderItems);
            MessageAlert.showMessage(mainStage, Alert.AlertType.INFORMATION, "Add Order", "The order was added successfully !");
        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }*/
    }

    @FXML
    private void handleRemoveOrderItem(){
        /*OrderItem orderItem = tableOrderItems.getSelectionModel().getSelectedItem();
        if(orderItem == null)
            return;
        modelOrderItems.remove(orderItem);*/
    }

    @FXML
    private void handleYourOrders() throws IOException {
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/ViewOrdersSection.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Scene previousScene = mainStage.getScene();
        mainStage.setScene(newScene);
        OrderSectionController orderSectionController = loader.getController();
        orderSectionController.setServices(mainStage, services, this.rootUser, previousScene);
        mainStage.show();*/
    }

    @FXML
    /*private void handleLogout(){
        try{
            services.logout(rootUser);
            mainStage.close();
        }catch (RuntimeException ex){
            MessageAlert.showErrorMessage(mainStage, ex.getMessage());
        }
    }*/

    @Override
    public void update(Observable o, Object arg) {

    }
}