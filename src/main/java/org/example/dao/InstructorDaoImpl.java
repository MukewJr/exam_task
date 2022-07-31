package org.example.dao;

import org.example.config.Config;
import org.example.model.Course;
import org.example.model.Instructor;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class InstructorDaoImpl implements InstructorDao{
    @Override
    public void saveInstructor(Instructor instructor) {
        EntityManager entityManager= Config.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(instructor);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Instructor updateInstructor(Long id,Instructor instructor) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Instructor ins=entityManager.find(Instructor.class,id);
        ins.setFirstName(instructor.getFirstName());
        ins.setLastName(instructor.getLastName());
        ins.setEmail(instructor.getEmail());
        ins.setPhoneNumber(instructor.getPhoneNumber());
        entityManager.getTransaction().commit();
        entityManager.close();
        return ins;
    }

    @Override
    public Instructor getInsById(Long id) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Instructor instructor=entityManager.createQuery("select i from Instructor i where   i.id=: id",Instructor.class).setParameter("id",id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return instructor;


    }

    @Override
    public List<Instructor> getInsByCourseId(Long id) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Course course=entityManager.find(Course.class,id);
        List<Instructor> instructors=course.getInstructors();
        entityManager.getTransaction().commit();
        entityManager.close();
        return instructors;
    }

    @Override
    public void deleteInsById(Long id) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Instructor instructor=entityManager.find(Instructor.class,id);
        entityManager.remove(instructor);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void assignInsToCourse(Long course_id,Long instructor_id) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
       Course course= entityManager.find(Course.class,course_id);
       Instructor instructor= entityManager.find(Instructor.class,instructor_id);
       course.addIns(instructor);
       instructor.addCourse(course);
       entityManager.persist(course);
       entityManager.getTransaction().commit();
       entityManager.close();
    }
}
