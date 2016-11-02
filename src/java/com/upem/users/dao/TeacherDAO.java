package com.upem.users.dao;

import com.upem.users.entities.Teacher;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
@Stateless
public class TeacherDAO {

    @PersistenceContext
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(TeacherDAO.class.getName());

    public void create(Teacher teacher) {
        /* persist:
            - Insert a new register to the database
            - Attach the object to the entity manager.
         */
        logger.log(Level.INFO, "Adding teacher ID: " + teacher.getTeacher_id());
        em.persist(teacher);

    }

    public void update(Teacher teacher) {
        /* merge:
            - Find an attached object with the same id and update it.
            - If exists update and return the already attached object.
            - If doesn't exist insert the new register to the database.
         */
        logger.log(Level.INFO, "Updating teacher ID: " + teacher.getTeacher_id());
        em.merge(teacher);

    }

    public void delete(Teacher teacher) {
        logger.log(Level.INFO, "Deleting teacher ID: " + teacher.getTeacher_id());
        em.remove(teacher);
    }
}
