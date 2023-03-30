package ro.itschool.demospringdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.demospringdata.dtos.StudentDTO;
import ro.itschool.demospringdata.dtos.StudentListDTO;
import ro.itschool.demospringdata.entities.StudentEntity;
import ro.itschool.demospringdata.exceptions.InexistentResourceException;
import ro.itschool.demospringdata.service.EnrolmentService;
import ro.itschool.demospringdata.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentResource {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EnrolmentService enrolmentService;

    @GetMapping   // GET /students
    public ResponseEntity<StudentListDTO> getAll() {
//        Iterable<StudentEntity> dbStudents = this.studentService.findAll();
//        List<StudentDTO> studentDTOList = new ArrayList<>();
//        for (StudentEntity studentEntity : dbStudents) {
//            studentDTOList.add(StudentDTO.from(studentEntity));
//        }
//
//        StudentListDTO studentListDTO = new StudentListDTO();
//        studentListDTO.setStudents(studentDTOList);
//
//        return new ResponseEntity<>(studentListDTO, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //DTO - Data Transfer Object
    @GetMapping("/{id}") // GET /students/{id}
    public ResponseEntity<StudentDTO> getById(@PathVariable("id") Integer id) {
        Optional<StudentEntity> optionalStudentEntity = this.studentService.findById(id);
        if (!optionalStudentEntity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
        }

        //student exists
        StudentEntity studentEntity = optionalStudentEntity.get();
        StudentDTO studentDTO = StudentDTO.from(studentEntity);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK); //200
    }

    @PostMapping //POST /students
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO student) {
        StudentEntity studentEntity = this.studentService.add(student);
        return new ResponseEntity<>(StudentDTO.from(studentEntity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateComplete(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO) {
        try {
            StudentEntity studentEntity = this.studentService.updateComplete(id, studentDTO);

            StudentDTO responseDto = StudentDTO.from(studentEntity);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (InexistentResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> updatePartial(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO) {
        try {
            StudentEntity studentEntity = this.studentService.updatePartial(id, studentDTO);

            StudentDTO responseDto = StudentDTO.from(studentEntity);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (InexistentResourceException e) {
            //TODO - improvement : create DTO for errors (with fields errorMessage, id)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        this.studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // PATCH students/{idStudent}/enroll/{idCurs}

    @PatchMapping("/{idStudent}/enroll/{idCourse}")
    public ResponseEntity<Void> enrollStudent(@PathVariable("idStudent") int idStudent,
                                              @PathVariable("idCourse") int idCourse) {


        try {
            this.enrolmentService.enrol(idStudent, idCourse);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InexistentResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/unenrolled")
    public ResponseEntity<StudentListDTO> getUnenrolledStudents() {
        List<StudentEntity> unenrolledEntities = this.studentService.getAllUnenrolledStudents();
        //TODO - refactor - add method that converts a list of entities in a list of dtos
        List<StudentDTO> unenrolledStudents = new ArrayList<>();
        for (StudentEntity studentEntity : unenrolledEntities) {

            unenrolledStudents.add(StudentDTO.from(studentEntity));
        }

        StudentListDTO studentListDTO = new StudentListDTO();
        studentListDTO.setStudents(unenrolledStudents);

        return new ResponseEntity<>(studentListDTO, HttpStatus.OK);

    }
}
