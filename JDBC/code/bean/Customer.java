package JDBC_test.bean;
import java.sql.Blob;
import java.sql.Date;
public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;
    private Blob photo;
    public Customer() {
    }
    public Customer(int id, String name, String email, Date birth) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.birth = birth;
    }

    public Customer(int id, String name, String email, Date birth, Blob photo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", photo=" + photo +
                '}';
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
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
