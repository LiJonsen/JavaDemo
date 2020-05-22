package JDBC_test.bean;
import java.sql.Date;

public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;

    public Customer() {
    }
    public Customer(int id, String name, String email, Date birth) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.birth = birth;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
