package ro.itschool.demospringdata.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.itschool.demospringdata.entities.User;
import ro.itschool.demospringdata.repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@Component
public class UsersApp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        Optional<User> optionalUser = userRepository.findById(1);

        if (optionalUser.isPresent()){
           User dbUser = optionalUser.get();
           dbUser.setLastName("NEW LAST NAME");

           userRepository.save(dbUser);
        }

        User newUser = new User();
        newUser.setLastName("my new user");
        newUser.setFirstName("first name user new");
        newUser.setAge(20);
        newUser.setBirthDate(LocalDate.now());

        userRepository.save(newUser);

        //userRepository.deleteById(16);

        Iterable<User> users = userRepository.findAll();

        System.out.println(users);
    }
}
