package org.example.dao;

import org.example.model.Course;

import java.util.List;

public interface CourseDao {
    String saveCourse(Course course);
    Course getCourseById(Long id);
    List<Course> getAllCourse();
    Course updateCourse(Long id,Course course);
    void deleteCourseById(Long id);
    Course getCourseByName(String courseName);
}
