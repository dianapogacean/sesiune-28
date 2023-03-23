package ro.itschool.demospringdata.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.demospringdata.entities.CourseEntity;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {

}
