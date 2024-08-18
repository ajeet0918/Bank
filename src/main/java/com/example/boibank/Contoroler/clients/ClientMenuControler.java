package com.example.boibank.Contoroler.clients;

import com.example.boibank.models.Model;
import com.example.boibank.view.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuControler implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
   addListeners();
    }

    private void addListeners(){

        dashboard_btn.setOnAction(event ->onDashboard());
        transaction_btn.setOnAction(event -> onTransactions());
        accounts_btn.setOnAction(event -> onAccounts());
        logout_btn.setOnAction(event ->onLogout() );
    }


    private void onDashboard(){
        Model.getInstance().getViewfactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }


    private void onTransactions(){
        Model.getInstance().getViewfactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);

    }

    private  void onAccounts(){
        Model.getInstance().getViewfactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }
  private void onLogout(){
      Stage stage = (Stage) dashboard_btn.getScene().getWindow();
      // close the client window
      Model.getInstance().getViewfactory().closeStage(stage);
      // show login windo
          Model.getInstance().getViewfactory().showLoginWindow();

      //set clientlogin success false
      Model.getInstance().setClinetLoginSuccessFlag(false);
    }
}
