package khorsun.springtest.MVC.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int personId;
    @NotEmpty(message = "name should not be empty")
    @Size(max = 30, message = "name should be between 0 and 30 characters")
    private String name;
    @Min( value = 0, message = "Age should be grater then 0")
    private int age;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Please write down valid email")
    private String email;

    public Person() {
    }

    public Person(int id, String name, int age, String email) {
        this.personId = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
