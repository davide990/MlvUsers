package com.upem.users.dao;

import com.upem.users.entities.Teacher;
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
public class TeacherDAO {

    @PersistenceContext
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(TeacherDAO.class.getName());

    public void createTeacher(Teacher teacher) {
        /* persist:
            - Insert a new register to the database
            - Attach the object to the entity manager.
         */
        logger.log(Level.INFO, "Adding teacher ID: " + teacher.getId());
        em.persist(teacher);

    }

    public void updateTeacher(Teacher teacher) {
        /* merge:
            - Find an attached object with the same id and update it.
            - If exists update and return the already attached object.
            - If doesn't exist insert the new register to the database.
         */
        logger.log(Level.INFO, "Updating teacher ID: " + teacher.getId());
        em.merge(teacher);

    }

    public void deleteTeacher(Teacher teacher) {
        logger.log(Level.INFO, "Deleting teacher ID: " + teacher.getId());
        em.remove(teacher);
    }

    public List<Teacher> getAllTeachers() {
        TypedQuery<Teacher> q = em.createQuery("select e from teacher e", Teacher.class);
        return q.getResultList();
    }

    public Teacher getTeacherByID(int teacher_id) {
        Teacher s;
        TypedQuery<Teacher> q = em.createQuery("select e from teacher e where e.teacher_id = '" + teacher_id + "'", Teacher.class);

        try {
            s = q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return s;
    }

    public Teacher getTeacherByEmail(String email) {
        Teacher s;
        TypedQuery<Teacher> q = em.createQuery("select e from teacher e where e.email = '" + email + "'", Teacher.class);

        try {
            s = q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return s;
    }

    public boolean existsTeacher(int teacher_id) {
        return getTeacherByID(teacher_id) != null;
    }
}
