package ro.itschool.demospringdata.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.demospringdata.entities.StudentDetailsEntity;

public interface StudentDetailsRepository extends CrudRepository<StudentDetailsEntity, Integer> {
}
