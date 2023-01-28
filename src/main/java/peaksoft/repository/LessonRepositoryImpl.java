package peaksoft.resources;

import org.hibernate.HibernateException;
import peaksoft.config.Util;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LessonResourceImpl implements LessonResource, AutoCloseable {
    static final EntityManagerFactory entityManagerFactory = Util.getEntityManagerFactory();

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }

    @Override
    public String saveLesson(Long courseId, Lesson lesson) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            lesson.setCourse(course);
            entityManager.persist(lesson);
            entityManager.merge(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            return lesson.getName() + " successfully saved in course " + course.getCourseName();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Lesson updateLesson(Long id, Lesson newLesson) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, id);
            lesson.setName(newLesson.getName());
            lesson.setVideoLink(newLesson.getVideoLink());
            lesson.setCourse(lesson.getCourse());
            entityManager.getTransaction().commit();
            entityManager.close();
            return lesson;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Lesson getLessonById(Long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
            return lesson;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long id) {
        List<Lesson> lessons = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, id);
            lessons.addAll(course.getLessons());
            entityManager.getTransaction().commit();
            entityManager.close();
            return lessons;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
