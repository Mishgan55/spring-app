package khorsun.springtest.MVC.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/first")
public class PeopleController {
//    @GetMapping("/hello")
//    public String sayHello(HttpServletRequest request) {
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        System.out.println("Hello, my name is "+name+" "+ surname);
//        return "first/hello";
//    }
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name",required = false) String name,
                           @RequestParam(value = "surname",required = false) String surname,
                           Model model){
        model.addAttribute("message","Hello, my name is "+name+" "+ surname);
        return "first/hello";
    }

    @GetMapping("goodbye")
    public  String sayGoodBye(){
        return "first/goodBye";
    }

}
