package lk.ijse.successInstitute.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import lk.ijse.successInstitute.bo.BOFactory;
import lk.ijse.successInstitute.bo.custom.PaymentBO;
import lk.ijse.successInstitute.db.DBConnection;
import lk.ijse.successInstitute.dto.PaymentDTO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.view.tm.PaymentTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class MakePaymentController {


    public JFXTextField txtRegfee;
    public JFXTextField txtPid;
    public TableView<PaymentTM> tblPayment;
    public JFXComboBox cmbStudentId;
    public JFXTextField txtdate;
    public JFXComboBox cmbSubjectName;
    public JFXTextField txtSearch;
    public JFXButton btnClear;
    public TableColumn colPid;
    public TableColumn colDate;
    public TableColumn colSname;
    public TableColumn colRegfee;
    public TableColumn colStuid;
    public TableColumn colOperate;
    public JFXTextField txtCash;
    public JFXTextField txtCBalance;
    public JFXTextField txtTotall;
    public JFXButton btnPayment;
    public TableColumn colTotal;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);

    public void initialize() throws Exception {
        colPid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        colRegfee.setCellValueFactory(new PropertyValueFactory<>("regfee"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSname.setCellValueFactory(new PropertyValueFactory<>("subname"));
        colStuid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblPayment.setItems(getAllPayment());

        //bot
        tblPayment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnPayment.setText("Update Payment");

            txtPid.setText(newValue.getPid());
            txtRegfee.setText(newValue.getRegfee());
            txtdate.setText(newValue.getDate());
            cmbSubjectName.setValue(newValue.getSubname());
            cmbStudentId.setValue(newValue.getSid());
            txtTotall.setText(String.valueOf(newValue.getAmount()));

        });

        getDateTime();
        generateLastId();
        loadStudentID();
        loadSubjectName();
    }

    public void clearAllFields() throws Exception {
        txtPid.setText(generateLastId());
        cmbSubjectName.setPromptText("");
        cmbStudentId.setPromptText("");
        txtTotall.setText("");
        txtCash.setText("");
        txtCBalance.setText("");
    }


    private void getDateTime() throws Exception {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd ");
        txtdate.setText(sdf.format(d));
    }

    public String generateLastId() throws Exception {
        String lastId = getLastPaymentId();
        if (lastId != null) {
            lastId = lastId.split("[A-Z]")[1];
            if (Integer.parseInt(lastId) <= 9) {
                lastId = "P00" + (Integer.parseInt(lastId) + 1);
                txtPid.setText(lastId);
            } else if (Integer.parseInt(lastId) <= 99) {
                lastId = "P0" + (Integer.parseInt(lastId) + 1);
                txtPid.setText(lastId);
            } else {
                lastId = "P" + (Integer.parseInt(lastId) + 1);
                txtPid.setText(lastId);
            }
        } else {
            txtPid.setText("P1");
        }
        return lastId;
    }

    private ObservableList<PaymentTM> getAllPayment() throws Exception {
        ArrayList<PaymentDTO> paymentDTOS = loadAllPayment();
        ObservableList<PaymentTM> tmObservableList = FXCollections.observableArrayList();
        for (PaymentDTO dto : paymentDTOS) {
            Button btn = new Button("Delete");
           btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(new PaymentTM(
                    dto.getPid(),
                    dto.getRegfee(),
                    dto.getDate(),
                    dto.getSubname(),
                    dto.getSid(),
                    dto.getAmount(),
                    btn
            ));

            btn.setOnAction((e) -> {
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
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setHeaderText("Confirmation Delete");
                        alert.setContentText("Are you sure to Delete ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            String pid = txtPid.getText();
                            try {
                                boolean isDeleted = delete(pid);
                                if (isDeleted) {
                                    //new Alert(Alert.AlertType.CONFIRMATION, "Delete Success", ButtonType.OK).show();
                                    tblPayment.setItems(getAllPayment());
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Delete Failed", ButtonType.OK).show();
                                }
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Password Incorrect", ButtonType.OK).show();

                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "No Password Input", ButtonType.OK).show();
                }


            });
            tblPayment.setItems(tmObservableList);
        }
        return tmObservableList;
    }

    public void PaymentOnAction(ActionEvent actionEvent) {
        String pid = txtPid.getText();
        String regfee = txtRegfee.getText();
        String date = txtdate.getText();
        String subject = String.valueOf(cmbSubjectName.getValue());
        String stuId = String.valueOf(cmbStudentId.getValue());
        Double amount = Double.parseDouble(txtTotall.getText());

        PaymentDTO paymentDTO = new PaymentDTO(pid, regfee, date, subject, stuId, amount);
        if (pid.length() > 0 && regfee.length() > 0 && date.length() > 0 && subject.length() > 0 && stuId.length() > 0 && amount.doubleValue() > 0) {
            if (btnPayment.getText().equalsIgnoreCase("Add Payment")) {
                try {
                    boolean isValidate = validation();
                    if (isValidate) {
                        boolean isAdded = add(paymentDTO);
                        if (isAdded) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Payment Made and Report Generated", ButtonType.OK).show();
                            generateReport();
                            tblPayment.setItems(getAllPayment());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Payment Didn't made", ButtonType.OK).show();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Pattern didn't Match", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    boolean isValidate = validation();
                    if (isValidate) {
                        boolean isUpdated = update(paymentDTO);
                        if (isUpdated) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Update Success", ButtonType.OK).show();
                            generateReport();
                            tblPayment.setItems(getAllPayment());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Update Failed", ButtonType.OK).show();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Pattern didn't match", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Update Success", ButtonType.OK).show();
                }
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Field", ButtonType.OK).show();
        }

    }


    public void loadStudentID() throws Exception {
        ArrayList<StudentDTO> studentDTOS = paymentBO.getAllStudent();
        ObservableList<String> all = FXCollections.observableArrayList();
        for (StudentDTO dto : studentDTOS) {
            all.add(dto.getSid());
        }
        cmbStudentId.setItems(all);
    }

    public void loadSubjectName() throws Exception {
        ArrayList<SubjectDTO> subjectDTOS = paymentBO.getAllSubject();
        ObservableList<String> all = FXCollections.observableArrayList();
        for (SubjectDTO dto : subjectDTOS) {
            all.add(dto.getSubjectname());
        }
        cmbSubjectName.setItems(all);
    }

    private boolean add(PaymentDTO paymentDTO) throws Exception {
        return paymentBO.add(paymentDTO);
    }

    private boolean update(PaymentDTO paymentDTO) throws Exception {
        return paymentBO.update(paymentDTO);
    }

    private boolean delete(String pid) throws Exception {
        return paymentBO.delete(pid);
    }

    private ArrayList<PaymentDTO> loadAllPayment() throws Exception {
        return paymentBO.getAll();

    }

    private String getLastPaymentId() throws Exception {
        return paymentBO.getLastPID();
    }


    public void searchOnAction(KeyEvent actionEvent) throws Exception {
        ArrayList<PaymentDTO> paymentDTOS = paymentBO.searchAllPayment(txtSearch.getText());
        if (paymentDTOS.size() == 0) {
            tblPayment.getItems().clear();
        } else {
            ObservableList<PaymentTM> tmObservableList = FXCollections.observableArrayList();
            for (PaymentDTO dto : paymentDTOS) {
                Button btn = new Button("Delete");
                btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
                tmObservableList.add(new PaymentTM(
                        dto.getPid(),
                        dto.getRegfee(),
                        dto.getDate(),
                        dto.getSubname(),
                        dto.getSid(),
                        dto.getAmount(),
                        btn
                ));

                btn.setOnAction((e) -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Confirmation Delete");
                    alert.setContentText("Are you sure to Delete ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        String pid = txtPid.getText();
                        try {
                            boolean isDeleted = delete(pid);
                            if (isDeleted) {
                                tblPayment.setItems(getAllPayment());
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Delete Failed", ButtonType.OK).show();
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }

                });
                tblPayment.setItems(tmObservableList);
            }
        }

    }

    public void ClearOnAction(ActionEvent actionEvent) throws Exception {
        clearAllFields();
        btnPayment.setText("Add Payment");
    }

    public void CashOnAction(ActionEvent actionEvent) {
        Double tot = Double.parseDouble(txtTotall.getText());
        Double cash = Double.parseDouble(txtCash.getText());
        Double balance = cash - tot;
        txtCBalance.setText(String.valueOf(balance));
    }

    public void cmbSearchOnAction(KeyEvent keyEvent) throws Exception {
        ArrayList<StudentDTO> studentDTOS = paymentBO.comboSearchStudent(String.valueOf(cmbStudentId.getValue()));
        ObservableList<String> all = FXCollections.observableArrayList();
        for (StudentDTO dto : studentDTOS) {
            all.add(dto.getSid());
        }
        cmbStudentId.setItems(all);
    }

    public void generateReport() {
        try {
            InputStream is = getClass().getResourceAsStream("../report/SuccessInstitute.jrxml");
            JasperReport jr = null;
            jr = JasperCompileManager.compileReport(is);
            HashMap hs = new HashMap();
            hs.put("paymentId", txtPid.getText());
            hs.put("studentID", cmbStudentId.getValue());
            hs.put("subjectName", cmbSubjectName.getValue());
            hs.put("amount", txtTotall.getText());

            JasperPrint jp = JasperFillManager.fillReport(jr, hs, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean validation() throws Exception {
        String cost = txtTotall.getText();
        String pid = txtPid.getText();
        if (Pattern.compile("^[0-9]{4}.[0]{2}$").matcher(cost).matches()) {
            return true;
        } else {
            txtTotall.setFocusColor(Paint.valueOf("red"));
            txtTotall.requestFocus();
        }
        return false;
    }

}
