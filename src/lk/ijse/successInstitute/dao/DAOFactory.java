package lk.ijse.successInstitute.dao;

import lk.ijse.successInstitute.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getInstance(){ return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory; }

    public enum DAOType{
        BATCH, STUDENT, ATTENDANCE, PAYMENT, CLASS, SUBJECT, TEACHER, TIMETABLE, EMPLOYEE, REGISTRATION
    }

    public SuperDAO getDAO(DAOType types){
        switch (types){
            case BATCH:
                return new BatchDAOImpl();
            case CLASS:
                return new ClassDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case SUBJECT:
                return new SubjectDAOImpl();
            case TEACHER:
                return new TeacherDAOImpl();
            case TIMETABLE:
                return new TimetableDAOImpl();
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            default:
                return null;
        }
    }

}
