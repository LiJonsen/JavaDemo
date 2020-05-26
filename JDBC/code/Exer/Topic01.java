package JDBC_test.Exer;

import JDBC_test.Utils.CommonUtil;
import JDBC_test.Utils.JDBC_Utils;
import org.junit.Test;

import java.util.Scanner;
import java.sql.Date;

/**
 * 题目1：从控制台向数据库的表customers中插入一条数据，表结构如下
 */
public class Topic01 {
    public static void main(String[] args) {
        // 1. 获取控制台输入的信息
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入名字：");
        String name = scanner.next();
        System.out.print("请输入邮箱：");
        String email = scanner.next();
        System.out.print("请输入生日日期（如：1997-01-12）：");
        String birth = scanner.next();

        // 将日期转为java.sql.Date格式
        long dateMillis = CommonUtil.getDateMillis(birth);
        Date date = new Date(dateMillis);

        // 2. 将信息插入到customers数据表中
        String update_sql = "insert into customers(name,email,birth) values(?,?,?)";
        boolean status = JDBC_Utils.updateSqlTable(update_sql, name, email, date);

        System.out.println(status?"新增成功":"新增失败");
    }
}
