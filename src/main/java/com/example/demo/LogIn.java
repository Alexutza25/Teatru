package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class LogIn {
    @FXML
    RadioButton spectatorRadioLogin;

    @FXML
    RadioButton adminRadioLogin;

    @FXML
    Button loginBut;

    @FXML
    TextField loginEmailText;

    @FXML
    PasswordField loginPasswordText;

    @FXML
    Button signupBut;
    ServiceSpectator spectator = new ServiceSpectator();

    @FXML
    TextField signupEmailText;

    @FXML
    TextField signupFirstNameText;

    @FXML
    TextField signupLastNameText;


    @FXML
    TextField signupPasswordText;

    @FXML
    TextField signupConfirmPasswordText;
    @FXML

    void handleLogin() throws IOException{
        ToggleGroup toggleGroup = new ToggleGroup();

        String username = loginEmailText.getText();
        String parola = loginPasswordText.getText();


        adminRadioLogin.setToggleGroup(toggleGroup);
        spectatorRadioLogin.setToggleGroup(toggleGroup);

        loginBut.setOnAction(e -> {
            if (toggleGroup.getSelectedToggle() == adminRadioLogin) {

                if (spectator.findById(username) && spectator.getById(username).getParola().equals(parola)) {
                    if (spectator.getById(username).getAdmin())
                    {
                        loadWindow("Admin.fxml");
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Wait!");
                    alert.setHeaderText("The password or the email adress is incorrect");
                    alert.setContentText("Please retry!");
                    alert.showAndWait();
                    loginEmailText.clear();
                    loginPasswordText.clear();
                }
            }
            else if (toggleGroup.getSelectedToggle() == spectatorRadioLogin) {
                if(spectator.findById(username) && spectator.getById(username).getParola().equals(parola)){
                    if(!spectator.getById(username).getAdmin())
                    {
                        loadWindow("Spectator.fxml");
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Wait!");
                    alert.setHeaderText("The password or the email adress is incorrect");
                    alert.setContentText("Please retry!");
                    alert.showAndWait();
                    loginEmailText.clear();
                    loginPasswordText.clear();
                }
            }
        });

    }

    @FXML
    private void handleSignin() throws IOException
    {
        String id = signupEmailText.getText();
        String firstName = signupFirstNameText.getText();
        String lastName = signupLastNameText.getText();
        String parola = signupPasswordText.getText();
        String confparola = signupConfirmPasswordText.getText();
        if(!spectator.findById(id) && parola.equals(confparola))
        {
            spectator.addSpectator(id,lastName,firstName,parola,false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congrats");
            alert.setHeaderText("Registration");
            alert.setContentText("The user was registered !");
            alert.showAndWait();
            signupEmailText.clear();
            signupFirstNameText.clear();
            signupLastNameText.clear();
            signupPasswordText.clear();
            signupConfirmPasswordText.clear();
        }

    }

    private void loadWindow(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stg = (Stage) loginBut.getScene().getWindow();
            stg.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}