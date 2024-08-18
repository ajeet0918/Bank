package com.example.boibank.Contoroler.clients;

import com.example.boibank.models.Client;
import com.example.boibank.models.Model;
import com.example.boibank.models.Transaction;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardControler implements Initializable {
    public Text user_name;
    public Label login_date;
    public Label checking_balance;
    public Label checking_acc_num;
    public Label savings_bal;
    public Label savings_acc;
    public Label income_lbl;
    public Label expanse_lbl;
    public ListView<Transaction> transaction_listview; // Changed to ListView<String>
    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  bindData();
    }
   /* private void bindData() {
        ObservableList<Client> clients = Model.getInstance().getClients();
        Client client = clients.isEmpty() ? null : clients.get(0);

        user_name.textProperty().bind(Bindings.concat("Hi, ").concat(client != null ? client.firstnameProperty() : new SimpleStringProperty("No client available")));
        login_date.setText("Today, " + LocalDate.now());

        if (client != null && client.checkingaccountProperty().get() != null) {
            checking_balance.textProperty().bind(client.checkingaccountProperty().get().balanceProperty().asString());
            checking_acc_num.textProperty().bind(client.checkingaccountProperty().get().accountNumberProperty());
        } else {
            checking_balance.setText("N/A");
            checking_acc_num.setText("N/A");
        }

        if (client != null && client.savingsaccountProperty().get() != null) {
            savings_bal.textProperty().bind(client.savingsaccountProperty().get().balanceProperty().asString());
            savings_acc.textProperty().bind(client.savingsaccountProperty().get().accountNumberProperty());
        } else {
            savings_bal.setText("N/A");
            savings_acc.setText("N/A");
        }
    }
*/


}
