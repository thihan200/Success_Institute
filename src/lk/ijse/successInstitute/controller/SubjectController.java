package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.successInstitute.bo.BOFactory;
import lk.ijse.successInstitute.bo.custom.SubjectBO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.dto.TeacherDTO;
import lk.ijse.successInstitute.view.tm.SubjectTM;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class SubjectController {

    public JFXTextField txtSubName;
    public JFXTextField txtSubId;
    public JFXComboBox cmbTeacherId;
    public JFXTextField txtTeacherName;
    public TableView<SubjectTM> tblSubject;
    public JFXTextField txtSearch;
    public JFXButton btnAdd;
    public JFXButton btnClear;
    public TableColumn colSid;
    public TableColumn colSname;
    public TableColumn colTid;
    public TableColumn colTname;
    public TableColumn colOperate;


    static SubjectBO subjectBO = (SubjectBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUBJECT);


    public void initialize()throws Exception{
        colSid.setCellValueFactory(new PropertyValueFactory<>("suid"));
        colSname.setCellValueFactory(new PropertyValueFactory<>("subjectname"));
        colTid.setCellValueFactory(new PropertyValueFactory<>("teachername"));
        colTname.setCellValueFactory(new PropertyValueFactory<>("tid"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblSubject.setItems(getAllSubject());

        tblSubject.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnAdd.setText("Update Subject");

            txtSubId.setText(newValue.getSuid());
            txtSubName.setText(newValue.getSubjectname());
            cmbTeacherId.setValue(newValue.getTid());
            txtTeacherName.setText(newValue.getTeachername());
        });

        getAllTeacher();
    }

    public void clearAllFields() throws Exception {

        txtSubId.setText("");
        txtSubName.setText("");
        cmbTeacherId.setPromptText(" ");
        txtTeacherName.setText("");

    }

    private ObservableList<SubjectTM> getAllSubject() throws Exception{
        ArrayList<SubjectDTO> subjectDTOS = loadAllSubjects();
        ObservableList<SubjectTM> tmObservableList = FXCollections.observableArrayList();
        for (SubjectDTO dto : subjectDTOS) {
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(new SubjectTM(
                    dto.getSuid(),
                    dto.getSubjectname(),
                    dto.getTeachername(),
                    dto.getTid(),
                    btn
            ));
            btn.setOnAction((e)->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirmation Delete");
                alert.setContentText("Are you sure to Delete ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String suid = txtSubId.getText();
                    try {
                        boolean isDeleted = delete(suid);
                        if (isDeleted){
                            //new Alert(Alert.AlertType.CONFIRMATION, "Delete Success", ButtonType.OK).show();
                            tblSubject.setItems(getAllSubject());
                        }else {
                            new Alert(Alert.AlertType.WARNING, "Delete Failed", ButtonType.OK).show();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }


            });
                tblSubject.setItems(tmObservableList);
        }
        return tmObservableList;
    }

    public void AddSubjectOnAction(ActionEvent actionEvent) {
        String suId = txtSubId.getText();
        String suName = txtSubName.getText();
        String tid = String.valueOf(cmbTeacherId.getValue());
        String tName = txtTeacherName.getText();

        SubjectDTO subjectDTO = new SubjectDTO(suId, suName, tName, tid);
        if (suId.length()>0 && suName.length()>0 && tid.length()>0 && tName.length()>0){
            if (btnAdd.getText().equalsIgnoreCase("Add New Subject")){
                try {
                    boolean isValidate = validate();
                    if (isValidate) {
                        boolean isAdded = add(subjectDTO);
                        if (isAdded) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Added Success", ButtonType.OK).show();
                            tblSubject.setItems(getAllSubject());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Added Failed", ButtonType.OK).show();
                        }
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Pattern Didn't Match", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else {
                try {
                    boolean isValidate = validate();
                    if (isValidate) {
                        boolean isUpdated = update(subjectDTO);
                        if (isUpdated) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Update Success", ButtonType.OK).show();
                            tblSubject.setItems(getAllSubject());
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
        }else {
            new Alert(Alert.AlertType.WARNING, "Empty Field", ButtonType.OK).show();
        }


    }

    private boolean update(SubjectDTO subjectDTO) throws Exception {
        return subjectBO.update(subjectDTO);
    }

    private boolean add(SubjectDTO subjectDTO) throws Exception {
        return subjectBO.add(subjectDTO);
    }


    private void getAllTeacher()throws Exception{
        ArrayList<TeacherDTO> teacherDTOS = loadAllTeacher();
        ObservableList<String> allTeacher = FXCollections.observableArrayList();
        for (TeacherDTO dto : teacherDTOS){
            allTeacher.add(dto.getTid());
        }
        cmbTeacherId.setItems(allTeacher);
    }

    private boolean delete(String suid) throws Exception {
        return subjectBO.delete(suid);
    }

    private ArrayList<SubjectDTO> loadAllSubjects() throws Exception {
        return subjectBO.getAll();
    }

    private ArrayList<TeacherDTO> loadAllTeacher() throws Exception{
        return subjectBO.getAllTeacher();

    }


    public void searchOnAction(KeyEvent actionEvent) throws Exception {
        ArrayList<SubjectDTO> allSubject = subjectBO.searchAllSubject(txtSearch.getText());
        if (allSubject.size()==0){
            tblSubject.getItems().clear();
        } else {
            ObservableList<SubjectTM> tmObservableList = FXCollections.observableArrayList();
            for (SubjectDTO dto : allSubject) {
                Button btn = new Button("Delete");
                btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
                tmObservableList.add(new SubjectTM(
                        dto.getSuid(),
                        dto.getSubjectname(),
                        dto.getTeachername(),
                        dto.getTid(),
                        btn
                ));
                btn.setOnAction((e)->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Confirmation Delete");
                    alert.setContentText("Are you sure to Delete ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        String suid = txtSubId.getText();
                        try {
                            boolean isDeleted = delete(suid);
                            if (isDeleted){
                                //new Alert(Alert.AlertType.CONFIRMATION, "Delete Success", ButtonType.OK).show();
                                tblSubject.setItems(getAllSubject());
                            }else {
                                new Alert(Alert.AlertType.WARNING, "Delete Failed", ButtonType.OK).show();
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }


                });
                tblSubject.setItems(tmObservableList);
            }
        }
    }

    public void ClearOnAction(ActionEvent actionEvent) throws Exception {
        clearAllFields();
        btnAdd.setText("Add New Subject");
    }

    public void cmbTeachOnAction(ActionEvent actionEvent) {
        String teacherID = (String) cmbTeacherId.getValue();
        try {
            TeacherDTO teacherDTO = searchTeacher(teacherID);
            if (teacherDTO != null){
                txtTeacherName.setText(teacherDTO.getTeachername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TeacherDTO searchTeacher(String teacherID) throws Exception {
        return subjectBO.searchTeacher(teacherID);
    }

    public boolean validate()throws Exception{
        String subId = txtSubId.getText();
        String subName = txtSubName.getText();

        if (Pattern.compile("^[A-Z0-9]{2,}$").matcher(subId).matches()) {
            if (Pattern.compile("^[A-z ]{2,}$").matcher(subName).matches()){
                return true;
            }else {
                txtSubName.setFocusColor(Paint.valueOf("red"));
                txtSubName.requestFocus();
            }

        }else {
            txtSubId.setFocusColor(Paint.valueOf("red"));
            txtSubId.requestFocus();
        }


        return false;
    }
}
