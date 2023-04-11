package ro.itschool.demospringdata.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ro.itschool.demospringdata.entities.StudentEntity;
import ro.itschool.demospringdata.validators.NoDigits;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class StudentDTO {

    private Integer id;

    @NotEmpty
    @NoDigits(message = "Name cannot contain digits.")
    private String name;

    @Email
    @NotNull
    private String email;

    @NotEmpty
    private String college;

    @Min(1)
    @Max(10)
    private Integer nbCoursesEnrolled;

    @PastOrPresent
    private LocalDate dateEnrolled;

    public static StudentDTO from(StudentEntity studentEntity) {

        return StudentDTO.builder()
                .id(studentEntity.getId())
                .name(studentEntity.getName())
                .email(studentEntity.getEmail())
                .college(studentEntity.getStudentDetailsEntity().getCollege())
                .dateEnrolled(studentEntity.getStudentDetailsEntity().getDateEnrolled())
                .nbCoursesEnrolled(studentEntity.getStudentDetailsEntity().getNbCoursesEnrolled())
                .build();
    }

    public static List<StudentDTO> from(List<StudentEntity> studentEntities) {
        List<StudentDTO> result = new ArrayList<>();

        for (StudentEntity studentEntity : studentEntities) {
            result.add(StudentDTO.from(studentEntity));
        }

        return result;
    }
}
