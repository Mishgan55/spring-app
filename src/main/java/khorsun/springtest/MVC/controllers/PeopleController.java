package khorsun.springtest.MVC.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/first")
public class PeopleController {
    @GetMapping("/hello")
    public String sayHello(){
        return "first/hello";
    }
    @GetMapping("goodbye")
    public  String sayGoodBye(){
        return "first/goodBye";
    }

}
