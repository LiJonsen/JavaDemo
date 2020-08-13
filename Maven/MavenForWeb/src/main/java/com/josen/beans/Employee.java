package com.josen.beans;

/**
 * @ClassName Employee
 * @Description Details
 * @Author Josen
 * @Create 2020/8/3 10:14
 */
public class Employee {
    private String name;
    private String email;
    private int empId;

    public Employee() {
    }

    public Employee(String name, String email, int empId) {
        this.name = name;
        this.email = email;
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", empId=" + empId +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
