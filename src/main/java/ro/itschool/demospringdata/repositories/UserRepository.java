package ro.itschool.demospringdata.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ro.itschool.demospringdata.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    //equality condition keywords
    List<User> findByAge(int age);
    List<User> findByFirstName(String firstName);

    List<User> findByActiveTrue();
    List<User> findByActiveFalse();

    //similarity condition keywords
    List<User> findByFirstNameStartingWith(String prefix);
    List<User> findByFirstNameEndingWith(String sufix);
    List<User> findByFirstNameContaining(String infix);


    //comparison condition keywords
    List<User> findByAgeLessThan(int age);
    List<User> findByAgeGreaterThan(int age);
    List<User> findByAgeGreaterThanEqual(int age);
    List<User> findByAgeBetween(int start, int stop);

    List<User> findByBirthDateAfter(LocalDate date);
    List<User> findByBirthDateBefore(LocalDate date);


    //multiple condition keywords (and, or etc)
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    List<User> findByFirstNameOrAge(String firstName, int age);

    List<User> findByFirstNameOrderByAge(String firstName);

    //limiting keywords

    List<User> findFirst2ByFirstName(String firstName);

    @Query("select u from User u where u.active=true")
    List<User> findUsersThatAreActive();


    @Query("select u from User u where u.firstName=:firstname")
    List<User> findUsersByFirstName(@Param("firstname") String firstName);


}
