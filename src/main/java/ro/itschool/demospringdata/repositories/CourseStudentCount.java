package ro.itschool.demospringdata.repositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CourseStudentCount {

    private String name;
    private long count;
}
