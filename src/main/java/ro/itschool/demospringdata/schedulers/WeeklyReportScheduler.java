package ro.itschool.demospringdata.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.itschool.demospringdata.entities.TeachersEntity;
import ro.itschool.demospringdata.repositories.CourseStudentCount;
import ro.itschool.demospringdata.repositories.TeacherRepository;
import ro.itschool.demospringdata.service.EmailService;

import java.util.List;

@Component
public class WeeklyReportScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Scheduled(cron = "0 0 8 * * MON")
    public void sendReport() {

        Iterable<TeachersEntity> teachersEntityList = this.teacherRepository.findAll();

        for (TeachersEntity teacher : teachersEntityList) {

            //get info from db
            List<CourseStudentCount> courseStudentCountList =
                    this.teacherRepository.countStudentsInCourses(teacher.getId());

            //generate csv report - write file in src/main/resources folder

            //send generated file to teacher.getEmail()

            //in a finally block, cleanup resources: delete generated csv file
        }

    }
}
