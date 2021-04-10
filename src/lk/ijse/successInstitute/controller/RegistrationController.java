package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.successInstitute.bo.BOFactory;
import lk.ijse.successInstitute.bo.custom.StudentAndRegisterBO;
import lk.ijse.successInstitute.dto.RegistrationDTO;

import java.util.ArrayList;

public class RegistrationController {
    public TableColumn colRegId;
    public TableColumn colRegDate;
    public TableColumn colStuId;
    public TableColumn colOperate;
    public TableView<RegistrationDTO> tblRegistration;
    public JFXTextField txtSearch;

    static StudentAndRegisterBO studentAndRegisterBO = (StudentAndRegisterBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);



    public void initialize()throws Exception{
        colRegId.setCellValueFactory(new PropertyValueFactory<>("regId"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStuId.setCellValueFactory(new PropertyValueFactory<>("stuId"));

        tblRegistration.setItems(getAllRegistration());

    }

    private ObservableList<RegistrationDTO> getAllRegistration()throws Exception{
        ArrayList<RegistrationDTO> registrationDTOS = studentAndRegisterBO.getAllRegistration();
        ObservableList<RegistrationDTO> tmObservableList = FXCollections.observableArrayList();
        for (RegistrationDTO dto : registrationDTOS){
            tmObservableList.add(new RegistrationDTO(
                    dto.getRegId(),
                    dto.getDate(),
                    dto.getStuId()
            ));
            tblRegistration.setItems(tmObservableList);
        }
        return tmObservableList;
    }

    public void searchOnAction(KeyEvent keyEvent) throws Exception {
        ArrayList<RegistrationDTO>registrationDTOS = studentAndRegisterBO.searchAllRegistration(txtSearch.getText());
        if (registrationDTOS.size() == 0){
            tblRegistration.getItems().clear();
        }else {
            ObservableList<RegistrationDTO> tmObservableList = FXCollections.observableArrayList();
            for (RegistrationDTO dto : registrationDTOS) {
                tmObservableList.add(new RegistrationDTO(
                        dto.getRegId(),
                        dto.getDate(),
                        dto.getStuId()
                ));
                tblRegistration.setItems(tmObservableList);
            }
        }
    }
}
