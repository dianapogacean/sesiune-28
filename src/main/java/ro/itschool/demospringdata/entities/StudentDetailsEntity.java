package ro.itschool.demospringdata.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "student_details", schema = "university")
public class StudentDetailsEntity {

//    @OneToOne( mappedBy = "studentDetailsEntity", fetch = FetchType.EAGER)
//    private StudentEntity student;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "college", nullable = false, length = 50)
    private String college;

    @Column(name = "nb_courses_enrolled", nullable = false)
    private Integer nbCoursesEnrolled;

    @Column(name = "date_enrolled", nullable = false)
    private LocalDate dateEnrolled;



}
