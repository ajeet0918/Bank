package com.example.boibank.Contoroler.Admin;

import com.example.boibank.models.Model;
import com.example.boibank.view.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenu  implements Initializable {
    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListerners();
    }

    private void addListerners() {
        create_client_btn.setOnAction(event -> onCreateClient());
        clients_btn.setOnAction(event -> onClients());
        deposit_btn.setOnAction(event -> onDeposit());
        logout_btn.setOnAction(event -> onLogout());

    }

    private void onCreateClient() {
        Model.getInstance().getViewfactory().getAdminSelectedItemmenu().set(AdminMenuOptions.CREATE_CLIENT);
    }

    private void onClients() {
        Model.getInstance().getViewfactory().getAdminSelectedItemmenu().set(AdminMenuOptions.CLIENT);
    }
    private void onDeposit() {
      Model.getInstance().getViewfactory().getAdminSelectedItemmenu().set(AdminMenuOptions.DEPOSIT);
    }
    private void onLogout(){
        Stage stage = (Stage)clients_btn.getScene().getWindow();
        // close the Admin window
        Model.getInstance().getViewfactory().closeStage(stage);
        // show login window
        try {
            Model.getInstance().getViewfactory().showLoginWindow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //set clientlogin success false
        Model.getInstance().setAdminLoginSuccessFlag(false);
    }
}