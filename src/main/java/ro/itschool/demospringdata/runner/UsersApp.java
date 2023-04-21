package ro.itschool.demospringdata.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import ro.itschool.demospringdata.entities.UserEntity;
import ro.itschool.demospringdata.repositories.UserRepository;

import java.time.LocalDate;
import java.util.Optional;


//@Component
public class UsersApp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        Optional<UserEntity> optionalUser = userRepository.findById(1);

        if (optionalUser.isPresent()){
           UserEntity dbUser = optionalUser.get();
           dbUser.setLastName("NEW LAST NAME");

           userRepository.save(dbUser);
        }

        UserEntity newUser = new UserEntity();
        newUser.setLastName("my new user");
        newUser.setFirstName("first name user new");
        newUser.setAge(20);
        newUser.setBirthDate(LocalDate.now());

        userRepository.save(newUser);

        //userRepository.deleteById(16);

        Iterable<UserEntity> users = userRepository.findAll();

        System.out.println(users);
    }
}
