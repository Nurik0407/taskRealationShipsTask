package peaksoft.resources;

import org.hibernate.HibernateException;
import peaksoft.config.Util;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructorResourceImpl implements InstructorResource, AutoCloseable {
    static final EntityManagerFactory entityManagerFactory = Util.getEntityManagerFactory();

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }

    @Override
    public String saveInstructor(Instructor instructor) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructor.getFirstname() + " successfully saved";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Instructor updateInstructor(Long id, Instructor newInstructor) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor oldInstructor = entityManager.find(Instructor.class, id);
            oldInstructor.setFirstname(newInstructor.getFirstname());
            oldInstructor.setLastName(newInstructor.getLastName());
            oldInstructor.setEmail(newInstructor.getEmail());
            oldInstructor.setPhoneNumber(newInstructor.getPhoneNumber());
            entityManager.getTransaction().commit();
            entityManager.close();
            return oldInstructor;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Instructor getInstructorById(Long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.find(Instructor.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructor;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long id) {
        List<Instructor> list = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, id);
            list.addAll(course.getInstructors());
            entityManager.getTransaction().commit();
            entityManager.close();
            return list;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteInstructorById(Long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.find(Instructor.class, id);
            entityManager.detach(instructor);
            entityManager.remove(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructor.getFirstname() + " successfully deleted..";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignInstructorToCourse(Long courseIs, Long instructorId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseIs);
            Instructor instructor = entityManager.find(Instructor.class, instructorId);
            List<Course> courses = new ArrayList<>(instructor.getCourses());
            List<Instructor> instructors = new ArrayList<>(course.getInstructors());
            instructors.add(instructor);
            courses.add(course);
            instructor.setCourses(courses);
            course.setInstructors(instructors);
            entityManager.merge(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructor.getFirstname() + " is successfully assigned as a " + course.getCourseName() + " course instructor";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
