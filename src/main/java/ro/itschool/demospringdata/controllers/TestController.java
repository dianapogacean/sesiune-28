package ro.itschool.demospringdata.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String all(){
        return "Permitted to all";
    }


    @GetMapping("user")
    public String user(){
        return "Permitted to user";
    }

    @GetMapping("admin")
    public String admin(){
        return "Permitted to admin";
    }
}
