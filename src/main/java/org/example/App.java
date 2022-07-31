package org.example;

import org.example.config.Config;
import org.example.dao.*;
import org.example.model.Course;
import org.example.model.Instructor;
import org.example.model.Lesson;
import org.example.model.Task;

import java.time.LocalDate;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Config.getEntityManager();
        CourseDaoImpl cd=new CourseDaoImpl();
        System.out.println(cd.saveCourse(new Course("php", 9, LocalDate.of(2021, 12, 11), "image", "very nice")));
        System.out.println(cd.getCourseById(1l));
        cd.getAllCourse().forEach(System.out::println);
        System.out.println(cd.getCourseByName("php"));
        cd.deleteCourseById(2l);
        System.out.println(cd.updateCourse(1l, new Course("php", 7, LocalDate.of(2019, 01, 11),"jpg", "easy")));
//    ****************************************************************************************************
        InstructorDao ins=new InstructorDaoImpl();
        ins.saveInstructor(new Instructor("Mukesh","Kurbanov","mukesh@gmail.com",705609809));
        System.out.println(ins.updateInstructor(3L,new Instructor("Mukew","malikov","jhdjhv",65564)));
        System.out.println(ins.getInsById(1L));
        ins.assignInsToCourse(1L,1L);
        ins.getInsByCourseId(1l);
        ins.deleteInsById(1l);
//    **************************************************************************
        LessonDao ls=new LessonDaoImpl();
        ls.saveLesson(1l,new Lesson("Map","video"));
        System.out.println(ls.getLessonByCourseId(1l));
        System.out.println(ls.updateLesson(2L, new Lesson("Database", "error")));

//    **************************************************************************************************************
        TaskDao ts=new TaskDaoImpl();
        ts.saveTask(1l,new Task("project1",11,"do homework"));
        ts.deleteTaskById(2l);
        ts.getAllTaskByLessonId(1l);
        ts.updateTask(1l,new Task("homework4",12,"atkargyla"));

    }
}
