package ro.itschool.demospringdata.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentListDTO {

    private List<StudentDTO> students;
}
