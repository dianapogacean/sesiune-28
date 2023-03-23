package ro.itschool.demospringdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.itschool.demospringdata.entities.StudentDetailsEntity;
import ro.itschool.demospringdata.entities.StudentEntity;
import ro.itschool.demospringdata.repositories.StudentDetailsRepository;
import ro.itschool.demospringdata.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    @Transactional
    public StudentEntity add(String name, String email, String college, LocalDate dateEnrolled, int nbCoursesEnrolled) {

        StudentDetailsEntity studentDetails = new StudentDetailsEntity();
        studentDetails.setCollege(college);
        studentDetails.setDateEnrolled(dateEnrolled);
        studentDetails.setNbCoursesEnrolled(nbCoursesEnrolled);

        StudentDetailsEntity savedDetails = studentDetailsRepository.save(studentDetails);

        StudentEntity student = new StudentEntity();

        student.setName(name);
        student.setEmail(email);
        student.setStudentDetailsEntity(savedDetails);

        return studentRepository.save(student);

    }

    public List<StudentEntity> getAllUnenrolledStudents(){
//        Iterable<StudentEntity> allStudents = studentRepository.findAll();
//
//        List<StudentEntity> unenrolledStudents = new ArrayList<>();
//
//        for (StudentEntity student : allStudents){
//            if (student.getCourses().isEmpty()){
//                unenrolledStudents.add(student);
//            }
//        }
//
//        return unenrolledStudents;

        return studentRepository.findByCoursesIsEmpty();
    }
}
