package com.example.boibank.view;

import com.example.boibank.Contoroler.Admin.Admincontroller;
import com.example.boibank.Contoroler.clients.Clientcontroller;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Viewfactory {

    private AccountType loginAccountType;

    // client veiw
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
     private AnchorPane dashboardView;
     private AnchorPane transactionsView;
     private AnchorPane accountsView;

     //Admin views

    private final ObjectProperty <AdminMenuOptions> adminSelectedItemmenu;
    private AnchorPane createClientView;
    private AnchorPane clientsView;
    private AnchorPane depositView;

     public Viewfactory(){
         this.loginAccountType = AccountType.CLIENTS;
         this.clientSelectedMenuItem = new SimpleObjectProperty<>();
          this.adminSelectedItemmenu = new SimpleObjectProperty<>();
     }


    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView(){
         if (dashboardView== null){
             try {
                 dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
             }
             catch (Exception e){
                 e.printStackTrace();
             }
         }
         return  dashboardView;
    }

    public AnchorPane getTransactionsView() {
        if (transactionsView == null){
            try {
                transactionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public  AnchorPane getAccountsView(){
         if (accountsView==null){
             try {
                 accountsView= new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
             } catch (Exception e){
                 e.printStackTrace();
             }
         }
        return accountsView;
    }

   /*
   * Admin Views sectionb
   * */

    public  ObjectProperty<AdminMenuOptions> getAdminSelectedItemmenu(){
        return adminSelectedItemmenu;
    }
public  AnchorPane getCreateClientView(){
    if (createClientView == null){
        try {
            createClientView =  new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateClient.fxml")).load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    return  createClientView;
}

public AnchorPane getClientsView(){
        if (clientsView== null){
            try {
                clientsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Clients.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return clientsView;
}


public AnchorPane getDepositView(){
        if (depositView==null){
            try{
                depositView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Deposit.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return depositView;
}

    public void showClientWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        Clientcontroller clientcontroller = new Clientcontroller();
        loader.setController(clientcontroller);
        Scene scene = null;
        scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Bank.png"))));
        stage.setResizable(false);
        stage.setTitle("BOB Bank");
        stage.show();
    }


    public void showAdminWindow() throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        Admincontroller controller= new Admincontroller();
        loader.setController(controller);
        Scene scene = null;

        scene = new Scene(loader.load());

        Stage  stage =  new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Bank.png"))));
        stage.setResizable(false);
        stage.setTitle("BOB Bank");
        stage.show();
    }

    public void showLoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Bank.png"))));
            stage.setResizable(false);
            stage.setTitle("BOB Bank");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeStage(Stage stage){
         stage.close();
  }
}

