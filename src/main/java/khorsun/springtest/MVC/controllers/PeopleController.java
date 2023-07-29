package khorsun.springtest.MVC.controllers;



import khorsun.springtest.MVC.models.Person;
import khorsun.springtest.MVC.services.PersonService;
import khorsun.springtest.MVC.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;



@Controller
@RequestMapping("/people")
public class PeopleController {


    private final PersonValidator personValidator;
    private final PersonService personService;

    @Autowired
    public PeopleController(PersonValidator personValidator, PersonService personService) {

        this.personValidator = personValidator;
        this.personService = personService;
    }
    @GetMapping()
    public String index(Model model){
    model.addAttribute("people",personService.findAll());
    return "people/index";
    }
 @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
    model.addAttribute("person",personService.findOne(id));
    return "people/show";
    }
    @GetMapping("/new")
    public String create(@ModelAttribute("person")Person person){
    return "people/new";
    }
    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
    personValidator.validate(person,bindingResult);
    if (bindingResult.hasErrors())
        return "people/new";

            personService.create(person);

        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable("id") int id,Model model){
    model.addAttribute("person",personService.findOne(id));
    return "people/edit";
    }
    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("person")@Valid Person person,
                       BindingResult bindingResult,@PathVariable("id") int id){
        personValidator.validate(person,bindingResult);
    if (bindingResult.hasErrors())
        return "people/edit";
    personService.update(id, person);
    return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
    personService.delete(id);
    return "redirect:/people";
    }
}
