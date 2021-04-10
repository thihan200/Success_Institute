package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.successInstitute.bo.BOFactory;
import lk.ijse.successInstitute.bo.custom.StudentAndRegisterBO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.dto.RegistrationDTO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.view.tm.StudentTM;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

public class StudentController {
    public JFXTextField txtStudentID;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXDatePicker txtdob;
    public JFXRadioButton radioMale;
    public ToggleGroup gender;
    public JFXRadioButton radioFemale;
    public JFXTextField txtStudentContact;
    public JFXComboBox<String> cmbBatchid;
    public TableView<StudentTM> tblStudent;
    public JFXButton btnRegister;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContactno;
    public TableColumn colDob;
    public TableColumn colGender;
    public TableColumn colOperate;
    public JFXTextField txtSchool;
    public TableColumn colBatch;
    public JFXTextField txtSubjects;
    public TableColumn colSubject;
    public TableColumn colSchool;
    public JFXButton btnClear;
    public JFXTextField txtSearch;
    public Label lblRegID;
    public JFXButton btnRegistration;
    public Label lblDate;

    static StudentAndRegisterBO studentBO = (StudentAndRegisterBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);


    public void initialize() throws Exception {
        colId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentname"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactno.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("bid"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colSchool.setCellValueFactory(new PropertyValueFactory<>("school"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblStudent.setItems(getAllStudent());

        //bot

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRegister.setText("Update Student");

            txtStudentID.setText(newValue.getSid());
            txtStudentName.setText(newValue.getStudentname());
            txtStudentAddress.setText(newValue.getAddress());
            txtStudentContact.setText(newValue.getContact());
            txtSchool.setText(newValue.getSchool());
            txtSubjects.setText(newValue.getSubject());
            txtdob.setValue(LocalDate.parse(newValue.getDob()));
            cmbBatchid.setValue(newValue.getBid());
            if ("Male".equals(newValue.getGender())) {
                radioMale.setSelected(true);
            } else {
                radioFemale.setSelected(true);
            }

        });
        loadBatchId();
        getTimeDate();
        generatePaymentId();
    }
    private void getTimeDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd ");
        lblDate.setText(sdf.format(d));
    }

    public String generatePaymentId()throws Exception{
        String lastID = studentBO.getLastPaymentId();
        if (lastID != null) {
            lastID = lastID.split("[A-Z]")[1];
            if (Integer.parseInt(lastID)<=9) {
                lastID = "R00" + (Integer.parseInt(lastID) + 1);
                lblRegID.setText(lastID);
            }else if (Integer.parseInt(lastID)<=99){
                lastID = "R0" + (Integer.parseInt(lastID) + 1);
                lblRegID.setText(lastID);
            }
        } else {
            lblRegID.setText("R001");
        }
        return lastID;
    }

    public void clearAllFields() throws Exception {
        txtStudentID.setText("");
        txtStudentName.setText("");
        txtStudentAddress.setText("");
        txtStudentContact.setText("");
        txtSubjects.setText("");
        txtSchool.setText("");
        cmbBatchid.setPromptText("Batch ID");
        txtdob.setPromptText("Date Of Birth");
        cmbBatchid.setPromptText("");
        radioFemale.setSelected(false);
        radioMale.setSelected(false);
        lblRegID.setText(generatePaymentId());


    }

    private ObservableList<StudentTM> getAllStudent() throws Exception {
        ArrayList<StudentDTO> studentDTOS = loadAllStudent();
        ObservableList<StudentTM> tmObservableList = FXCollections.observableArrayList();
        for (StudentDTO dto : studentDTOS) {
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(
                    new StudentTM(
                            dto.getSid(),
                            dto.getStudentname(),
                            dto.getAddress(),
                            dto.getContact(),
                            dto.getDob(),
                            dto.getGender(),
                            dto.getBid(),
                            dto.getSchool(),
                            dto.getSubject(),
                            btn

                    )
            );
            btn.setOnAction((e) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirmation Delete");
                alert.setContentText("Are you sure to Delete ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String sid = txtStudentID.getText();
                    try {
                        boolean isDeleted = delete(sid);
                        if (isDeleted) {
                           // new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted", ButtonType.OK).show();
                            tblStudent.setItems(getAllStudent());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Removed Failed", ButtonType.OK).show();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                    }

            );
            tblStudent.setItems(tmObservableList);
        }
        return tmObservableList;
    }


    public void registerOnAction(ActionEvent actionEvent){
        //Student details
        String sid = txtStudentID.getText();
        String sname = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String contact = txtStudentContact.getText();
        String dob = String.valueOf(txtdob.getValue());
        String school = txtSchool.getText();
        String subject = txtSubjects.getText();
        String bid = String.valueOf(cmbBatchid.getValue());
        boolean selected = radioMale.isSelected();
        String gender = "";
        if (selected) {
            gender = "Male";
        } else {
            gender = "Female";
        }

        ArrayList<RegistrationDTO> allRegistration = new ArrayList<>();
        String regId = lblRegID.getText();
        String date = lblDate.getText();
        String stuId = txtStudentID.getText();
        RegistrationDTO registrationDTO = new RegistrationDTO(regId, date, stuId);
        allRegistration.add(registrationDTO);



        StudentDTO studentDTO = new StudentDTO(sid, sname, address, contact, dob, gender, bid, school, subject, allRegistration);
        if (sid.length() > 0 && sname.length() > 0 && address.length() > 0 && contact.length() > 0 && gender.length() > 0 && bid.length() > 0 && school.length() > 0 && subject.length() > 0) {
            if (btnRegister.getText().equalsIgnoreCase("Register")) {
                try {
                    boolean isValidate = validate();
                    if (isValidate) {
                        boolean isAdded = add(studentDTO);
                        if (isAdded) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Added Success", ButtonType.OK).show();
                            tblStudent.setItems(getAllStudent());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Added Failed", ButtonType.OK).show();
                        }
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Pattern Didn't Match", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    boolean isValidate = validate();
                    if (isValidate) {
                        boolean isUpdated = update(studentDTO);
                        if (isUpdated) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Updated", ButtonType.OK).show();
                            tblStudent.setItems(getAllStudent());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Update Failed", ButtonType.OK).show();
                        }
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Pattern Didn't Match", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Fields", ButtonType.OK).show();
        }
    }


    public void loadBatchId() throws Exception {
        
        ArrayList<BatchDTO> batchDTOS = studentBO.getBatchId();
        ObservableList<String> all = FXCollections.observableArrayList();
        for (BatchDTO dto : batchDTOS) {
            all.add(dto.getBid());
        }
        cmbBatchid.setItems(all);

    }

    public void BatchOnAction(ActionEvent actionEvent) throws Exception {


    }



    public String generateLastId() throws Exception {
        String lastId = getLastStudentId();
        if (lastId != null) {
            lastId = lastId.split("[A-Z]")[1];
            lastId = "S00" + (Integer.parseInt(lastId) + 1);
            txtStudentID.setText(lastId);
        } else {
            txtStudentID.setText("S001");
        }
        return lastId;
    }

    private boolean add(StudentDTO studentDTO) throws Exception {
        return studentBO.registerStudent(studentDTO);
    }

    private boolean update(StudentDTO studentDTO) throws Exception {
        return studentBO.update(studentDTO);
    }

    private String getLastStudentId() throws Exception {
        return studentBO.getLastStudentId();
    }

    private ArrayList<BatchDTO> getBatchId() throws Exception {
        return studentBO.getBatchId();
    }

    private ArrayList<StudentDTO> loadAllStudent() throws Exception {
        return studentBO.getAll();
    }

    public void ClearOnAction(ActionEvent actionEvent) throws Exception {
        clearAllFields();
        btnRegister.setText("Register");
    }

    private boolean delete(String sid) throws Exception {
        return studentBO.delete(sid);
    }


    public void searchOnAction(KeyEvent keyEvent) throws Exception {
        ArrayList<StudentDTO> studentDTOS = studentBO.searchAllStudent(txtSearch.getText());
        if (studentDTOS.size() == 0) {
            tblStudent.getItems().clear();
        } else {
            ObservableList<StudentTM> tmObservableList = FXCollections.observableArrayList();
            for (StudentDTO dto : studentDTOS) {
                Button btn = new Button("Delete");
                btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
                tmObservableList.add(
                        new StudentTM(
                                dto.getSid(),
                                dto.getStudentname(),
                                dto.getAddress(),
                                dto.getContact(),
                                dto.getDob(),
                                dto.getGender(),
                                dto.getBid(),
                                dto.getSchool(),
                                dto.getSubject(),
                                btn

                        )
                );
                btn.setOnAction((e) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText("Confirmation Delete");
                            alert.setContentText("Are you sure to Delete ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                String sid = txtStudentID.getText();
                                try {
                                    boolean isDeleted = delete(sid);
                                    if (isDeleted) {
                                        // new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted", ButtonType.OK).show();
                                        tblStudent.setItems(getAllStudent());
                                    } else {
                                        new Alert(Alert.AlertType.WARNING, "Removed Failed", ButtonType.OK).show();
                                    }
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        }

                );
                tblStudent.setItems(tmObservableList);
            }
        }
    }

    public void registrationOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Registration.fxml"));
        Parent root= (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public boolean validate()throws Exception{
        String studentId = txtStudentID.getText();
        String name = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String school = txtSchool.getText();
        String contact = txtStudentContact.getText();

        if (Pattern.compile("^[A-Z0-9]{8}$").matcher(studentId).matches()){
            if (Pattern.compile("^[A-z ]{2,}$").matcher(name).matches()){
                if (Pattern.compile("^[A-z0-9 ]{2,}$").matcher(address).matches()){
                    if (Pattern.compile("^[A-z ]{2,}$").matcher(school).matches()){
                        if (Pattern.compile("^[0-9]{3}-[0-9]{7}$").matcher(contact).matches()){
                            return true;
                        }else {
                            txtStudentContact.setFocusColor(Paint.valueOf("red"));
                            txtStudentContact.requestFocus();
                        }
                    }else {
                        txtSchool.setFocusColor(Paint.valueOf("red"));
                        txtSchool.requestFocus();
                    }
                }else {
                    txtStudentAddress.setFocusColor(Paint.valueOf("red"));
                    txtStudentAddress.requestFocus();
                }
            }else {
                txtStudentName.setFocusColor(Paint.valueOf("red"));
                txtStudentName.requestFocus();
            }
        }else {
            txtStudentID.setFocusColor(Paint.valueOf("red"));
            txtStudentID.requestFocus();
        }


        return false;

    }
}
