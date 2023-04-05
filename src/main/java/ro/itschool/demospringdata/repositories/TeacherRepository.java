package ro.itschool.demospringdata.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ro.itschool.demospringdata.entities.TeachersEntity;

import java.util.List;

public interface TeacherRepository extends CrudRepository<TeachersEntity, Integer> {

    /*
    name, count
    java, 3
    react, 5
    php, 10
     */
    @Query("SELECT new ro.itschool.demospringdata.repositories.CourseStudentCount(c.name, count(s)) from CourseEntity c JOIN c.students s WHERE c.teacher.id=:idTeacher GROUP BY c.name")
    List<CourseStudentCount> countStudentsInCourses(@Param("idTeacher") int idTeacher);

}
