package JDBC_test.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
    /**
     * 获取指定日期的毫秒时间戳
     * @param date 年月日日期
     * @return 毫秒时间戳
     */
    public static long getDateMillis(String date)  {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date db_date = df.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(db_date);
            return cal .getTimeInMillis();
        }catch (ParseException e){
            e.printStackTrace();
        }
        return 0;
    }
}
