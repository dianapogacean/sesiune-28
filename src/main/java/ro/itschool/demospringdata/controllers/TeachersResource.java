package ro.itschool.demospringdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.demospringdata.exceptions.InexistentResourceException;
import ro.itschool.demospringdata.service.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeachersResource {

    @Autowired
    private TeacherService teacherService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){

        try {
            teacherService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InexistentResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
