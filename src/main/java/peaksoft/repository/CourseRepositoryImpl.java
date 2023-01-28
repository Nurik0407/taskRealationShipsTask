package peaksoft.resources;

import org.hibernate.HibernateException;
import peaksoft.config.Util;
import peaksoft.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class CourseResourceImpl implements CourseResource, AutoCloseable {

    private final EntityManagerFactory entityManagerFactory = Util.getEntityManagerFactory();

    @Override
    public void close() throws Exception {
        assert entityManagerFactory != null;
        entityManagerFactory.close();
    }

    @Override
    public String saveCourse(Course course) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            return course.getCourseName() + " successfully saved";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Course getCourseById(Long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
            return course;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Course> getAllCourse(String ascendingOrDescending) {
        String hql1 = "select l from Course l order by creatAt";
        String hql2 = "select l from Course l order by creatAt desc ";
        String hql = "";
        if (ascendingOrDescending.toLowerCase().equals("asc")) hql = hql1;
        if (ascendingOrDescending.toLowerCase().equals("desc")) hql = hql2;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Course> courses = entityManager.createQuery(hql, Course.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return courses;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course oldCourse = entityManager.find(Course.class, id);
            oldCourse.setCourseName(course.getCourseName());
            oldCourse.setDescription(course.getDescription());
            oldCourse.setDuration(course.getDuration());
            oldCourse.setLessons(oldCourse.getLessons());
            oldCourse.setInstructors(oldCourse.getInstructors());
            oldCourse.setImagineLink(course.getImagineLink());
            oldCourse.setCreatAt(course.getCreatAt());
            entityManager.getTransaction().commit();
            entityManager.close();
            return oldCourse;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteCourseById(Long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, id);
            course.getLessons().stream().forEach(s->entityManager.remove(s));
            course.setLessons(null);
            course.getInstructors().stream().forEach(s->s.setCourses(null));
            course.setInstructors(null);
            entityManager.remove(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            return " successfully deleted...";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Course getCourseByName(String courseName) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.createQuery("select l from Course  l where l.courseName=:name", Course.class)
                    .setParameter("name", courseName).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return course;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
