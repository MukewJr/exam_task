package org.example.dao;

import org.example.config.Config;
import org.example.model.Course;
import org.example.model.Lesson;

import javax.persistence.EntityManager;
import java.util.List;

public class LessonDaoImpl implements LessonDao{
    @Override
    public void saveLesson(Long id, Lesson lesson) {
        EntityManager entityManager= Config.getEntityManager();
        entityManager.getTransaction().begin();
        Course course=entityManager.find(Course.class,id);
        lesson.setCourse(course);
        entityManager.persist(lesson);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Lesson updateLesson(Long id, Lesson lesson) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Lesson les=entityManager.find(Lesson.class,id);
        les.setVideoLink(lesson.getVideoLink());
        les.setName(lesson.getName());
        entityManager.getTransaction().commit();
        entityManager.close();
        return les;
    }

    @Override
    public Lesson getLessonById(Long id) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Lesson lesson=entityManager.createQuery("select l from Lesson l where   l.id=:id ",Lesson.class).setParameter("id",id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return  lesson;
    }

    @Override
    public List<Lesson> getLessonByCourseId(Long id) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Course course=entityManager.find(Course.class,id);
        List<Lesson> lessons=course.getLessons();
        entityManager.getTransaction().commit();
        entityManager.close();
        return lessons;
    }
}
