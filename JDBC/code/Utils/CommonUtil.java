package JDBC_test.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
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
