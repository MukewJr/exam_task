package org.example.dao;

import org.example.model.Instructor;

import java.util.List;

public interface InstructorDao {
    void saveInstructor(Instructor instructor);
    Instructor updateInstructor(Long id,Instructor instructor);
    Instructor getInsById(Long id);
    List<Instructor> getInsByCourseId(Long id);
    void deleteInsById(Long id);
    void assignInsToCourse(Long course_id,Long instructor_id);
}
