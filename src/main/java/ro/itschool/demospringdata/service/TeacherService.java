package ro.itschool.demospringdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.itschool.demospringdata.entities.TeacherDetailsEntity;
import ro.itschool.demospringdata.entities.TeachersEntity;
import ro.itschool.demospringdata.exceptions.InexistentResourceException;
import ro.itschool.demospringdata.repositories.TeacherDetailsRepository;
import ro.itschool.demospringdata.repositories.TeacherRepository;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherDetailsRepository teacherDetailsRepository;


    @Transactional
    public void add(String name, String email, String college, String address, int nrClasses, String subject){

        TeachersEntity teacher = new TeachersEntity();

        teacher.setName(name);
        teacher.setEmail(email);

        TeacherDetailsEntity teacherDetails = new TeacherDetailsEntity();

        teacherDetails.setSubject(subject);
        teacherDetails.setCollege(college);
        teacherDetails.setAddress(address);
        teacherDetails.setNbClasses(nrClasses);

        TeachersEntity savedTeacher = teacherRepository.save(teacher);
        teacherDetails.setTeacher(savedTeacher);

        savedTeacher.setTeacherDetails(teacherDetails);

        teacherRepository.save(savedTeacher);
    }

    public void delete(int id) throws InexistentResourceException {

        Optional<TeachersEntity> foundTeacher =  this.teacherRepository.findById(id);

        if (!foundTeacher.isPresent()){
            throw new InexistentResourceException("This teacher doesn't exist", id);
        } else {
            teacherRepository.delete(foundTeacher.get());
        }
    }

}
