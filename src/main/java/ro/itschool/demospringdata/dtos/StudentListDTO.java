package ro.itschool.demospringdata.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudentListDTO {

    private List<StudentDTO> students;
}
