package com.beans;

/**
 * @ClassName User
 * @Description Details
 * @Author Josen
 * @Create 2020/7/29 15:41
 */
public class User {
    private String name;
    private Integer age;
    private String email;
    private String gender;
    private Address address;

    public User() {
    }

    public User(String name, Integer age, String email, String gender, Address address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", address=" + address +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
