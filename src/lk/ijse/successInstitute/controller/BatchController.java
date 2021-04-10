package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.successInstitute.bo.BOFactory;
import lk.ijse.successInstitute.bo.custom.BatchBO;
import lk.ijse.successInstitute.dto.BatchDTO;
import lk.ijse.successInstitute.view.tm.BatchTM;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BatchController {
    static BatchBO batchBO = (BatchBO) BOFactory.getInstance().getBO(BOFactory.BOType.BATCH);

    public JFXTextField txtBatchName;
    public JFXTextField txtBatchid;
    public TableView<BatchTM> tblBatch;
    public JFXButton btnBatch;
    public TableColumn colBatchid;
    public TableColumn colBatchname;
    public TableColumn colOperate;
    public JFXTextField txtSearch;

    public void initialize()throws Exception{
        colBatchid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        colBatchname.setCellValueFactory(new PropertyValueFactory<>("bname"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn1"));
        tblBatch.setItems(getALLBatch());

        // Bot
        tblBatch.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           btnBatch.setText("Update Batch");

            txtBatchid.setText(newValue.getBid());
            txtBatchName.setText(newValue.getBname());



        });

        generateBatchId();
    }

    public void clearAllFields() throws Exception {
        txtBatchid.setText(generateBatchId());
        txtBatchName.setText("");

    }
    private  ObservableList<BatchTM> getALLBatch() throws Exception {
        ArrayList<BatchDTO> batchDTOS = loadAllBatch();
        ObservableList<BatchTM> tmObservableList= FXCollections.observableArrayList();
        for (BatchDTO dto : batchDTOS) {
            Button btn1 = new Button("Delete");
            btn1.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
            tmObservableList.add(
                    new BatchTM(
                            dto.getBid(),
                            dto.getBname(),
                            btn1
                    )
            );
            btn1.setOnAction((e)->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirmation Delete");
                alert.setContentText("Are you sure to Delete ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String bid = txtBatchid.getText();
                    try {
                        boolean isDelete = delete(bid);
                        if (isDelete) {
                            //new Alert(Alert.AlertType.CONFIRMATION, "Batch Removed", ButtonType.OK).show();
                            tblBatch.setItems(getALLBatch());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Removed Failed", ButtonType.OK).show();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
            tblBatch.setItems(tmObservableList);
        }
        return tmObservableList;
    }


    public String generateBatchId()throws Exception{
        String lastID = getLastBatchID();
        if (lastID != null) {
            lastID = lastID.split("[A-Z]")[2];
            lastID = "AL" + (Integer.parseInt(lastID) + 1);
            txtBatchid.setText(lastID);
        } else {
            txtBatchid.setText("AL2019");
        }
        return lastID;
    }

    private String getLastBatchID() throws Exception {
        return  batchBO.getLastCode();
    }


    public void BatchOnAction(ActionEvent actionEvent) {
        String bid = txtBatchid.getText();
        String bname = txtBatchName.getText();

       BatchDTO batchDTO = new BatchDTO(bid, bname);
       if (bid.length()>0 && bname.length()>0) {
           if (btnBatch.getText().equalsIgnoreCase("Add New Batch")) {
               try {
                   boolean isAdded = add(batchDTO);
                   if (isAdded) {
                       new Alert(Alert.AlertType.CONFIRMATION, "Added Success", ButtonType.OK).show();
                       tblBatch.setItems(getALLBatch());
                   } else {
                       new Alert(Alert.AlertType.WARNING, "Added Failed", ButtonType.OK).show();
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
           } else {
               try {
                   boolean isUpdated = updateBatch(batchDTO);
                   if (isUpdated) {
                       new Alert(Alert.AlertType.CONFIRMATION, "Updated", ButtonType.OK).show();
                       tblBatch.setItems(getALLBatch());
                   } else {
                       new Alert(Alert.AlertType.WARNING, "Failed", ButtonType.OK).show();
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }else {
           new Alert(Alert.AlertType.WARNING, "Empty Fields", ButtonType.OK).show();
       }

    }

    private ArrayList<BatchDTO> loadAllBatch() throws Exception {
        return batchBO.getAll();
    }

    private boolean add(BatchDTO batchDTO) throws Exception {
        return batchBO.add(batchDTO);
    }

    private boolean delete(String id)throws Exception{
        return batchBO.delete(id);
    }

    public static boolean updateBatch(BatchDTO batchDTO) throws Exception {
        return batchBO.update(batchDTO);
    }

    public void ClearOnAction(ActionEvent actionEvent) throws Exception {
        clearAllFields();
        btnBatch.setText("Add New Batch");
    }


    public void SearchOnAction(KeyEvent keyEvent) throws Exception {
        ArrayList<BatchDTO> allBatch = batchBO.searchAllBatch(txtSearch.getText());
        if (allBatch.size() == 0) {
            tblBatch.getItems().clear();
        } else {
            ObservableList<BatchTM> tmObservableList = FXCollections.observableArrayList();
            for (BatchDTO dto : allBatch) {
                Button button = new Button("Delete");
                button.setStyle("-fx-background-color: #e76361 ; -fx-text-fill: white");
                tmObservableList.add(
                        new BatchTM(
                                dto.getBid(),
                                dto.getBname(),
                                button
                        ));

                button.setOnAction((e) -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Confirmation Delete");
                    alert.setContentText("Are you sure to Delete ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        String bid = txtBatchid.getText();
                        try {
                            boolean isDelete = delete(bid);
                            if (isDelete) {
                                //new Alert(Alert.AlertType.CONFIRMATION, "Batch Removed", ButtonType.OK).show();
                                tblBatch.setItems(getALLBatch());
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Removed Failed", ButtonType.OK).show();
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                    }
                });
                tblBatch.setItems(tmObservableList);
            }
        }
    }
}
