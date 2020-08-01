package com.josen.beans;

/**
 * @ClassName Employee
 * @Description 员工信息
 * @Author Josen
 * @Create 9:03 9:03
 */
public class Employee {
    private int eid;
    private Double salary;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    // 员工所属部门信息
    private Department deps;

    public Employee() {
    }

    public Employee(int eid, Double salary, String firstName, String lastName, String phoneNumber, Department deps) {
        this.eid = eid;
        this.salary = salary;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.deps = deps;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", salary=" + salary +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", deps=" + deps +
                '}';
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Department getDeps() {
        return deps;
    }

    public void setDeps(Department deps) {
        this.deps = deps;
    }
}
