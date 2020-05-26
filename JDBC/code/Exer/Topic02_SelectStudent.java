package JDBC_test.Exer;

import JDBC_test.Utils.QueryForTables;

import java.util.List;
import java.util.Scanner;

/**
 * 题目2：操作数据库表 examstudent，并实现以下功能
 * 输入身份证号或准考证号可以查询到学生的基本信息。
 */
public class Topic02_SelectStudent {
    public static void main(String[] args) {

        String sql = "select Type type,IDCard idCard,ExamCard examCard,StudentName studentName,Location location,Grade grade " +
                "from examstudent where IDCard=? or ExamCard=?";
        String idCardNum = "412824195263214584";
        List<Student> result = QueryForTables.getResult(Student.class, sql, idCardNum,"");
        result.forEach(System.out::println);

        String examCardNul = "44010123";
        List<Student> res2 = QueryForTables.getResult(Student.class, sql, "",examCardNul);
        res2.forEach(System.out::println);
    }

}
