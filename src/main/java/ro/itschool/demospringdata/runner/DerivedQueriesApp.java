package ro.itschool.demospringdata.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.itschool.demospringdata.entities.User;
import ro.itschool.demospringdata.repositories.UserRepository;

import java.util.List;

//@Component
public class DerivedQueriesApp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {


        List<User> byFirstName =  userRepository.findByFirstName("Jane");

        List<User> byAge = userRepository.findByAge(20);
        List<User> byFirstNameStarting = userRepository.findByFirstNameStartingWith("J");
        List<User> byFirstNameEnding = userRepository.findByFirstNameEndingWith("w");
        List<User> byFirstNameContaining = userRepository.findByFirstNameContaining("a");

        List<User> byAgeBetween = userRepository.findByAgeBetween(20,30);

        List<User> findByFirstNameOrder = userRepository.findByFirstNameOrderByAge("John");

        List<User> findFirst2 = userRepository.findFirst2ByFirstName("John");

//        System.out.println(byFirstName);
//        System.out.println(byAge);
//        System.out.println(byFirstNameStarting);
//        System.out.println(byFirstNameEnding);
//        System.out.println(byFirstNameContaining);

        System.out.println(findFirst2);
    }
}
