package ro.itschool.demospringdata.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ro.itschool.demospringdata.entities.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    //equality condition keywords
    List<UserEntity> findByAge(int age);
    List<UserEntity> findByFirstName(String firstName);

    List<UserEntity> findByActiveTrue();
    List<UserEntity> findByActiveFalse();

    //similarity condition keywords
    List<UserEntity> findByFirstNameStartingWith(String prefix);
    List<UserEntity> findByFirstNameEndingWith(String sufix);
    List<UserEntity> findByFirstNameContaining(String infix);


    //comparison condition keywords
    List<UserEntity> findByAgeLessThan(int age);
    List<UserEntity> findByAgeGreaterThan(int age);
    List<UserEntity> findByAgeGreaterThanEqual(int age);
    List<UserEntity> findByAgeBetween(int start, int stop);

    List<UserEntity> findByBirthDateAfter(LocalDate date);
    List<UserEntity> findByBirthDateBefore(LocalDate date);


    //multiple condition keywords (and, or etc)
    List<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);
    List<UserEntity> findByFirstNameOrAge(String firstName, int age);

    List<UserEntity> findByFirstNameOrderByAge(String firstName);

    //limiting keywords

    List<UserEntity> findFirst2ByFirstName(String firstName);

    @Query("select u from UserEntity u where u.active=true")
    List<UserEntity> findUsersThatAreActive();


    @Query("select u from UserEntity u where u.firstName=:firstname")
    List<UserEntity> findUsersByFirstName(@Param("firstname") String firstName);


}
