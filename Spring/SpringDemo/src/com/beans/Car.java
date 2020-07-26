package com.beans;

/**
 * @ClassName Car
 * @Description Details
 * @Author Josen
 * @Create 18:21 18:21
 */
public class Car {
    private String brand; // 品牌
    private Double price; // 价格

    public Car() {
    }

    public Car(String brand, Double price) {
        this.brand = brand;
        this.price = price;
    }

    @Override
    public String toString() {

        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
