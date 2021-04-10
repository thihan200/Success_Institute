package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.*;
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
import lk.ijse.successInstitute.bo.custom.TeacherBO;
import lk.ijse.successInstitute.dto.TeacherDTO;
import lk.ijse.successInstitute.view.tm.TeacherTM;
import rex.utils.S;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class TeacherController {

    public JFXTextField txtTeacherID;
    public JFXTextField txtTeacherName;
    public JFXTextField txtTeacherAddress;
    public JFXDatePicker txtdob;
    public JFXRadioButton radioMale;
    public ToggleGroup gender;
    public JFXRadioButton radioFemale;
    public JFXTextField txtTeacherContact;
    public TableView<TeacherTM> tblTeacher;
    public JFXTextField txtSearch;
    public JFXButton btnClear;
    public JFXButton btnRegister;
    public TableColumn colTid;
    public TableColumn colTname;
    public TableColumn colAddress;
    public TableColumn colDob;
    public TableColumn colContactno;
    public TableColumn colGender;
    public TableColumn colQualification;
    public TableColumn colOperate;
    public TableColumn colContactNo;
    public JFXTextField txtQualification;


    static TeacherBO teacherBO = (TeacherBO) BOFactory.getInstance().getBO(BOFactory.BOType.TEACHER);

    public void initialize() throws Exception {

        colTid.setCellValueFactory(new PropertyValueFactory<>("tid"));
        colTname.setCellValueFactory(new PropertyValueFactory<>("teachername"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactno.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblTeacher.setItems(getAllTeacher());

        tblTeacher.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRegister.setText("Update Teacher");

            txtTeacherID.setText(newValue.getTid());
            txtTeacherName.setText(newValue.getTeachername());
            txtTeacherAddress.setText(newValue.getAddress());
            txtTeacherContact.setText(newValue.getContact());
            txtdob.setValue(LocalDate.parse(newValue.getDob()));
            if ("Male".equals(newValue.getGender())) {
                radioMale.setSelected(true);
            } else {
                radioFemale.setSelected(true);
            }
            txtQualification.setText(newValue.getQualification());
        });

    }

    public void clearAllFields() throws Exception {
        txtTeacherID.setText("");
        txtTeacherName.setText("");
        txtTeacherAddress.setText("");
        txtTeacherContact.setText("");
        txtdob.setPromptText("Date Of Birth");
        radioFemale.setSelected(false);
        radioMale.setSelected(false);
        txtQualification.setText("");


    }

    private ObservableList<TeacherTM> getAllTeacher() throws Exception {
        ArrayList<TeacherDTO> teacherDTOS = loadAllTeacher();
        ObservableList<TeacherTM> tmObservableList = FXCollections.observableArrayList();
        for (TeacherDTO dto : teacherDTOS) {
            Button button = new Button("Delete");
            button.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(new TeacherTM(
                    dto.getTid(),
                    dto.getTeachername(),
                    dto.getAddress(),
                    dto.getContact(),
                    dto.getDob(),
                    dto.getGender(),
                    dto.getQualification(),
                    button
            ));

            button.setOnAction((e) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirmation Delete");
                alert.setContentText("Are you sure to Delete ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String tid = txtTeacherID.getText();
                    try {
                        boolean isDeleted = delete(tid);
                        if (isDeleted) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Delete Success", ButtonType.OK).show();
                            tblTeacher.setItems(getAllTeacher());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Delete Failed", ButtonType.OK).show();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
            tblTeacher.setItems(tmObservableList);
        }
        return tmObservableList;
    }

    public void registerOnAction(ActionEvent actionEvent) {
        String tid = txtTeacherID.getText();
        String tname = txtTeacherName.getText();
        String address = txtTeacherAddress.getText();
        String contact = txtTeacherContact.getText();
        String dob = String.valueOf(txtdob.getValue());
        boolean selected = radioMale.isSelected();
        String gender = "";
        if (selected) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        String qualification = txtQualification.getText();

        TeacherDTO teacherDTO = new TeacherDTO(tid, tname, address, contact, dob, gender, qualification);
        if (tid.length() > 0 && tname.length() > 0 && address.length() > 0 && contact.length() > 0 && gender.length() > 0 && qualification.length() > 0) {
            if (btnRegister.getText().equalsIgnoreCase("Register")) {
                try {
                    boolean isValidate = validate();
                    if (isValidate) {
                        boolean isAdded = add(teacherDTO);
                        if (isAdded) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Added Success", ButtonType.OK).show();
                            tblTeacher.setItems(getAllTeacher());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Added Failed", ButtonType.OK).show();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Pattern Didn't Match", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    boolean isValidate = validate();
                    if (isValidate) {
                        boolean isUpdated = Update(teacherDTO);
                        if (isUpdated) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Update Success", ButtonType.OK).show();
                            tblTeacher.setItems(getAllTeacher());
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
            new Alert(Alert.AlertType.WARNING, "Empty Field", ButtonType.OK).show();
        }
    }

    private boolean Update(TeacherDTO teacherDTO) throws Exception {
        return teacherBO.update(teacherDTO);
    }

    private boolean add(TeacherDTO teacherDTO) throws Exception {
        return teacherBO.add(teacherDTO);
    }

    private boolean delete(String tid) throws Exception {
        return teacherBO.delete(tid);
    }

    private ArrayList<TeacherDTO> loadAllTeacher() throws Exception {
        return teacherBO.getAll();
    }


    public void searchOnAction(KeyEvent actionEvent) throws Exception {
        ArrayList<TeacherDTO> teacherDTOS = teacherBO.searchAllTecher(txtSearch.getText());
        if (teacherDTOS.size() == 0) {
            tblTeacher.getItems().clear();
        } else {
            ObservableList<TeacherTM> tmObservableList = FXCollections.observableArrayList();
            for (TeacherDTO dto : teacherDTOS) {
                Button button = new Button("Delete");
                button.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
                tmObservableList.add(new TeacherTM(
                        dto.getTid(),
                        dto.getTeachername(),
                        dto.getAddress(),
                        dto.getContact(),
                        dto.getDob(),
                        dto.getGender(),
                        dto.getQualification(),
                        button
                ));

                button.setOnAction((e) -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Confirmation Delete");
                    alert.setContentText("Are you sure to Delete ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        String tid = txtTeacherID.getText();
                        try {
                            boolean isDeleted = delete(tid);
                            if (isDeleted) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Delete Success", ButtonType.OK).show();
                                tblTeacher.setItems(getAllTeacher());
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Delete Failed", ButtonType.OK).show();
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
                tblTeacher.setItems(tmObservableList);
            }
        }
    }

    public void clearOnAction(ActionEvent actionEvent) throws Exception {
        clearAllFields();
        btnRegister.setText("Register");
    }

    public boolean validate() throws Exception {
        String tid = txtTeacherID.getText();
        String tname = txtTeacherName.getText();
        String address = txtTeacherAddress.getText();
        String contact = txtTeacherContact.getText();

        if (Pattern.compile("^[A-Z0-9]{2,}$").matcher(tid).matches()) {
            if (Pattern.compile("^[A-z]{2,3}. [A-z ]{2,}$").matcher(tname).matches()) {
                if (Pattern.compile("^[A-z0-9 ]{2,}$").matcher(address).matches()) {
                    if (Pattern.compile("^[0-9]{3}-[0-9]{7}$").matcher(contact).matches()) {
                        return true;
                    } else {
                        txtTeacherContact.setFocusColor(Paint.valueOf("red"));
                        txtTeacherContact.requestFocus();
                    }

                } else {
                    txtTeacherAddress.setFocusColor(Paint.valueOf("red"));
                    txtTeacherAddress.requestFocus();
                }

            } else {
                txtTeacherName.setFocusColor(Paint.valueOf("red"));
                txtTeacherName.requestFocus();
            }

        } else {
            txtTeacherID.setFocusColor(Paint.valueOf("red"));
            txtTeacherID.requestFocus();
        }
        return false;

    }



}
