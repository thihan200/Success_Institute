package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.successInstitute.bo.BOFactory;
import lk.ijse.successInstitute.bo.custom.TimetableBO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.dto.TeacherDTO;
import lk.ijse.successInstitute.dto.TimetableDTO;
import lk.ijse.successInstitute.view.tm.TimetableTM;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

public class TimetableController {

    public JFXComboBox cmbBid;
    public JFXTimePicker txtTime;
    public JFXTextField txtDay;
    public TableView<TimetableTM> tblTimetable;
    public JFXComboBox cmbSuName;
    public JFXComboBox cmbTName;
    public TableColumn colSubName;
    public TableColumn colTeacherName;
    public TableColumn colBatchId;
    public TableColumn colDay;
    public TableColumn colTime;
    public TableColumn colOperate;
    public JFXButton Addbtn;

    TimetableBO timetableBO = (TimetableBO) BOFactory.getInstance().getBO(BOFactory.BOType.TIMETABLE);

    public void initialize() throws Exception{
        colSubName.setCellValueFactory(new PropertyValueFactory<>("suName"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        colBatchId.setCellValueFactory(new PropertyValueFactory<>("batchid"));
        colDay.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblTimetable.setItems(getAllItems());

        //bot
        tblTimetable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{

            cmbSuName.setValue(newValue.getSuName());
            cmbTName.setValue(newValue.getTeacherName());
            cmbBid.setValue(newValue.getBatchid());
            txtDay.setText(newValue.getDate());
            txtTime.setValue(LocalTime.parse(newValue.getTime()));
        });
        loadAllSubject();
        loadAllteacher();
        loadAllbid();
        cmbSuName.requestFocus();

    }

    private ObservableList<TimetableTM> getAllItems() throws Exception{
        ArrayList<TimetableDTO> timetableDTOS = timetableBO.getAll();
        ObservableList<TimetableTM> tmObservableList = FXCollections.observableArrayList();
        for (TimetableDTO dto : timetableDTOS){
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(new TimetableTM(
                    dto.getSuName(),
                    dto.gettName(),
                    dto.getBatchid(),
                    dto.getDate(),
                    dto.getTime(),
                    btn
            ));
            btn.setOnAction((e)->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirmation Delete");
                alert.setContentText("Are you sure to Delete ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String SuName = String.valueOf(cmbSuName.getValue());
                    String bid = String.valueOf(cmbBid.getValue());
                    String day = txtDay.getText();

                    try {
                        boolean isDeleted = delete(SuName, bid ,day);
                        if (isDeleted){
                            new Alert(Alert.AlertType.CONFIRMATION, "Delete Success", ButtonType.OK).show();
                            tblTimetable.setItems(getAllItems());
                        }else {
                            new Alert(Alert.AlertType.WARNING, "delete Failed", ButtonType.OK).show();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
            tblTimetable.setItems(tmObservableList);
        }

        return tmObservableList;
    }

    private boolean delete(String suName, String bid, String day)throws Exception {
        return timetableBO.deleteAll(suName, bid, day);
    }

    public void loadAllSubject()throws Exception{
        ArrayList<SubjectDTO> subjectDTOS = timetableBO.geyAllSubject();
        ObservableList<String> all = FXCollections.observableArrayList();
        for (SubjectDTO dto : subjectDTOS){
            all.add(dto.getSubjectname());
        }
        cmbSuName.setItems(all);
    }

    public void loadAllteacher()throws Exception{
        ArrayList<TeacherDTO> teacherDTOS = timetableBO.getAllTeacher();
        ObservableList<String> all = FXCollections.observableArrayList();
        for (TeacherDTO dto : teacherDTOS){
            all.add(dto.getTeachername());
        }
        cmbTName.setItems(all);
    }

    public void loadAllbid()throws Exception{
        ArrayList<BatchDTO> batchDTOS = timetableBO.getAllBath();
        ObservableList<String> all = FXCollections.observableArrayList();
        for (BatchDTO dto : batchDTOS){
            all.add(dto.getBid());
        }
        cmbBid.setItems(all);
    }

    public void AddOnAction(ActionEvent actionEvent) {
        String suName = String.valueOf(cmbSuName.getValue());
        String tName = String.valueOf(cmbTName.getValue());
        String bid = String.valueOf(cmbBid.getValue());
        String day = txtDay.getText();
        String time = String.valueOf(txtTime.getValue());

        TimetableDTO timetableDTO = new TimetableDTO(suName, tName, bid, day, time);
        if (suName.length()>0 && tName.length()>0 && bid.length()>0 && day.length()>0 && time.length()>0) {
            try {
                boolean isAdded = add(timetableDTO);
                if (isAdded){
                    new Alert(Alert.AlertType.CONFIRMATION, "Added Suucess", ButtonType.OK).show();
                    tblTimetable.setItems(getAllItems());
                }else {
                    new Alert(Alert.AlertType.WARNING, "Added Failed", ButtonType.OK).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Empty Fields", ButtonType.OK).show();
        }
    }

    private boolean add(TimetableDTO timetableDTO) throws Exception {
        return timetableBO.add(timetableDTO);
    }


    private boolean update(TimetableDTO timetableDTO) throws Exception {
        return timetableBO.update(timetableDTO);
    }

    public void UpdateOnActon(ActionEvent actionEvent) {
        String suName = String.valueOf(cmbSuName.getValue());
        String tName = String.valueOf(cmbTName.getValue());
        String bid = String.valueOf(cmbBid.getValue());
        String day = txtDay.getText();
        String time = String.valueOf(txtTime.getValue());

        TimetableDTO timetableDTO = new TimetableDTO(suName, tName, bid, day, time);
        if (suName.length()>0 && tName.length()>0 && bid.length()>0 && day.length()>0 && time.length()>0) {
            try {
                boolean isUpdated = update(timetableDTO);
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION, "Update Success", ButtonType.OK).show();
                    tblTimetable.setItems(getAllItems());
                }else {
                    new Alert(Alert.AlertType.WARNING, "Update Failed", ButtonType.OK).show();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING, "Update Failed", ButtonType.OK).show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Empty Fields", ButtonType.OK).show();
        }
    }
}
