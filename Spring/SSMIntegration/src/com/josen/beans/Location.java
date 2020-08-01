package com.josen.beans;

/**
 * @ClassName Location
 * @Description 住址定位信息
 * @Author Josen
 * @Create 2020/8/1 19:13
 */
public class Location {
    private int locationId;
    private String streetAddress;
    private String city;

    public Location() {
    }
    public Location(int locationId, String streetAddress, String city) {
        this.locationId = locationId;
        this.streetAddress = streetAddress;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
