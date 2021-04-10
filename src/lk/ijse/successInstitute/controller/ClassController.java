package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.successInstitute.bo.BOFactory;
import lk.ijse.successInstitute.bo.custom.ClassBO;
import lk.ijse.successInstitute.dto.ClassDTO;
import lk.ijse.successInstitute.view.tm.ClassTM;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class ClassController {
    public JFXTextField txtClassName;
    public JFXTextField txtClassId;
    public JFXTextField txtStudentLimit;
    public TableView<ClassTM> tblclassroom;
    public JFXTextField txtSearch;
    public TableColumn colCID;
    public TableColumn colClassName;
    public TableColumn colStudentLimit;
    public TableColumn colOperate;
    public JFXButton btnAddClass;
    public JFXButton btnClear;

    static ClassBO classBO = (ClassBO) BOFactory.getInstance().getBO(BOFactory.BOType.CLASS);

    public void initialize()throws Exception{
        colCID.setCellValueFactory(new PropertyValueFactory<>("classid"));
        colClassName.setCellValueFactory(new PropertyValueFactory<>("classname"));
        colStudentLimit.setCellValueFactory(new PropertyValueFactory<>("studentlimit"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));
        tblclassroom.setItems(getAllClass());

        //bot
        tblclassroom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> {
            btnAddClass.setText("Update Class");

            txtClassId.setText(newValue.getClassid());
            txtClassName.setText(newValue.getClassname());
            txtStudentLimit.setText(newValue.getStudentlimit());

                });

        generateLastCID();
    }

    public void clearAllFields() throws Exception {
        txtClassId.setText(generateLastCID());
        txtClassName.setText("");
        txtStudentLimit.setText("");

    }

    public String generateLastCID()throws Exception{
        String lastCID = getLastCID();
        if (lastCID != null) {
            lastCID = lastCID.split("[A-Z]")[2];
            lastCID = "CL0" + (Integer.parseInt(lastCID) + 1 );
            txtClassId.setText(lastCID);
        } else {
            txtClassId.setText("CL01");
        }
        return lastCID;

    }

    private ObservableList<ClassTM> getAllClass()throws Exception{
        ArrayList<ClassDTO> classDTOS = loadAllClass();
        ObservableList<ClassTM> tmObservableList = FXCollections.observableArrayList();
        for (ClassDTO dto : classDTOS){
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(new ClassTM(
                    dto.getClassid(),
                    dto.getClassname(),
                    dto.getStudentlimit(),
                    btn
            ));

            btn.setOnAction((e)->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirmation Delete");
                alert.setContentText("Are you sure to Delete ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String cid = txtClassId.getText();
                    try {
                        boolean isDeleted = delete(cid);
                        if (isDeleted) {
                            tblclassroom.setItems(getAllClass());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Removed Failed", ButtonType.OK).show();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
            tblclassroom.setItems(tmObservableList);
        }
        return tmObservableList;
    }



    public void ClassOnAction(ActionEvent actionEvent) {
        String classId = txtClassId.getText();
        String cName = txtClassName.getText();
        String stuLimit = txtStudentLimit.getText();

        ClassDTO classDTO = new ClassDTO(classId, cName, stuLimit);
        if (classId.length()>0 && cName.length()>0 && stuLimit.length()>0){
            if (btnAddClass.getText().equalsIgnoreCase("Add New Class")){
                try {
                    boolean isValidate = validate();
                    if (isValidate) {
                        boolean isAdded = add(classDTO);
                        if (isAdded) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Added Success", ButtonType.OK).show();
                            tblclassroom.setItems(getAllClass());
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
                        boolean isUpdated = Update(classDTO);
                        if (isUpdated) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Update Sccuess", ButtonType.OK).show();
                            tblclassroom.setItems(getAllClass());
                        } else {
                            new Alert(Alert.AlertType.CONFIRMATION, "Update Failed", ButtonType.OK).show();
                        }
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Pattern Didn't Match", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }else {
            new Alert(Alert.AlertType.WARNING, "Empty Fields", ButtonType.OK).show();
        }

    }

    private boolean Update(ClassDTO classDTO) throws Exception {
        return classBO.update(classDTO);
    }

    private String getLastCID()throws Exception{
        return classBO.getLastCID();
    }

    public void searchOnAction(KeyEvent actionEvent) throws Exception {
        ArrayList<ClassDTO> classDTOS = classBO.searchAllClass(txtSearch.getText());
        if (classDTOS.size() == 0) {
            tblclassroom.getItems().clear();
        } else {
            ObservableList<ClassTM> tmObservableList = FXCollections.observableArrayList();
            for (ClassDTO dto : classDTOS) {
                Button btn = new Button("Delete");
                btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
                tmObservableList.add(new ClassTM(
                        dto.getClassid(),
                        dto.getClassname(),
                        dto.getStudentlimit(),
                        btn
                ));

                btn.setOnAction((e) -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Confirmation Delete");
                    alert.setContentText("Are you sure to Delete ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        String cid = txtClassId.getText();
                        try {
                            boolean isDeleted = delete(cid);
                            if (isDeleted) {
                                tblclassroom.setItems(getAllClass());
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Removed Failed", ButtonType.OK).show();
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
                tblclassroom.setItems(tmObservableList);
            }
        }
    }

    private ArrayList<ClassDTO> loadAllClass()throws Exception{
        return classBO.getAll();
    }

    private boolean add(ClassDTO classDTO)throws Exception{
        return classBO.add(classDTO);
    }

    private boolean delete(String cid) throws Exception {
        return classBO.delete(cid);
    }

    public boolean validate()throws Exception{
        String className = txtClassName.getText();
        String limit = txtStudentLimit.getText();

        if (Pattern.compile("^[A-Z0-9]{2,}$").matcher(className).matches()) {
            if (Pattern.compile("^[0-9]{2,4}-[0-9]{2,4}$").matcher(limit).matches()){
                return true;
            }else {
                txtStudentLimit.setFocusColor(Paint.valueOf("red"));
                txtStudentLimit.requestFocus();
            }

        }else {
            txtClassName.setFocusColor(Paint.valueOf("red"));
            txtClassName.requestFocus();
        }
        return false;
    }


    public void CllearOnAction(ActionEvent actionEvent) throws Exception {
        clearAllFields();
        btnAddClass.setText("Add New Class");
    }
}
