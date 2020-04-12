package com.atguigu.common_class;
public class Goods implements Comparable{
    private String name;
    private double price;
    public Goods(){}
    public Goods(String name,double price){
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    // Comparable自然排序
    @Override
    public int compareTo(Object o) {
        if(o instanceof  Goods){
            Goods goods = (Goods)o;
            if(this.price==goods.price){
               //2. name英文字母从小到大排序
               return this.name.compareTo(goods.name);
            }else{
                //1. 价格从低到高排序
                return Double.compare(this.price,goods.price);
            }
        }
        throw new RuntimeException("传入的参数类型不一致");
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
