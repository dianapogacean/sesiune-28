package ro.itschool.demospringdata.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.itschool.demospringdata.dtos.StudentDTO;
import ro.itschool.demospringdata.entities.StudentDetailsEntity;
import ro.itschool.demospringdata.entities.StudentEntity;
import ro.itschool.demospringdata.exceptions.InexistentResourceException;
import ro.itschool.demospringdata.repositories.StudentDetailsRepository;
import ro.itschool.demospringdata.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    public Optional<StudentEntity> findById(int id) {
        return this.studentRepository.findById(id);
    }

    public Iterable<StudentEntity> findAll() {
        return this.studentRepository.findAll();
    }

    @Transactional
    public StudentEntity add(StudentDTO studentDTO) {

        log.info("info in service");
        log.debug("debug in service");
        log.warn("warn in service");
        log.error("error in service");

        StudentDetailsEntity studentDetails = new StudentDetailsEntity();
        studentDetails.setCollege(studentDTO.getCollege());
        studentDetails.setDateEnrolled(studentDTO.getDateEnrolled());
        studentDetails.setNbCoursesEnrolled(studentDTO.getNbCoursesEnrolled());

        StudentDetailsEntity savedDetails = studentDetailsRepository.save(studentDetails);

        StudentEntity student = new StudentEntity();

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setStudentDetailsEntity(savedDetails);

        log.info("Saving to database...");
        StudentEntity studentEntity = studentRepository.save(student);

        log.info("Save successful!");

        return studentEntity;
    }

    public List<StudentEntity> getAllUnenrolledStudents() {
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


    @Transactional
    public StudentEntity updateComplete(int id, StudentDTO studentDTO) throws InexistentResourceException {

        Optional<StudentEntity> optionalStudent = this.studentRepository.findById(id);

        if (!optionalStudent.isPresent()) {
            throw new InexistentResourceException("Student does not exist.", id);
        }

        StudentEntity student = optionalStudent.get();
        student.setEmail(studentDTO.getEmail());
        student.setName(studentDTO.getName());
        student.getStudentDetailsEntity().setCollege(studentDTO.getCollege());
        student.getStudentDetailsEntity().setDateEnrolled(studentDTO.getDateEnrolled());
        student.getStudentDetailsEntity().setNbCoursesEnrolled(studentDTO.getNbCoursesEnrolled());

        return student;

    }

    @Transactional
    public StudentEntity updatePartial(int id, StudentDTO studentDTO) throws InexistentResourceException {


        Optional<StudentEntity> optionalStudent = this.studentRepository.findById(id);

        if (!optionalStudent.isPresent()) {
            throw new InexistentResourceException("Student does not exist.", id);
        }

        StudentEntity student = optionalStudent.get();

        if (studentDTO.getName() != null) {
            student.setName(studentDTO.getName());
        }

        if (studentDTO.getEmail() != null) {
            student.setEmail(studentDTO.getEmail());
        }
        if (studentDTO.getCollege() != null) {
            student.getStudentDetailsEntity().setCollege(studentDTO.getCollege());
        }

        if (studentDTO.getDateEnrolled() != null) {
            student.getStudentDetailsEntity().setDateEnrolled(studentDTO.getDateEnrolled());
        }

        if (studentDTO.getNbCoursesEnrolled() != null) {
            student.getStudentDetailsEntity().setNbCoursesEnrolled(studentDTO.getNbCoursesEnrolled());
        }


        this.studentRepository.save(student);
        return student;

    }


    public void delete(int id) {
        this.studentRepository.deleteById(id);
    }

    public List<StudentEntity> search(String name, String email, LocalDate enrolledBefore) {

       Iterable<StudentEntity> dbStudents = this.studentRepository.findAll();

       //we have a list with ALL students in the db
       List<StudentEntity> studentEntities = new ArrayList<>();

       for (StudentEntity studentEntity : dbStudents){
           studentEntities.add(studentEntity);
       }


       if (name !=null && !name.isEmpty()){
           List<StudentEntity> studentsByName = this.studentRepository.findByNameIgnoreCase(name);
           studentEntities.retainAll(studentsByName);
       }

        if (email !=null && !email.isEmpty()){
            List<StudentEntity> studentsByEmail = this.studentRepository.findByEmailIgnoreCase(email);
            studentEntities.retainAll(studentsByEmail);
        }

        if (enrolledBefore!=null){
            List<StudentEntity> studentsEnrolledBefore =
                    this.studentRepository.findByStudentDetailsEntityDateEnrolledBefore(enrolledBefore);
            studentEntities.retainAll(studentsEnrolledBefore);
        }


        return studentEntities;
    }
}
