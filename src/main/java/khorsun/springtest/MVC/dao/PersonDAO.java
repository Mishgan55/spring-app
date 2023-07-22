package khorsun.springtest.MVC.dao;

import khorsun.springtest.MVC.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int COUNT_ID ;
    private List<Person> people;

    {
        people=new ArrayList<>();

        people.add(new Person(++COUNT_ID,"Mikhail"));
        people.add(new Person(++COUNT_ID,"Hanna"));
        people.add(new Person(++COUNT_ID,"Ksenia"));
        
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){

        return people.stream().filter(person -> person.getId()==id).findAny().orElse(null);
    }
    public void createPerson(Person person){
        person.setId(++COUNT_ID);
        people.add(person);

    }
}
