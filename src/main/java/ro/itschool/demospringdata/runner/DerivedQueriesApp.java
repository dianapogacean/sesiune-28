package ro.itschool.demospringdata.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import ro.itschool.demospringdata.entities.UserEntity;
import ro.itschool.demospringdata.repositories.UserRepository;

import java.util.List;

//@Component
public class DerivedQueriesApp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {


        List<UserEntity> byFirstName =  userRepository.findByFirstName("Jane");

        List<UserEntity> byAge = userRepository.findByAge(20);
        List<UserEntity> byFirstNameStarting = userRepository.findByFirstNameStartingWith("J");
        List<UserEntity> byFirstNameEnding = userRepository.findByFirstNameEndingWith("w");
        List<UserEntity> byFirstNameContaining = userRepository.findByFirstNameContaining("a");

        List<UserEntity> byAgeBetween = userRepository.findByAgeBetween(20,30);

        List<UserEntity> findByFirstNameOrder = userRepository.findByFirstNameOrderByAge("John");

        List<UserEntity> findFirst2 = userRepository.findFirst2ByFirstName("John");

//        System.out.println(byFirstName);
//        System.out.println(byAge);
//        System.out.println(byFirstNameStarting);
//        System.out.println(byFirstNameEnding);
//        System.out.println(byFirstNameContaining);

        System.out.println(findFirst2);
    }
}
