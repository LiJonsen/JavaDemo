package com.beans;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RichMan
 * @Description Details
 * @Author Josen
 * @Create 8:39 8:39
 */
public class RichMan {
    private String name;
    private List<Car> cars;
    private Map<Integer,Car> carsMap;
    public RichMan() {
    }

    public RichMan(String name, List<Car> cars, Map<Integer, Car> carsMap) {
        this.name = name;
        this.cars = cars;
        this.carsMap = carsMap;
    }

    @Override
    public String toString() {
        return "RichMan{" +
                "name='" + name + '\'' +
                ", cars=" + cars +
                ", carsMap=" + carsMap +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Map<Integer, Car> getCarsMap() {
        return carsMap;
    }

    public void setCarsMap(Map<Integer, Car> carsMap) {
        this.carsMap = carsMap;
    }
}
