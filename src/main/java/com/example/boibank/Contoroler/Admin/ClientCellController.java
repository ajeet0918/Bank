package com.example.boibank.Contoroler.Admin;

import com.example.boibank.models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController  implements Initializable {


    public Label fname_lbl;
    public Label lName_lbl;
    public Label pAddress_lbl;
    public Label ch_acc_lbl;
    public Label sv_acc_lbl;
    public Label date_lbl;
    public Button delete_btn;

    private final Client client;

    public ClientCellController (Client client){

        this.client = client;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fname_lbl.textProperty().bind(client.firstnameProperty());
        lName_lbl.textProperty().bind(client.lastnameProperty());
        pAddress_lbl.textProperty().bind(client.payeeadressProperty());
        ch_acc_lbl.textProperty().bind(client.checkingaccountProperty().asString());
        sv_acc_lbl.textProperty().bind(client.savingsaccountProperty().asString());
        date_lbl.textProperty().bind(client.datecreatedProperty().asString());
    }
}
