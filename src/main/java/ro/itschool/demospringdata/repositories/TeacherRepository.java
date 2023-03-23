package ro.itschool.demospringdata.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.demospringdata.entities.TeachersEntity;

public interface TeacherRepository extends CrudRepository<TeachersEntity, Integer> {
}
