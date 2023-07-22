package khorsun.springtest.MVC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String calculate(@RequestParam(value = "firstNum",required = false) int firstNumber,
                            @RequestParam(value = "secondNum",required = false) int secondNumber,
                            @RequestParam(value = "action",required = false) String action,
                            Model model){
        double result;
        switch (action){
            case "multiplication":
                result=firstNumber*secondNumber;
            break;
            case "addition" :
                result=firstNumber+secondNumber;
            break;
            case "subtraction" :
                result=firstNumber-secondNumber;
            break;
            case "division" :
                result=firstNumber/(double)secondNumber;
            break;
            default:
                result=0;
                break;
        }
        model.addAttribute("solution",result);

        return "first/solution";

    }
}
