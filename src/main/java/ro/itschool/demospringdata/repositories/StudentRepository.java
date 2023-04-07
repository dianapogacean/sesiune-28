package ro.itschool.demospringdata.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.demospringdata.entities.StudentEntity;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    List<StudentEntity> findByCoursesIsEmpty();

    List<StudentEntity> findByNameIgnoreCase(String name);

    List<StudentEntity> findByEmailIgnoreCase(String email);

    List<StudentEntity> findByStudentDetailsEntityDateEnrolledBefore(LocalDate date);
}
