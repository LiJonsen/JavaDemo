import java.util.Scanner;

public class commonUtils {
    private static Scanner scan = new Scanner(System.in, "UTF-8");

    public static void main(String[] args) {
    }

    // 获取菜单选项
    public static String getMenuSelection() {
        String select_option;
        while (true) {
            select_option = scan.next();
            if (select_option.equals("1") || select_option.equals("2") || select_option.equals("3")
                    || select_option.equals("4")) {
                break;
            } else {
                printError();
            }
        }
        return select_option;
    }

    // 确认退出程序
    public static boolean confirmExit() {
        String str = scan.next();
        boolean status = false;
        str = str.toUpperCase();
        if (str.equals("Y")) {
            status = true;
        }
        return status;
    }

    // 打印错误
    public static void printError() {
        System.out.println("-------------选择错误，请重新输入----------------\n");
    }

    // 用于收入和支出金额的输入，金额不超过10000，并将其金额作为返回值
    // 支出：flag=true 收入：flag=flase
    public static int enterMoney(int balance, boolean flag) {
        int money;
        String error_tip = flag ? "账户余额不足" : "收入金额必须大于0";;

        while (true) {
            try {
                money = scan.nextInt();
                // 支出大于账户余额
                if (flag && money > balance) {
                    System.out.println(error_tip);
                }
                // 收入不小于等于0
                if (!flag && money <= 0) {
                    System.out.println(error_tip);
                }
                // 返回金额
                return money;
            } catch (Exception e) {
                // TODO: handle exception\
                System.out.println("输入的金额无法正常录入，请重新输入");
            }
        }
    }

    // 输入收入&支出说明，并将值返回
    public static String enterDescription() {
        String description;
        while (true) {
            try {
                description = scan.next();
                int len = description.length();
                System.out.println("description:" + description);

                if (len > 0 && len <= 8) {
                    return description;
                }
                System.out.println("说明只能在1-8个文字");
            } catch (Exception e) {
                // TODO: handle exception\
                System.out.println("输入的说明无法正常录入，请重新输入");
            }
        }

    }
}