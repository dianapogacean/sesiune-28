package ro.itschool.demospringdata.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.itschool.demospringdata.entities.StudentEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Integer id;

    @JsonProperty("name_student")
    private String name;

    private String email;

    private String college;

    @JsonProperty("nb_c_enrolled")
    private Integer nbCoursesEnrolled;

    private LocalDate dateEnrolled;

    public static StudentDTO from(StudentEntity studentEntity){
        return StudentDTO.builder()
                .id(studentEntity.getId())
                .name(studentEntity.getName())
                .email(studentEntity.getEmail())
                .college(studentEntity.getStudentDetailsEntity().getCollege())
                .dateEnrolled(studentEntity.getStudentDetailsEntity().getDateEnrolled())
                .nbCoursesEnrolled(studentEntity.getStudentDetailsEntity().getNbCoursesEnrolled())
                .build();
    }
}
