package ro.itschool.demospringdata.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "teacher_details", schema = "university")
public class TeacherDetailsEntity {

    @ToString.Exclude
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "subject", nullable = false, length = 40)
    private String subject;

    @Column(name = "nb_classes", nullable = false)
    private Integer nbClasses;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private TeachersEntity teacher;

}
