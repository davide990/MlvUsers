package com.upem.users.dao;

import com.upem.users.entities.Person;
import com.upem.users.entities.Student;
import com.upem.users.entities.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> person = builder.createQuery(Person.class);

        //THIS KIND OF QUERY IS NECESSARY SINCE PERSON AND STUDENT ARE IN INHERITANCE RELATIONSHIP
        //JOIN CANNOT BE DONE
        Root<Person> personRoot = person.from(Person.class); //ROOT
        Root<Teacher> teacherRoot = builder.treat(personRoot, Teacher.class);   //SUBCLASS

        Predicate pd = builder.equal(teacherRoot.get("id"), personRoot.get("id"));

        person.select(personRoot).where(pd);

        TypedQuery<Person> pp = em.createQuery(person);

        List<Person> rl = pp.getResultList();
        List<Teacher> sl = new ArrayList<>();
        rl.stream().forEach((p) -> {
            sl.add((Teacher) p);
        });

        return sl;
    }

    public Teacher getTeacherByID(long teacher_id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> person = builder.createQuery(Person.class);

        Root<Person> personRoot = person.from(Person.class); //ROOT
        Root<Teacher> studentRoot = builder.treat(personRoot, Teacher.class);   //SUBCLASS

        Predicate pd_1 = builder.equal(studentRoot.get("id"), personRoot.get("id"));
        Predicate pd_2 = builder.equal(studentRoot.get("id"), teacher_id);
        Predicate pd_3 = builder.and(pd_1, pd_2);

        person.select(personRoot).where(pd_3);
        TypedQuery<Person> pp = em.createQuery(person);
        return (Teacher) pp.getSingleResult();
    }

    public Teacher getTeacherByEmail(String email) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> person = builder.createQuery(Person.class);

        Root<Person> personRoot = person.from(Person.class); //ROOT
        Root<Student> studentRoot = builder.treat(personRoot, Student.class);   //SUBCLASS

        Predicate pd_1 = builder.equal(studentRoot.get("id"), personRoot.get("id"));
        Predicate pd_2 = builder.equal(studentRoot.get("email"), email);
        Predicate pd_3 = builder.and(pd_1, pd_2);

        person.select(personRoot).where(pd_3);
        TypedQuery<Person> pp = em.createQuery(person);
        return (Teacher) pp.getSingleResult();
    }

    public boolean existsTeacher(long teacher_id) {
        return getTeacherByID(teacher_id) != null;
    }
}
