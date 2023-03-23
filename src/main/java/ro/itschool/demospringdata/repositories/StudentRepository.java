package ro.itschool.demospringdata.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.demospringdata.entities.StudentDetailsEntity;
import ro.itschool.demospringdata.entities.StudentEntity;

import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    List<StudentEntity> findByCoursesIsEmpty();
}
