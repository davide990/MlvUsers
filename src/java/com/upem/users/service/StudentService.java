package com.upem.users.service;

import com.upem.users.dao.StudentDAO;
import com.upem.users.entities.Student;
import com.upem.users.service.bank.binding.Compte_;
import com.upem.users.service.bank.client.BankServiceClient;
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
    public Student getStudentByID(@WebParam(name = "student_id") int student_id) {
        return ejbRef.getStudentByID(student_id);
    }

    @WebMethod(operationName = "getStudentByEmail")
    public Student getStudentByEmail(@WebParam(name = "email") String email) {
        return ejbRef.getStudentByEmail(email);
    }

    @WebMethod(operationName = "existsStudent")
    public boolean existsStudent(@WebParam(name = "student_id") int student_id) {
        return ejbRef.existsStudent(student_id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "studentHasEnoughMoney")
    public Boolean studentHasEnoughMoney(@WebParam(name = "student_id") int student_id,
            @WebParam(name = "purchase_amount") int purchase_amount) {

        String iban = ejbRef.getStudentByID(student_id).getIban();
        Compte_ compte = BankServiceClient.getAccountByIBAN(iban);

        if (compte == null) {
            return false;
        }

        return compte.getAmount() >= purchase_amount;
    }

}
