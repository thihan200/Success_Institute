package lk.ijse.successInstitute.controller;

import com.sun.xml.internal.bind.CycleRecoverable;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.Context;

import java.io.IOException;
import java.util.Optional;

public class DashboardFormController {

    public AnchorPane context;

    public void initialize() throws IOException {
        loadDefault();
    }

    private void loadDefault() throws IOException {
        setUi("DefaultForm");
    }

    private void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location+".fxml")));
    }

    public void dashboardOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("DefaultForm");
    }

    public void AttendanceOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("MarkAttendance");
    }

    public void PaymentOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("MakePayment");
    }

    public void StudentOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("Student");
    }

    public void BatchOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("Batch");
    }

    public void ClassroomOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("Classroom");
    }

    public void SubjectOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("Subject");
    }

    public void TeacherOnAction(MouseEvent mouseEvent) throws IOException {
        toEnderTeacher();
    }

    public void EmployeeOnAction(MouseEvent mouseEvent) throws IOException {
        toEnderEmployee();
    }


    public void TimetableOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("Timetable");
    }

    private void toEnderTeacher() throws IOException {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Enter Password");
        dialog.setHeaderText("Enter Password to continue");
        dialog.setGraphic(new Circle(15, Color.RED)); // Custom graphic
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        PasswordField pwd = new PasswordField();
        HBox content = new HBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        content.getChildren().addAll(new Label("Enter Password"), pwd);
        dialog.getDialogPane().setContent(content);
        Optional<String> results = dialog.showAndWait();

        if (pwd.getText().toString().length() > 0) {
            if (pwd.getText().equalsIgnoreCase("admin123")) {
                new Alert(Alert.AlertType.CONFIRMATION, "Log Success", ButtonType.OK).show();
                setUi("Teacher");
            } else {
                new Alert(Alert.AlertType.WARNING, "Wrong Password", ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No Password Input", ButtonType.OK).show();
        }
    }

    private void toEnderEmployee() throws IOException {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Enter Password");
        dialog.setHeaderText("Enter Password to continue");
        dialog.setGraphic(new Circle(15, Color.RED)); // Custom graphic
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        PasswordField pwd = new PasswordField();
        HBox content = new HBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        content.getChildren().addAll(new Label("Enter Password"), pwd);
        dialog.getDialogPane().setContent(content);
        Optional<String> results = dialog.showAndWait();

        if (pwd.getText().toString().length() > 0) {
            if (pwd.getText().equalsIgnoreCase("admin123")) {
                new Alert(Alert.AlertType.CONFIRMATION, "Log Success", ButtonType.OK).show();
                setUi("Employee");
            } else {
                new Alert(Alert.AlertType.WARNING, "Wrong Password", ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "No Password Input", ButtonType.OK).show();
        }

    }

    public void logoutOnAction(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirmation Logout");
        alert.setContentText("Are you sure to logout ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/loginForm.fxml"))));
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }else{
            //new Alert(Alert.AlertType.WARNING, "Logout Failed", ButtonType.OK).show();
        }
    }

}
