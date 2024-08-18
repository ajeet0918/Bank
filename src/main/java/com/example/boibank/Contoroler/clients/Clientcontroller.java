package com.example.boibank.Contoroler.clients;

import com.example.boibank.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.boibank.view.ClientMenuOptions.*;

public class Clientcontroller implements Initializable {
    public BorderPane client_parent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewfactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            if (newVal.equals(TRANSACTIONS)) {
                client_parent.setCenter(Model.getInstance().getViewfactory().getTransactionsView());
            } else if (newVal.equals(ACCOUNTS)) {
                client_parent.setCenter(Model.getInstance().getViewfactory().getAccountsView());
            } else if (newVal.equals(DASHBOARD)) {
                client_parent.setCenter(Model.getInstance().getViewfactory().getDashboardView());
            }

        });
    }
}
