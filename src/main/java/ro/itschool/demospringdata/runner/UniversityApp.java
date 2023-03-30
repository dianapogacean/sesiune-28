package ro.itschool.demospringdata.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.itschool.demospringdata.entities.StudentEntity;
import ro.itschool.demospringdata.service.CourseService;
import ro.itschool.demospringdata.service.EnrolmentService;
import ro.itschool.demospringdata.service.StudentService;
import ro.itschool.demospringdata.service.TeacherService;

import java.util.List;

//@Component
public class UniversityApp implements CommandLineRunner {

    @Autowired
    private StudentService studentService;
    @Autowired
    private EnrolmentService enrolmentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;


    @Override
    public void run(String... args) throws Exception {

//        studentService.add("abcdd", "abc@cd.com", "abc college", LocalDate.now(), 10);
//        enrolmentService.enrol(9,1);

//        teacherService.add("name", "email@email.com", "college", "address nr. 2" ,5,"biology");

      //  courseService.add("math", "numbers", 8);

        List<StudentEntity> unenrolled = studentService.getAllUnenrolledStudents();

        System.out.println(unenrolled);
    }
}
