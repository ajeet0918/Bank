package com.example.boibank.Contoroler;
//import javafx.scene.control.CheckBox;

import com.example.boibank.models.Model;
import com.example.boibank.view.AccountType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Logincontroler implements Initializable {

    public TextField payee_address_fld;
    public Label password_lb;
    public TextField password_fld;
   // public Button login_btn;
    public Label error_lbl;
    public Label payee_address_lb;
    public ChoiceBox<AccountType> acc_selector;



    @FXML
    public Button login_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENTS, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewfactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());
        login_btn.setOnAction(this::handle);
}
    private void onLogin() throws Exception {
        Stage stage = (Stage) acc_selector.getScene().getWindow();
        String username = payee_address_fld.getText();
        String password = password_fld.getText();

        if (username.isEmpty() || password.isEmpty()) {
            error_lbl.setText("Please enter both username and password");
            return; // Exit the method without further processing
        }

        if (Model.getInstance().getViewfactory().getLoginAccountType() == AccountType.CLIENTS) {
            // Evaluate Client Login Credential
            Model.getInstance().evaluateClientCred(username, password);
            if (Model.getInstance().getClinetLoginSuccessFlag()) {
                Model.getInstance().getViewfactory().showClientWindow();
                // close login stage
                Model.getInstance().getViewfactory().closeStage(stage);
            } else {
                error_lbl.setText("Unidentified userId or password");
                password_fld.clear(); // Clear password field for new input
                payee_address_fld.clear();
            }
        } else {
            // Evaluate Admin login
            Model.getInstance().evaluateAdminCred(username, password);
            if (Model.getInstance().getAdminLoginSuccesFlag()) {
                Model.getInstance().getViewfactory().showAdminWindow();
                // Close the Admin window
                Model.getInstance().getViewfactory().closeStage(stage);
            } else {
                error_lbl.setText("Unidentified userId or password");
                password_fld.clear();
                payee_address_fld.clear();
            }
        }
    }

    private void handle(ActionEvent event) {
        try {
            onLogin();
        } catch (Exception e) {
//throw new RuntimeException(e);
        }
    }
    private void setAcc_selector(){
        Model.getInstance().getViewfactory().setLoginAccountType(acc_selector.getValue());
        if (acc_selector.getValue()== AccountType.ADMIN){
            payee_address_lb.setText("Username:");
        }else{
            payee_address_lb.setText("Payee Address:");
        }
    }

}















































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































