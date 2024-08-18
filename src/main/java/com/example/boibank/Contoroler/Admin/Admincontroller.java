package com.example.boibank.Contoroler.Admin;

import com.example.boibank.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Admincontroller implements Initializable {
    public BorderPane admin_parent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewfactory().getAdminSelectedItemmenu().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case CLIENT -> admin_parent.setCenter(Model.getInstance().getViewfactory().getClientsView());
                case DEPOSIT -> admin_parent .setCenter(Model.getInstance().getViewfactory().getDepositView());
                default -> admin_parent.setCenter(Model.getInstance().getViewfactory().getCreateClientView());
            }
        });
    }
}
