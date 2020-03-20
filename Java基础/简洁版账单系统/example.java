/**
 * 简洁版 - 账单记录
 * 
 */
public class example {
    public static void main(String[] args) {
        boolean flag = true;
        String details = "收支\t账户金额\t收支金额\t说 明\n"; // 新增收入&支出记录
        int balance = 10000; // 默认账户金额=10000
        while (flag) {
            System.out.println("-------------简洁版账单系统--------------");
            System.out.println("-------------1. 收支明细");
            System.out.println("-------------2. 新增收入");
            System.out.println("-------------3. 新增支出");
            System.out.println("-------------4. 退出");
            String select_option = commonUtils.getMenuSelection();
            switch (select_option) {
                case "1":
                    System.out.println("-------------当前收支明细记录----------------");
                    System.out.println(details);
                    System.out.println("--------------------------------------------\n");
                    break;
                case "2":
                    System.out.println("本次收入金额：");
                    int price = commonUtils.enterMoney(balance,false);
                    System.out.println("本次收入说明：");
                    String description = commonUtils.enterDescription();
                    balance += price;
                    details += "\n收入\t" + balance + "\t\t" + price + "\t\t" + description ;
                    System.out.println("-------------新增一条收入记录----------------\n");
                    break;
                case "3":
                    System.out.println("本次支出金额：");
                    int price2 = commonUtils.enterMoney(balance,true);
                    System.out.println("本次支出说明：");
                    String description2 = commonUtils.enterDescription();
                    
                    balance -= price2;
                    details += "\n收入\t" + balance + "\t\t" + price2 + "\t\t" + description2 ;
                    System.out.println("-------------新增一条支出记录----------------\n");
                    break;
                case "4":
                    System.out.println("-------------确认是否退出(Y退出/N返回主菜单)----------------");
                    boolean status = commonUtils.confirmExit();
                    // 退出程序
                    if(status){
                        flag = false;
                    }
                    break;
                default:
                    commonUtils.printError();

            }
        }
    }
}