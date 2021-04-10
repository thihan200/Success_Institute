package lk.ijse.successInstitute.bo;

import lk.ijse.successInstitute.bo.custom.impl.*;

public class BOFactory {
    static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getInstance(){ return (boFactory == null) ? boFactory = new BOFactory() : boFactory; }

    public enum BOType{
        BATCH, STUDENT, ATTENDANCE, PAYMENT, CLASS, SUBJECT, TEACHER, TIMETABLE, EMPLOYEE
    }

    public SuperBO getBO(BOType boType){
        switch (boType){
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case TIMETABLE:
                return new TimetableBOImpl();
            case TEACHER:
                return new TeacherBOImpl();
            case CLASS:
                return new ClassBOImpl();
            case SUBJECT:
                return new SubjectBOImpl();
            case STUDENT:
                return new StudentAndRegisterBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case BATCH:
                return new BatchBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            default:
                return null;

        }
    }
}
