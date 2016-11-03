package com.upem.users.dao;

import com.upem.users.entities.Student;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
@Stateless
public class StudentDAO {

    @PersistenceContext
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(StudentDAO.class.getName());

    public void createStudent(Student student) {
        /* persist:
            - Insert a new register to the database
            - Attach the object to the entity manager.
         */
        logger.log(Level.INFO, "Adding student ID: " + student.getStudent_id());
        em.persist(student);

    }

    public void updateStudent(Student student) {
        /* merge:
            - Find an attached object with the same id and update it.
            - If exists update and return the already attached object.
            - If doesn't exist insert the new register to the database.
         */
        logger.log(Level.INFO, "Updating student ID: " + student.getStudent_id());
        em.merge(student);

    }

    public void deleteStudent(Student student) {
        logger.log(Level.INFO, "Deleting student ID: " + student.getStudent_id());
        em.remove(student);
    }

    public List<Student> getAllStudents() {
        TypedQuery<Student> q = em.createQuery("select e from student e", Student.class);
        return q.getResultList();
    }

    public Student getStudentByID(int student_id) {
        Student s;
        TypedQuery<Student> q = em.createQuery("select e from student e where e.student_id = '" + student_id + "'", Student.class);

        try {
            s = q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return s;
    }

    public Student getStudentByEmail(String email) {
        Student s;
        TypedQuery<Student> q = em.createQuery("select e from student e where e.email = '" + email + "'", Student.class);

        try {
            s = q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return s;
    }

    public boolean existsStudent(int student_id) {
        return getStudentByID(student_id) != null;
    }

}
