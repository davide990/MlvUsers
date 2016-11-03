/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upem.users.service;

import com.upem.users.dao.TeacherDAO;
import com.upem.users.entities.Teacher;
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
@WebService(serviceName = "TeacherService")
public class TeacherService {

    @EJB
    private TeacherDAO ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "createTeacher")
    @Oneway
    public void createTeacher(@WebParam(name = "teacher") Teacher teacher) {
        ejbRef.createTeacher(teacher);
    }

    @WebMethod(operationName = "updateTeacher")
    @Oneway
    public void updateTeacher(@WebParam(name = "teacher") Teacher teacher) {
        ejbRef.updateTeacher(teacher);
    }

    @WebMethod(operationName = "deleteTeacher")
    @Oneway
    public void deleteTeacher(@WebParam(name = "teacher") Teacher teacher) {
        ejbRef.deleteTeacher(teacher);
    }

    @WebMethod(operationName = "getAllTeachers")
    public List<Teacher> getAllTeachers() {
        return ejbRef.getAllTeachers();
    }

    @WebMethod(operationName = "getTeacherByID")
    public Teacher getTeacherByID(@WebParam(name = "teacher_id") int teacher_id) {
        return ejbRef.getTeacherByID(teacher_id);
    }

    @WebMethod(operationName = "getTeacherByEmail")
    public Teacher getTeacherByEmail(@WebParam(name = "email") String email) {
        return ejbRef.getTeacherByEmail(email);
    }

    @WebMethod(operationName = "existsTeacher")
    public boolean existsTeacher(@WebParam(name = "teacher_id") int teacher_id) {
        return ejbRef.existsTeacher(teacher_id);
    }
    
}
