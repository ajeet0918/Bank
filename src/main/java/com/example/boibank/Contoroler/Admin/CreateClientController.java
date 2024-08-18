package com.example.boibank.Contoroler.Admin;

import com.example.boibank.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField password_fld;
    public TextField aadhar_fld; // New field for Aadhar number
    public TextField pan_fld; // New field for PAN number
    public TextField mobile_fld; // New field for Mobile number
    public CheckBox pAddress_box;
    public Label pAddress_lbl;
    public CheckBox ch_acc_box;
    public TextField ch_amount_fld;
    public CheckBox sv_acc_box;
    public TextField sv_amount_fld;
    public Button create_client_btn;
    public Label error_lbl;
    private String payeeAddress;
    private boolean createcheckingAccountflag = false;
    private boolean createsavingsAccountflag = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_client_btn.setOnAction(event -> createClient());
        pAddress_box.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                payeeAddress = createPayeeAddress();
                onCreatePayeeAddress();
            }
        });
        ch_acc_box.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                createcheckingAccountflag = true;
            }
        });
        sv_acc_box.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                createsavingsAccountflag = true;
            }
        });
    }

    private void createClient() {
        // Create checking and savings accounts if selected
        if (createcheckingAccountflag) {
            createAccount("Checking");
        }
        if (createsavingsAccountflag) {
            createAccount("Savings");
        }

        // Retrieve values from the new fields
        String fName = fName_fld.getText();
        String lName = lName_fld.getText();
        String password = password_fld.getText();
        String aadharNumber = aadhar_fld.getText();
        String panNumber = pan_fld.getText();
        String mobileNumber = mobile_fld.getText();

        // Create client with the provided information
        Model.getInstance().getDatabaseDriver().createClient(fName, lName, payeeAddress, password, aadharNumber,panNumber,mobileNumber,LocalDate.now());

        // Update UI
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight:bold");
        error_lbl.setText("Client Created Successfully!");
        emptyFields();
    }

    // Method to create a checking or savings account
    public void createAccount(String accountType) {
        String amountText = ch_amount_fld.getText();
        if (!amountText.isEmpty()) {
            double balance = Double.parseDouble(amountText);
            // Generate Account number
            String firstSection = "4416101000";
            String lastSection = Integer.toString((new Random()).nextInt(9999) + 1000);
            String accountNumber = firstSection + " " + lastSection;
            // Create the Checking Account and Savings Account
            if (accountType.equals("Checking")) {
                Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress, accountNumber, 6, balance);
            } else {
                Model.getInstance().getDatabaseDriver().createSavingAccount(payeeAddress, accountNumber, 6, balance);
            }
        } else {
            // Handle case where amount field is empty
            // For example, you can display an error message or log a warning
            System.err.println("Amount field is empty. Unable to create account.");
        }
    }

    // Method to update UI with the created payee address
    private void onCreatePayeeAddress() {
        if (fName_fld.getText() != null && lName_fld.getText() != null) {
            pAddress_lbl.setText(payeeAddress);
        }
    }

    // Method to generate payee address
    private String createPayeeAddress() {
        int id = Model.getInstance().getDatabaseDriver().getLastAutoIncrementValue() + 1;
        char fChar = Character.toLowerCase(fName_fld.getText().charAt(0));
        return "@" + fChar + lName_fld.getText() + id;
    }

    // Method to clear input fields
    private void emptyFields() {
        fName_fld.setText("");
        lName_fld.setText("");
        password_fld.setText("");
        aadhar_fld.setText("");
        pan_fld.setText("");
        mobile_fld.setText("");
        pAddress_box.setSelected(false);
        pAddress_lbl.setText("");
        ch_acc_box.setSelected(false);
        ch_amount_fld.setText("");
        sv_acc_box.setSelected(false);
        sv_amount_fld.setText("");
    }
}

