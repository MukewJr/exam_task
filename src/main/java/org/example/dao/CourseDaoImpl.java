package org.example.dao;

import org.example.config.Config;
import org.example.model.Course;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseDaoImpl implements CourseDao{

    @Override
    public String saveCourse(Course course) {
        EntityManager entityManager= Config.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "course saved";
    }

    @Override
    public Course getCourseById(Long id) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Course courses=entityManager.createQuery("select c from Course c where   c.id=:id ",Course.class).setParameter("id",id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
 return  courses;
    }

    @Override
    public List<Course> getAllCourse() {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        List<Course> courses=entityManager.createQuery("select c from Course c ORDER BY  c.createAt  asc ",Course.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
return courses;
    }

    @Override
    public Course updateCourse(Long id,Course course) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Course crs=entityManager.find(Course.class,id);
        crs.setCourseName(course.getCourseName());
        crs.setCreateAt(course.getCreateAt());
        crs.setDescription(course.getDescription());
        crs.setDuration(course.getDuration());
        crs.setImageLink(course.getImageLink());
        entityManager.getTransaction().commit();
        entityManager.close();

        return crs;
    }

    @Override
    public void deleteCourseById(Long id) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Course course=entityManager.find(Course.class,id);
        entityManager.remove(course);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public Course getCourseByName(String courseName) {
        EntityManager entityManager=Config.getEntityManager();
        entityManager.getTransaction().begin();
        Course courses=entityManager.createQuery("select c from Course c where   c.courseName=:name",Course.class).setParameter("name",courseName).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return  courses;
    }
}
