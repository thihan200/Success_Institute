package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.successInstitute.bo.BOFactory;
import lk.ijse.successInstitute.bo.custom.EmployeeBO;
import lk.ijse.successInstitute.dto.EmployeeDTO;
import lk.ijse.successInstitute.view.tm.EmployeeTM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class EmployeeController {
    public JFXTextField txtEmployeeID;
    public JFXTextField txtEmployeeName;
    public JFXTextField txtEmployeeAddress;
    public JFXDatePicker txtdob;
    public JFXRadioButton radioMale;
    public ToggleGroup gender;
    public JFXRadioButton radioFemale;
    public JFXTextField txtEmployeeContact;
    public JFXTextField txtPosition;
    public TableView<EmployeeTM> tblEmployee;
    public TableColumn colEid;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;
    public TableColumn colPosition;
    public TableColumn colOperate;
    public JFXTextField txtSearch;
    public JFXButton btnClear;
    public JFXButton btnRegister;

    static EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);

    public void initialize()throws Exception{
        colEid.setCellValueFactory(new PropertyValueFactory<>("eid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employeename"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblEmployee.setItems(getAllEmployee());

        //bot

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            btnRegister.setText("Update Employee");

            txtEmployeeID.setText(newValue.getEid());
            txtEmployeeName.setText(newValue.getEmployeename());
            txtEmployeeAddress.setText(newValue.getAddress());
            txtEmployeeContact.setText(newValue.getContact());
            txtdob.setValue(LocalDate.parse(newValue.getDob()));
            if ("Male".equals(newValue.getGender())) {
                radioMale.setSelected(true);
            } else {
                radioFemale.setSelected(true);
            }
            txtPosition.setText(newValue.getPosition());

        });
        generateLastId();

    }

    public String generateLastId()throws Exception{
        String empId = getLastEmployeId();
        if (empId != null) {
            empId = empId.split("[A-Z]")[1];
            if (Integer.parseInt(empId)<=9) {
                empId = "E00" + (Integer.parseInt(empId) + 1);
                txtEmployeeID.setText(empId);
            }else {
                empId = "E0" + (Integer.parseInt(empId) + 1);
                txtEmployeeID.setText(empId);
            }
        } else {
            txtEmployeeID.setText("E001");
        }
        return empId;
    }

    public void clearAllFields() throws Exception {
        txtEmployeeID.setText(generateLastId());
        txtEmployeeName.setText("");
        txtEmployeeAddress.setText("");
        txtEmployeeContact.setText("");
        txtdob.setPromptText(" ");
        radioMale.setSelected(false);
        radioFemale.setSelected(false);
        txtPosition.setText("");

    }

    private ObservableList<EmployeeTM> getAllEmployee()throws Exception{
        ArrayList<EmployeeDTO> employeeDTOS = loadAllEmployee();
        ObservableList<EmployeeTM> tmObservableList = FXCollections.observableArrayList();
        for (EmployeeDTO dto : employeeDTOS){
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(
                    new EmployeeTM(
                            dto.getEid(),
                            dto.getEmployeename(),
                            dto.getAddress(),
                            dto.getContact(),
                            dto.getDob(),
                            dto.getGender(),
                            dto.getPosition(),
                            btn

                    )
            );
            btn.setOnAction((e)-> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirmation Delete");
                alert.setContentText("Are you sure to Delete ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String eid = txtEmployeeID.getText();
                    try {
                        Boolean isDeleted = delete(eid);
                        if (isDeleted){
                            tblEmployee.setItems(getAllEmployee());
                        }else {
                            new Alert(Alert.AlertType.CONFIRMATION, "Delete Failed", ButtonType.OK).show();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                }
            });
            tblEmployee.setItems(tmObservableList);
        }
        return tmObservableList;
    }

    private Boolean delete(String eid) throws Exception {
        return employeeBO.delete(eid);
    }

    public void registerOnAction(ActionEvent actionEvent) {
        String eid = txtEmployeeID.getText();
        String ename = txtEmployeeName.getText();
        String address = txtEmployeeAddress.getText();
        String contact = txtEmployeeContact.getText();
        String dob = String.valueOf(txtdob.getValue());
        boolean selected = radioMale.isSelected();
        String gender = "";
        if (selected){
            gender = "Male";
        }else{
            gender = "Female";
        }
        String positon = txtPosition.getText();

        EmployeeDTO employeeDTO = new EmployeeDTO(eid, ename, address, contact, dob, gender, positon);
        if (eid.length()>0 && ename.length()>0 && address.length()>0 && contact.length()>0 && gender.length()>0 && positon.length()>0){
            if (btnRegister.getText().equalsIgnoreCase("Register")){
                try {
                    boolean isValidate = validation();
                    if (isValidate) {
                        boolean isAdded = add(employeeDTO);

                        if (isAdded) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Added Success", ButtonType.OK).show();
                            tblEmployee.setItems(getAllEmployee());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Added Failed", ButtonType.OK).show();
                        }
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Pattern Didn't Match", ButtonType.OK).show();
                    }
                    } catch(Exception e){
                        e.printStackTrace();
                    }


            }else {
                try {
                    boolean isValidate = validation();
                    if (isValidate) {
                        boolean isUpdated = update(employeeDTO);
                        if (isUpdated) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Update Success", ButtonType.OK).show();
                            tblEmployee.setItems(getAllEmployee());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Update Failed", ButtonType.OK).show();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Pattern Didn't Match", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Fieldset", ButtonType.OK).show();
        }
    }

    private boolean update(EmployeeDTO employeeDTO) throws Exception {
        return employeeBO.update(employeeDTO);
    }

    private boolean add(EmployeeDTO employeeDTO) throws Exception {
        return employeeBO.add(employeeDTO);
    }

    private String getLastEmployeId()throws Exception{
        return employeeBO.getLastEmployeeId();
    }

    private ArrayList<EmployeeDTO> loadAllEmployee()throws Exception{
        return employeeBO.getAll();
    }


    public void clearOnAction(ActionEvent actionEvent) throws Exception {
        clearAllFields();
        btnRegister.setText("Register");

    }

    public void searchOnAction(KeyEvent actionEvent) throws Exception {
        ArrayList<EmployeeDTO> employeeDTOS = employeeBO.searchAllEmployee(txtSearch.getText());
        if (employeeDTOS.size() == 0){
            tblEmployee.getItems().clear();
        }else {
            ObservableList<EmployeeTM> tmObservableList = FXCollections.observableArrayList();
            for (EmployeeDTO dto : employeeDTOS){
                Button btn = new Button("Delete");
                btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
                tmObservableList.add(
                        new EmployeeTM(
                                dto.getEid(),
                                dto.getEmployeename(),
                                dto.getAddress(),
                                dto.getContact(),
                                dto.getDob(),
                                dto.getGender(),
                                dto.getPosition(),
                                btn

                        )
                );
                btn.setOnAction((e)-> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Confirmation Delete");
                    alert.setContentText("Are you sure to Delete ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        String eid = txtEmployeeID.getText();
                        try {
                            Boolean isDeleted = delete(eid);
                            if (isDeleted){
                                tblEmployee.setItems(getAllEmployee());
                            }else {
                                new Alert(Alert.AlertType.CONFIRMATION, "Delete Failed", ButtonType.OK).show();
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                    }
                });
                tblEmployee.setItems(tmObservableList);
            }
        }
    }


    public boolean validation() throws Exception{
        String eid = txtEmployeeID.getText();
        String name = txtEmployeeName.getText();
        String address = txtEmployeeAddress.getText();
        String contact = txtEmployeeContact.getText();
        String position = txtPosition.getText();
       /* Pattern compile = Pattern.compile("^(^C)[\\d]{0,3}$");
        Matcher matcher = compile.matcher(id);
        boolean matches = matcher.matches();*/

        if (Pattern.compile("^(^E)[\\d]{0,3}$").matcher(eid).matches()){
            if (Pattern.compile("^[A-z]{2,} [A-z]{2,}$").matcher(name).matches()){
                if (Pattern.compile("^[A-z0-9 ]{2,}$").matcher(address).matches()){

                    if (Pattern.compile("^[0-9]{3}-[0-9]{7}$").matcher(contact).matches()){
                       if (Pattern.compile("^[A-z]{2,} [A-z]{2,}|[A-z]{2,}$").matcher(position).matches()) {
                           // new Alert(Alert.AlertType.INFORMATION,"Pattern OK").show();
                           return true;
                       }else {
                           txtPosition.setFocusColor(Paint.valueOf("red"));
                           txtPosition.requestFocus();
                       }
                    }else {
                        txtEmployeeContact.setFocusColor(Paint.valueOf("red"));
                        txtEmployeeContact.requestFocus();
                    }
                }else {
                    txtEmployeeAddress.setFocusColor(Paint.valueOf("red"));
                    txtEmployeeAddress.requestFocus();
                }
            }else{
                txtEmployeeName.setFocusColor(Paint.valueOf("red"));
                txtEmployeeName.requestFocus();
            }

        }else {
            txtEmployeeID.setFocusColor(Paint.valueOf("red"));
            txtEmployeeID.requestFocus();
        }

        return false;
    }
}
