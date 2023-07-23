package khorsun.springtest.MVC.dao;


import khorsun.springtest.MVC.models.Person;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {


    private static int PEOPLE_COUNT;

    private static Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection= DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/spring_app",
                    "postgres",
                    "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



//    public Person show(int id){
//        return people.stream().filter(person -> person.getId()==id).findAny().orElse(null);
//    }

    public List<Person> index() throws SQLException {
        List<Person> people=new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM spring_app.public.person");
        while (resultSet.next()){
            Person person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

            people.add(person);
        }

        return people;
    }

//    public void create(Person person){
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
//
//    }

//    public void edit(Person person,int id){
//        Person toUpdate = show(id);
//        toUpdate.setName(person.getName());
//        toUpdate.setAge(person.getAge());
//        toUpdate.setEmail(person.getEmail());
//    }
//
//    public void delete(int id){
//        people.removeIf(person -> person.getId()==id);
//
//    }


}
