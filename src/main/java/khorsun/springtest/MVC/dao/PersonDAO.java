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
    @Transactional(readOnly = true)
    public Person show(int id) {
         Session session = sessionFactory.getCurrentSession();
         Person person = session.get(Person.class, id);

         return  person;
    }
    public Person show(String email){
        return  jdbcTemplate.query("select * from hibernate_demo_db.public.person where email=?",new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> resultList = session.createQuery("select p from Person p", Person.class).getResultList();
        return resultList;
    }
    @Transactional
    public void create(Person person) {

        Session session = sessionFactory.getCurrentSession();

        session.save(person);

    }
    @Transactional
    public void edit(Person person,int id) {
        Session session = sessionFactory.getCurrentSession();
        Person personToUpdate = session.get(Person.class, id);
        personToUpdate.setName(person.getName());
        personToUpdate.setAge(person.getAge());
        personToUpdate.setEmail(person.getEmail());
    }
    @Transactional
    public void delete(int id)  {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class,id));


    }


}
