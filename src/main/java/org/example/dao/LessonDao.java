package org.example.dao;

import org.example.model.Lesson;

import java.util.List;

public interface LessonDao {
    void saveLesson(Long id, Lesson lesson);
    Lesson updateLesson(Long id, Lesson lesson);
    Lesson getLessonById(Long id);
    List<Lesson> getLessonByCourseId(Long id);
}
