package ro.itschool.demospringdata.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.demospringdata.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
}
