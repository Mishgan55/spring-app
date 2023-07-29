package khorsun.springtest.MVC.dao;


import khorsun.springtest.MVC.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SessionFactory sessionFactory;

@Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate,  SessionFactory sessionFactory1) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionFactory = sessionFactory1;
}

    public Person show(int id) {

        return  jdbcTemplate.query("SELECT * FROM hibernate_demo_db.public.person WHERE person_id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public Person show(String email){
        return  jdbcTemplate.query("select * from hibernate_demo_db.public.person where email=?",new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> resultList = session.createQuery("select p from Person p", Person.class).getResultList();
        System.out.println(resultList);


        return resultList;
    }

    public void create(Person person) {

    jdbcTemplate.update("insert into hibernate_demo_db.public.person(name, age, email) values (?,?,?)",
            person.getName(),person.getAge(),person.getEmail());

    }

    public void edit(Person person,int id) {
    jdbcTemplate.update("update hibernate_demo_db.public.person set name=?,age=?,email=? where person_id=?",
            person.getName(),person.getAge(),person.getEmail(),id);
    }

    public void delete(int id)  {

    jdbcTemplate.update("delete from hibernate_demo_db.public.person where person_id=?",id);


    }


}
