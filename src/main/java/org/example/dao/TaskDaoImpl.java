package org.example.dao;

import org.example.config.Config;
import org.example.model.Course;
import org.example.model.Lesson;
import org.example.model.Task;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskDaoImpl implements TaskDao{
    @Override
    public void saveTask(Long id, Task task) {
        EntityManager entityManager= Config.getEntityManager();
        entityManager.getTransaction().begin();
        Lesson lesson=entityManager.find(Lesson.class,id);
        task.setLesson(lesson);
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Task updateTask(Long id, Task task) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Task task1 =entityManager.find(Task.class,id);
        task1.setName(task.getName());
        task1.setDeadLine(task.getDeadLine());
        task1.setTask(task.getTask());
        entityManager.getTransaction().commit();
        entityManager.close();
        return task1;
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long id) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Lesson lesson=entityManager.find(Lesson.class,id);
        List<Task> tasks=lesson.getTasks();
        entityManager.getTransaction().commit();
        entityManager.close();
        return tasks;    }

    @Override
    public void deleteTaskById(Long id) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Task task=entityManager.find(Task.class,id);
        entityManager.remove(task);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
