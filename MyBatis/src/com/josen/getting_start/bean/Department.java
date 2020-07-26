package com.josen.getting_start.bean;

/**
 * @ClassName Department
 * @Description 部门-Bean（myemployees数据库departments表）
 * @Author Josen
 * @Create 20:54 20:54
 */
public class Department {
    // 部门id
    private int did;
    // 部门名称
    private String departmentName;
    // 部门地址信息
    private Location location;
    public Department() {
    }

    public Department(int did, String departmentName, Location location) {
        this.did = did;
        this.departmentName = departmentName;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "did=" + did +
                ", departmentName='" + departmentName + '\'' +
                ", location=" + location +
                '}';
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
