package com.beans;

/**
 * @ClassName Address
 * @Description Details
 * @Author Josen
 * @Create 2020/7/29 15:41
 */
public class Address {
    private String location;

    public Address() {
    }

    public Address(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Address{" +
                "location='" + location + '\'' +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
