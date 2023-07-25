package khorsun.springtest.MVC.dao;


import khorsun.springtest.MVC.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
@Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person show(int id) {

        return  jdbcTemplate.query("SELECT * FROM spring_app.public.person WHERE person_id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public Person show(String email){
        return  jdbcTemplate.query("select * from spring_app.public.person where email=?",new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public List<Person> index() {

        return jdbcTemplate.query("SELECT * FROM spring_app.public.person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public void create(Person person) {

    jdbcTemplate.update("insert into spring_app.public.person(name, age, email) values (?,?,?)",
            person.getName(),person.getAge(),person.getEmail());

    }

    public void edit(Person person,int id) {
    jdbcTemplate.update("update spring_app.public.person set name=?,age=?,email=? where person_id=?",
            person.getName(),person.getAge(),person.getEmail(),id);
    }

    public void delete(int id)  {

    jdbcTemplate.update("delete from spring_app.public.person where person_id=?",id);


    }


}
