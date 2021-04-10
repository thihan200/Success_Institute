package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtpwd;
    public AnchorPane root;

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText().trim();
        String password = txtpwd.getText().trim();

        if(userName.length()>0 && password.length()>0){
            if(userName.equalsIgnoreCase("success")&&password.equalsIgnoreCase("12345")){
                //Start login
                Stage window = (Stage) this.root.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/dashboardForm.fxml"))));
                window.centerOnScreen();

                //end login
            }else {
                new Alert(Alert.AlertType.WARNING, "Wrong Username or Password",
                        ButtonType.OK).show();

            }

        }else{
            new Alert(Alert.AlertType.WARNING,"UserName or Password Empty",ButtonType.OK).show();
        }

    }

    public void loginOnActionm(ActionEvent actionEvent) throws IOException {
        String UserName = txtUserName.getText().trim();
        String password = txtpwd.getText().trim();

        if(UserName.length()>0 && password.length()>0){
            if(UserName.equalsIgnoreCase("success")&&password.equalsIgnoreCase("12345")){
                //Start login
                Stage window = (Stage) this.root.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/dashboardForm.fxml"))));
                window.centerOnScreen();

                //end login
            }else {
                new Alert(Alert.AlertType.WARNING, "Wrong Username or Password",
                        ButtonType.OK).show();

            }

        }else{
            new Alert(Alert.AlertType.WARNING,"UserName or Password Empty",ButtonType.OK).show();
        }
    }

    public void usenameOnAction(ActionEvent actionEvent) {
        txtpwd.requestFocus();
    }
}
