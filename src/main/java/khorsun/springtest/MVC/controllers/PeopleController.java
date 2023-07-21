package khorsun.springtest.MVC.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PeopleController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

}
