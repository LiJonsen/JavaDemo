package com.josen.getting_start.bean;

/**
 * @ClassName Location
 * @Description 地址-Bean（myemployees数据库locations表）
 * @Author Josen
 * @Create 20:55 20:55
 */
public class Location {
    // 地址id
    private int locationId;
    // 城市
    private String city;
    // 国家id
    private String countryId;

    public Location() {
    }

    public Location(int locationId, String city, String countryId) {
        this.locationId = locationId;
        this.city = city;
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", city='" + city + '\'' +
                ", countryId='" + countryId + '\'' +
                '}';
    }

    public int getlocationId() {
        return locationId;
    }

    public void setlocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getcountryId() {
        return countryId;
    }

    public void setcountryId(String countryId) {
        this.countryId = countryId;
    }
}
