package lk.ijse.successInstitute.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.util.Duration;
import lk.ijse.successInstitute.bo.BOFactory;
import lk.ijse.successInstitute.bo.custom.ClassBO;
import lk.ijse.successInstitute.bo.custom.StudentAndRegisterBO;
import lk.ijse.successInstitute.bo.custom.TeacherBO;
import lk.ijse.successInstitute.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class DefaultFormController {


    public Label lblDate;
    public Label lblTime;
    public JFXButton btnMonthly;
    public JFXButton btndaily;
    public JFXButton btnYearly;
    public Label lblStudentCount;
    public Label lblTeacherCount;
    public Label lblClassroomCount;

    StudentAndRegisterBO studentBO = (StudentAndRegisterBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    TeacherBO teacherBO = (TeacherBO) BOFactory.getInstance().getBO(BOFactory.BOType.TEACHER);
    ClassBO classBO = (ClassBO) BOFactory.getInstance().getBO(BOFactory.BOType.CLASS);

    public void initialize() throws Exception {
        getTimeDate();
        getStudentCount();
        getTeacherCount();
        getClassCount();
    }

    private void getTimeDate() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd ");
        lblDate.setText(sdf.format(d));
    }

    public int getStudentCount()throws Exception{

        int count = Integer.parseInt(studentBO.getStudentCount());
        lblStudentCount.setText(String.valueOf(count));

        return count;
    }

    public int getTeacherCount()throws Exception{
        int count = Integer.parseInt(teacherBO.getTeacherCount());
        lblTeacherCount.setText(String.valueOf(count));
        return count;
    }

    public int getClassCount()throws Exception{
        int count = Integer.parseInt(classBO.getClassCount());
        lblClassroomCount.setText(String.valueOf(count));
        return count;
    }

    public void monthlyOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("../report/successMonthlyReport.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint js = JasperFillManager.fillReport(jr, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(js, false);
            //JasperPrintManager.printReport(js, true);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dailyOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("../report/successDailyReport.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            HashMap hs = new HashMap();
            hs.put("Date", lblDate.getText());

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

    public void yearlyOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("../report/successYearReport.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint js = JasperFillManager.fillReport(jr, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(js, false);
            //JasperPrintManager.printReport(js, true);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
