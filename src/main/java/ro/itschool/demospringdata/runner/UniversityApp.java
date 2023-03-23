package ro.itschool.demospringdata.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.itschool.demospringdata.service.EnrolmentService;
import ro.itschool.demospringdata.service.StudentService;

@Component
public class UniversityApp implements CommandLineRunner {

    @Autowired
    private StudentService studentService;
    @Autowired
    private EnrolmentService enrolmentService;


    @Override
    public void run(String... args) throws Exception {

//        studentService.add("abcdd", "abc@cd.com", "abc college", LocalDate.now(), 10);
        enrolmentService.enrol(9,1);
    }
}
