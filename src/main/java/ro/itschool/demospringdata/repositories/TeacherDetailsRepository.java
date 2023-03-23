package ro.itschool.demospringdata.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.demospringdata.entities.TeacherDetailsEntity;

public interface TeacherDetailsRepository extends CrudRepository<TeacherDetailsEntity, Integer> {
}
