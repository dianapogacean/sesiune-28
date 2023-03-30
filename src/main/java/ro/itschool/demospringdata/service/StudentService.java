package ro.itschool.demospringdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.itschool.demospringdata.dtos.StudentDTO;
import ro.itschool.demospringdata.entities.StudentDetailsEntity;
import ro.itschool.demospringdata.entities.StudentEntity;
import ro.itschool.demospringdata.exceptions.InexistentResourceException;
import ro.itschool.demospringdata.repositories.StudentDetailsRepository;
import ro.itschool.demospringdata.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
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

        StudentDetailsEntity studentDetails = new StudentDetailsEntity();
        studentDetails.setCollege(studentDTO.getCollege());
        studentDetails.setDateEnrolled(studentDTO.getDateEnrolled());
        studentDetails.setNbCoursesEnrolled(studentDTO.getNbCoursesEnrolled());

        StudentDetailsEntity savedDetails = studentDetailsRepository.save(studentDetails);

        StudentEntity student = new StudentEntity();

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setStudentDetailsEntity(savedDetails);

        return studentRepository.save(student);

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
}
