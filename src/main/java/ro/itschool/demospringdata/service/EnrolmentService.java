package ro.itschool.demospringdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.demospringdata.entities.CourseEntity;
import ro.itschool.demospringdata.entities.StudentEntity;
import ro.itschool.demospringdata.exceptions.InexistentResourceException;
import ro.itschool.demospringdata.repositories.CourseRepository;
import ro.itschool.demospringdata.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EnrolmentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public void enrol(int studentId, int idCourse) throws InexistentResourceException {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(studentId);
        if (!optionalStudent.isPresent()) {
            throw new InexistentResourceException("Student does not exist!", studentId);
        }

        Optional<CourseEntity> optionalCourse = courseRepository.findById(idCourse);
        if (!optionalCourse.isPresent()) {
            throw new InexistentResourceException("Course does not exist!", idCourse);
        }

        StudentEntity student = optionalStudent.get();
        CourseEntity course = optionalCourse.get();

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
    }
}
