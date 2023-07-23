package khorsun.springtest.MVC.dao;

import khorsun.springtest.MVC.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private List<Person> people;

    private static int PEOPLE_COUNT;

    {
        people=new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT,"Hanna",26,"hanna@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT,"Mikhail",25,"mikhail@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT,"Ksenia",9,"ksenia@gmail.com"));

    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId()==id).findAny().orElse(null);
    }

    public List<Person> index(){
        return people;
    }

    public void create(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);

    }

    public void edit(Person person,int id){
        Person toUpdate = show(id);
        toUpdate.setName(person.getName());
        toUpdate.setAge(person.getAge());
        toUpdate.setEmail(person.getEmail());
    }

    public void delete(int id){
        people.removeIf(person -> person.getId()==id);

    }


}
