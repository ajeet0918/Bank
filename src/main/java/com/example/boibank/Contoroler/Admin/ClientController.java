package com.example.boibank.Contoroler.Admin;

import com.example.boibank.models.Client;
import com.example.boibank.models.Model;
import com.example.boibank.view.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public ListView<Client> clients_listview;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initDAta();
        clients_listview.setItems(Model.getInstance().getClients());
        clients_listview.setCellFactory(e -> new ClientCellFactory());
    }
    private void initDAta(){
        if (Model.getInstance().getClients().isEmpty()){
            Model.getInstance().setClients();
        }
    }
}
