package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.successInstitute.bo.BOFactory;
import lk.ijse.successInstitute.bo.custom.AttendanceBO;
import lk.ijse.successInstitute.dto.AttendanceDTO;
import lk.ijse.successInstitute.dto.StudentDTO;
import lk.ijse.successInstitute.dto.SubjectDTO;
import lk.ijse.successInstitute.view.tm.AttendanceTM;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class MarkAttendanceController {


    public JFXButton btnClear;
    public JFXTextField txtSearch;
    public JFXComboBox cmbSubjectName;
    public TableColumn colSid;
    public TableColumn colDate;
    public TableColumn colSubname;
    public TableColumn colArrivaltime;
    public TableColumn colOperate;
    public JFXComboBox cmbStudentId;
    public JFXButton btnAttend;
    public TableView<AttendanceTM> tblAttendance;
    public JFXTextField txtDate;
    public JFXTextField txtTime;

    AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getInstance().getBO(BOFactory.BOType.ATTENDANCE);

    public void initialize() throws Exception{
        colSid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colArrivaltime.setCellValueFactory(new PropertyValueFactory<>("arrivaltime"));
        colSubname.setCellValueFactory(new PropertyValueFactory<>("subname"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblAttendance.setItems(getAllAttendance());

        //bot
        tblAttendance.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            btnAttend.setText("Update Attend");

            cmbStudentId.setValue(newValue.getSid());
            txtDate.setText(newValue.getDate());
            txtTime.setText(newValue.getArrivaltime());
            cmbSubjectName.setValue(newValue.getSubname());

        });

    getTimeDate();
    loadAllStudentId();
    loadAllSubjectName();

    }

    private String getTimeDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = ((SimpleDateFormat) dateFormat).format(date);
        txtTime.setText(formattedDate);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd ");
        txtDate.setText(sdf.format(d));

        return formattedDate;
    }

    public void clearAllFields() throws Exception {
        txtDate.setText(getTimeDate());
        cmbSubjectName.setPromptText(" ");
        cmbStudentId.setPromptText(" ");
        txtTime.setText(getTimeDate());

    }


    private ObservableList<AttendanceTM> getAllAttendance() throws Exception {
        ArrayList<AttendanceDTO> attendanceDTOS = attendanceBO.getAll();
        ObservableList<AttendanceTM> observableList = FXCollections.observableArrayList();
        for (AttendanceDTO dto :attendanceDTOS){
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            observableList.add(
                    new AttendanceTM(
                            dto.getSid(),
                            dto.getDate(),
                            dto.getArrivaltime(),
                            dto.getSubname(),
                            btn
                    ));

            btn.setOnAction((e)->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirmation Delete");
                alert.setContentText("Are you sure to Delete ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String sid = String.valueOf(cmbStudentId.getValue());
                    String date = txtDate.getText();
                    try {
                        boolean isDeleted = delete(sid, date);
                        if (isDeleted){
                            //new Alert(Alert.AlertType.CONFIRMATION, "Delete Success", ButtonType.OK).show();
                            tblAttendance.setItems(getAllAttendance());
                        }else {
                            new Alert(Alert.AlertType.WARNING, "Delete Failed", ButtonType.OK).show();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                }else {
                    new Alert(Alert.AlertType.WARNING, "Empty Field", ButtonType.OK).show();
                }

            });
           tblAttendance.setItems(observableList);
        }
        return observableList;
    }

    public void loadAllStudentId()throws Exception{
        ArrayList<StudentDTO> studentDTOS = attendanceBO.getAllStudent();
        ObservableList<String> all = FXCollections.observableArrayList();
        for (StudentDTO dto : studentDTOS){
            all.add(dto.getSid());
        }
        cmbStudentId.setItems(all);
    }

    public void loadAllSubjectName()throws Exception{
        ArrayList<SubjectDTO>subjectDTOS = attendanceBO.getAllSubject();
        ObservableList<String> all = FXCollections.observableArrayList();
        for (SubjectDTO dto : subjectDTOS){
            all.add(dto.getSubjectname());
        }
        cmbSubjectName.setItems(all);
    }

    private boolean delete(String sid, String date) throws Exception {
        return attendanceBO.deleteAll(sid, date);
    }

    public void AttendOnAction(ActionEvent actionEvent) {
        String stuId = String.valueOf(cmbStudentId.getValue());
        String date = txtDate.getText();
        String time = txtTime.getText();
        String subName = String.valueOf(cmbSubjectName.getValue());

        AttendanceDTO attendanceDTO = new AttendanceDTO(stuId, date, time, subName);
        if (stuId.length()>0 && date.length()>0 && time.length()>0 && subName.length()>0) {
            if (btnAttend.getText().equalsIgnoreCase("Student attended")) {
                try {
                    boolean isAdded = add(attendanceDTO);
                    if (isAdded) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Attended", ButtonType.OK).show();
                        tblAttendance.setItems(getAllAttendance());
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Attend Not Success", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.WARNING, "Attend Not Success", ButtonType.OK).show();
                }
            } else {
                try {
                    boolean isUpdate = update(attendanceDTO);
                    if (isUpdate) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Update Success", ButtonType.OK).show();
                        tblAttendance.setItems(getAllAttendance());
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Attend Not Update", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Empty Fields", ButtonType.OK).show();
        }

    }

    private boolean update(AttendanceDTO attendanceDTO) throws Exception {
        return attendanceBO.update(attendanceDTO);
    }

    private boolean add(AttendanceDTO attendanceDTO) throws Exception {
        return attendanceBO.add(attendanceDTO);
    }

    public void searchOnAction(KeyEvent actionEvent) throws Exception {
        ArrayList<AttendanceDTO> allAttend = attendanceBO.searchAllAttend(txtSearch.getText());
        if (allAttend.size() ==0 ){
            tblAttendance.getItems().clear();
        }else {
            ObservableList<AttendanceTM> tmObservableList = FXCollections.observableArrayList();
            for (AttendanceDTO dto : allAttend){
                Button button = new Button("Delete");
                button.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
                tmObservableList.add(new AttendanceTM(
                        dto.getSid(),
                        dto.getDate(),
                        dto.getArrivaltime(),
                        dto.getSubname(),
                        button
                ));
                button.setOnAction((e)->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Confirmation Delete");
                    alert.setContentText("Are you sure to Delete ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        String sid = String.valueOf(cmbStudentId.getValue());
                        String date = txtDate.getText();
                        try {
                            boolean isDeleted = delete(sid, date);
                            if (isDeleted){
                                //new Alert(Alert.AlertType.CONFIRMATION, "Delete Success", ButtonType.OK).show();
                                tblAttendance.setItems(getAllAttendance());
                            }else {
                                new Alert(Alert.AlertType.WARNING, "Delete Failed", ButtonType.OK).show();
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                    }else {
                        new Alert(Alert.AlertType.WARNING, "Empty Field", ButtonType.OK).show();
                    }

                });
                tblAttendance.setItems(tmObservableList);
            }
        }
    }

    public void ClearOnAction(ActionEvent actionEvent) throws Exception {
        clearAllFields();
        btnAttend.setText("Student Attended");
    }


    public void comboSearchOnAction(KeyEvent keyEvent) throws Exception {
        ArrayList<StudentDTO> studentDTOS = attendanceBO.comboSearchStudent(String.valueOf(cmbStudentId.getValue()));
        ObservableList<String> all = FXCollections.observableArrayList();
        for (StudentDTO dto : studentDTOS){
            all.add(dto.getSid());
        }
        cmbStudentId.setItems(all);
    }
}
