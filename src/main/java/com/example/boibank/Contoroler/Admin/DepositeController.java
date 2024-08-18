package com.example.boibank.Contoroler.Admin;

import com.example.boibank.de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import com.example.boibank.models.Client;
import com.example.boibank.models.Model;
import com.example.boibank.view.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositeController implements Initializable {


    public TextField pAddress_fld;
    public Button search_btn;
    public ListView <Client>result_listview;
    public TextField amount_fld;
    public  Button deposit_btn;

    private  Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_btn.setOnAction(event -> onclientsearch());
        deposit_btn.setOnAction(event -> onDeposite());
    }

    private void onclientsearch(){
        ObservableList<Client> searchresults = Model.getInstance().searchclient(pAddress_fld.getText());
        result_listview .setCellFactory(e-> new ClientCellFactory());
        client = searchresults.get(0);
    }

    private void onDeposite(){
        double amount = Double.parseDouble(amount_fld.getText());
        double newbalance = amount + client.savingsaccountProperty().get().balanceProperty().get();
        if (amount_fld.getText()!=null){
            Model.getInstance().getDatabaseDriver().depositesavings(client.payeeadressProperty().get(), newbalance);
        }
    }
    private void emptyfields(){
        pAddress_fld.setText("");
        amount_fld.setText("");
    }
}
