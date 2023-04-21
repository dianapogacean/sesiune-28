package ro.itschool.demospringdata.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ro.itschool.demospringdata.entities.UserEntity;
import ro.itschool.demospringdata.repositories.UserRepository;

import java.util.List;

//@Component
public class PagingAndSortingUserApp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {


        PageRequest pageRequest = PageRequest.of(1,5);

        Page<UserEntity> usersPage =  userRepository.findAll(pageRequest);

        List<UserEntity> usersList = usersPage.getContent();
        int pageNumber = usersPage.getNumber();
        int totalPages = usersPage.getTotalPages();

//        System.out.println(usersList);
//        System.out.println("page number: "+pageNumber);
//        System.out.println("total pages: "+totalPages);


        Sort byFirstName = Sort.by("firstName");
        Sort byAge = Sort.by("age");
        Sort grouped = byFirstName.and(byAge);
        Iterable<UserEntity> sortedUsers = userRepository.findAll(byFirstName);

        PageRequest pageRequest1 = PageRequest.of(0,3, byFirstName);
        Page<UserEntity> usersPageSorted =  userRepository.findAll(pageRequest1);
        List<UserEntity> usersSortedList = usersPageSorted.getContent();
        int sortedPageNumber = usersPageSorted.getNumber();
        int sortedTotalPages = usersPageSorted.getTotalPages();

        System.out.println(usersSortedList);
        System.out.println("page number: " + sortedPageNumber);
        System.out.println("total pages: " + sortedTotalPages);

       // System.out.println(sortedUsers);

    }
}
