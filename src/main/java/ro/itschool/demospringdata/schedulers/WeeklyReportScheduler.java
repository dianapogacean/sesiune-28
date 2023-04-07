package ro.itschool.demospringdata.schedulers;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.itschool.demospringdata.entities.TeachersEntity;
import ro.itschool.demospringdata.repositories.CourseStudentCount;
import ro.itschool.demospringdata.repositories.TeacherRepository;
import ro.itschool.demospringdata.service.EmailService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Component
@Slf4j
public class WeeklyReportScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Scheduled(cron = "0 0 8 * * MON")
    public void sendReport() {

        log.trace("Fetching all teachers...");
        Iterable<TeachersEntity> teachersEntityList = this.teacherRepository.findAll();
        log.trace("Teachers fetched sucessfully.");

        log.debug("Generating reports for all teachers.");
        for (TeachersEntity teacher : teachersEntityList) {

            log.trace("Generate report for teacher with name {}", teacher.getName());

            log.trace("Get student count for each course from database.");
            List<CourseStudentCount> courseStudentCountList =
                    this.teacherRepository.countStudentsInCourses(teacher.getId());


            try (StringWriter stringWriter = new StringWriter()) {

                CSVWriter csvWriter = new CSVWriter(stringWriter);

                StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(csvWriter).build();

                beanToCsv.write(courseStudentCountList);

                log.debug("Generating report...");
                String csvReport = stringWriter.toString();

                log.debug("Generated sucessfully.");

                csvWriter.close();

                log.debug("Sending email to {}", teacher.getEmail());
                this.emailService.sendEmail(teacher.getEmail(), "Weekly report", "Hello! This is your report.", csvReport);
                log.debug("Email sent sucessfully!");

            } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                log.error("Something exception happened when creating report for teacher with name {} due to {}", teacher.getName(), e.getMessage());
            } catch (MessagingException e) {
                log.error("Something exception happened when sending email to {} due to {}", teacher.getEmail(), e.getMessage());
            }

        }

    }
}
