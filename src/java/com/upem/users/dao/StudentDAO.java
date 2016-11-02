package com.upem.users.dao;

import com.upem.users.entities.Student;
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
public class StudentDAO {

    @PersistenceContext
    private EntityManager em;

    private final static Logger logger = Logger.getLogger(StudentDAO.class.getName());

    public void create(Student student) {
        /* persist:
            - Insert a new register to the database
            - Attach the object to the entity manager.
         */
        logger.log(Level.INFO, "Adding student ID: " + student.getStudent_id());
        em.persist(student);

    }

    public void update(Student student) {
        /* merge:
            - Find an attached object with the same id and update it.
            - If exists update and return the already attached object.
            - If doesn't exist insert the new register to the database.
         */
        logger.log(Level.INFO, "Updating student ID: " + student.getStudent_id());
        em.merge(student);

    }

    public void delete(Student student) {
        logger.log(Level.INFO, "Deleting student ID: " + student.getStudent_id());
        em.remove(student);
    }
}
