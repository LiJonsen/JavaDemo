package JDBC_test.bean;
import java.sql.Date;

public class Order {
    private int orderID;
    private String orderName;
    private Date orderDate;
    public Order(){}
    public Order(int orderID, String orderName, Date orderDate) {
        this.orderID = orderID;
        this.orderName = orderName;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderName='" + orderName + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
