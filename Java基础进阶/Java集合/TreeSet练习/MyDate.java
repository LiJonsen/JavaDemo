package com.atguigu.hashSetTest;

public class MyDate implements Comparable{
    private int year;
    private int month;
    private int day;

    public MyDate(){}
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof MyDate){
            MyDate o_date = (MyDate) o;
            if(this.year - o_date.year != 0){
                return this.year - o_date.year;
            }
            if(this.month - o_date.month != 0){
                return this.month - o_date.month;
            }

            return this.day - o_date.day;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
