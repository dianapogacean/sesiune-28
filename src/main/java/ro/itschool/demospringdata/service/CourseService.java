package ro.itschool.demospringdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.demospringdata.entities.CourseEntity;
import ro.itschool.demospringdata.entities.TeachersEntity;
import ro.itschool.demospringdata.repositories.TeacherRepository;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private TeacherRepository teacherRepository;

    public void add(String name, String details,int teacherId){

        TeachersEntity foundTeacher = teacherRepository.findById(teacherId).orElseThrow();

        CourseEntity course = new CourseEntity();
        course.setName(name);
        course.setDetails(details);

        foundTeacher.getCourses().add(course);
        course.setTeacher(foundTeacher);

        teacherRepository.save(foundTeacher);
    }
}
