package com.upem.users.dao;

import com.upem.users.entities.Person;
import com.upem.users.entities.Student;
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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

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
        logger.log(Level.INFO, "Adding student ID: " + student.getId());
        em.persist(student);

    }

    public void updateStudent(Student student) {
        /* merge:
            - Find an attached object with the same id and update it.
            - If exists update and return the already attached object.
            - If doesn't exist insert the new register to the database.
         */
        logger.log(Level.INFO, "Updating student ID: " + student.getId());
        em.merge(student);

    }

    public void deleteStudent(Student student) {
        logger.log(Level.INFO, "Deleting student ID: " + student.getId());
        em.remove(student);
    }

    public List<Student> getAllStudents() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> person = builder.createQuery(Person.class);
        
        //THIS KIND OF QUERY IS NECESSARY SINCE PERSON AND STUDENT ARE IN INHERITANCE RELATIONSHIP
        //JOIN CANNOT BE DONE
        Root<Person> personRoot = person.from(Person.class); //ROOT
        Root<Student> studentRoot = builder.treat(personRoot, Student.class);   //SUBCLASS

        Predicate pd = builder.equal(studentRoot.get("id"), personRoot.get("id"));

        person.select(personRoot).where(pd);

        TypedQuery<Person> pp = em.createQuery(person);

        List<Person> rl = pp.getResultList();
        List<Student> sl = new ArrayList<>();
        rl.stream().forEach((p) -> {
            sl.add((Student) p);
        });

        return sl;

    }

    public Student getStudentByID(int student_id) {
        Student s;
        //TypedQuery<Student> q = em.createQuery("select e from Student e where e.student_id = '" + student_id + "'", Student.class);
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p WHERE p.class='Student' AND p.id='" + student_id + "'", Person.class);

        //TypedQuery<Student> q = em.createQuery("SELECT s FROM Student s Person p JOIN s.id p WHERE s.id = '" + student_id + "'", Student.class);
        try {
            s = (Student) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return s;
    }

    public Student getStudentByEmail(String email) {
        Student s;
        TypedQuery<Student> q = em.createQuery("select e from Student e where e.email = '" + email + "'", Student.class);

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
