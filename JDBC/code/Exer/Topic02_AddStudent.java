package JDBC_test.Exer;

import JDBC_test.Utils.JDBC_Utils;

import java.util.Scanner;

/**
 * 题目2：操作数据库表 examstudent，并实现以下功能
 * 插入一个新的student 信息
 */
public class Topic02_AddStudent {
    public static void main(String[] args) {
        // 插入一个新的student 信息(录入新的学生信息)
        Topic02_AddStudent.addStudentInfo();
    }
    public static void addStudentInfo(){
        Student s = Topic02_AddStudent.addStudent();
        String sql = "insert into examstudent(Type,IDCard,ExamCard,StudentName,Location,Grade) values(?,?,?,?,?,?)";
        // 执行sql，添加学生信息
        boolean status = JDBC_Utils.updateSqlTable(sql, s.getType(), s.getIdCard(), s.getExamCard(), s.getStudentName(), s.getLocation(), s.getGrade());
        System.out.println(status?"信息录入成功!":"信息录入失败!");
    }

    public static Student addStudent(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入考试级别/类型（输入4/6）：");
        int type = scanner.nextInt();

        System.out.print("请输入身份证号码：");
        String idCard = scanner.next();

        System.out.print("请输入准考证号码：");
        String examCard = scanner.next();

        System.out.print("请输入学生姓名：");
        String studentName = scanner.next();

        System.out.print("请输入区域：");
        String location = scanner.next();

        System.out.print("请输入成绩：");
        int grade = scanner.nextInt();


        return new Student(type, idCard, examCard, studentName, location, grade);
    }
}
