package khorsun.springtest.MVC.dao;


import khorsun.springtest.MVC.models.Person;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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



    public Person show(int id) throws SQLException {
        Person person = new Person();
        PreparedStatement preparedStatement=
                connection.prepareStatement("SELECT * FROM spring_app.public.person WHERE ID=?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));

        return  person;

    }

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

    public void create(Person person) throws SQLException {

        PreparedStatement preparedStatement= connection.prepareStatement(
                "INSERT INTO spring_app.public.person VALUES (1,?,?,?)");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2,person.getAge());
        preparedStatement.setString(3, person.getEmail());

        preparedStatement.executeUpdate();


    }

    public void edit(Person person,int id) throws SQLException {

        PreparedStatement preparedStatement=
                connection.prepareStatement("UPDATE spring_app.public.person SET name=?, age=?, email=? WHERE id=?");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2,person.getAge());
        preparedStatement.setString(3, person.getEmail());
        preparedStatement.setInt(4,id);

        preparedStatement.executeUpdate();


    }

    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement=
                connection.prepareStatement("DELETE FROM spring_app.public.person WHERE id=?");

        preparedStatement.setInt(1,id);

        preparedStatement.executeUpdate();

    }


}
