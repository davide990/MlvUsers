package com.upem.users.service;

import com.upem.users.dao.StudentDAO;
import com.upem.users.entities.Student;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
@WebService(serviceName = "StudentService")
public class StudentService {

    @EJB
    private StudentDAO ejbRef;

    @WebMethod(operationName = "createStudent")
    @Oneway
    public void createStudent(@WebParam(name = "student") Student student) {
        ejbRef.createStudent(student);
    }

    @WebMethod(operationName = "updateStudent")
    @Oneway
    public void updateStudent(@WebParam(name = "student") Student student) {
        ejbRef.updateStudent(student);
    }

    @WebMethod(operationName = "deleteStudent")
    @Oneway
    public void deleteStudent(@WebParam(name = "student") Student student) {
        ejbRef.deleteStudent(student);
    }

    @WebMethod(operationName = "getAllStudents")
    public List<Student> getAllStudents() {
        return ejbRef.getAllStudents();
    }

    @WebMethod(operationName = "getStudentByID")
    public Student getStudentByID(@WebParam(name = "student_id") long student_id) {
        return ejbRef.getStudentByID(student_id);
    }

    @WebMethod(operationName = "getStudentByEmail")
    public Student getStudentByEmail(@WebParam(name = "email") String email) {
        return ejbRef.getStudentByEmail(email);
    }

    @WebMethod(operationName = "existsStudent")
    public boolean existsStudent(@WebParam(name = "student_id") long student_id) {
        return ejbRef.existsStudent(student_id);
    }

}
