package peaksoft.resources;

import org.hibernate.HibernateException;
import peaksoft.config.Util;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskResourceImpl implements TaskResource, AutoCloseable {
    EntityManagerFactory entityManagerFactory = Util.getEntityManagerFactory();

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }

    @Override
    public String saveTask(Task task, Long id) {
        List<Task> tasks = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, id);
            tasks.addAll(lesson.getTasks());
            tasks.add(task);
            lesson.setTasks(tasks);
            entityManager.persist(task);
            entityManager.merge(lesson);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Task " + task.getName() + " successfully saved";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Task updateTask(Long id, Task newTask) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Task oldTask = entityManager.find(Task.class, id);
            oldTask.setName(newTask.getName());
            oldTask.setTask(newTask.getTask());
            oldTask.setDeadline(newTask.getDeadline());
            entityManager.getTransaction().commit();
            entityManager.close();
            return oldTask;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long id) {
        List<Task> tasks = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, id);
            tasks.addAll(lesson.getTasks());
            entityManager.getTransaction().commit();
            entityManager.close();
            return tasks;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteTaskById(Long lessonId, Long id) {
        try {
            Task taskNull = new Task();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Task task = entityManager.find(Task.class, id);
            entityManager.detach(task);
            Lesson lesson = entityManager.find(Lesson.class, lessonId);
            List<Task> tasks = lesson.getTasks().stream().filter(s -> s!=task).toList();
            lesson.setTasks(tasks);
            entityManager.remove(task);
            entityManager.getTransaction().commit();
            entityManager.close();
            return task.getName() + " successfully deleted...";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
