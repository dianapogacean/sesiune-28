package ro.itschool.demospringdata.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "students", schema = "university")
@NamedQueries(
        @NamedQuery(name = "byNameEmail", query = "select s from StudentEntity s where s.name=:name and s.email=:email")
)
public class StudentEntity {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "students_courses",
            joinColumns = {@JoinColumn(name = "id_student")}, //owning side
            inverseJoinColumns = {@JoinColumn(name = "id_course")} //non-owning side
    )
    private List<CourseEntity> courses = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER) //READD CASCADE ALL
    @JoinColumn(name = "id_details", referencedColumnName = "id")
    private StudentDetailsEntity studentDetailsEntity;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Basic
    @Column(name = "email", nullable = false, length = 40)
    private String email;

}
