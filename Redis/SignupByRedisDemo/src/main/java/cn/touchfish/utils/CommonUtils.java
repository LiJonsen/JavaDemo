package cn.touchfish.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName CommonUtils
 * @Description IO流工具类
 * @Author Josen
 * @Create 2020/8/15 19:30
 */
public final class CommonUtils {
    public static void closeStream(InputStream is){
        if(is!=null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   public static String getRedisActiveName(String name){
        return "Active_"+name;
   }
   public static String getRedisTokenKey(String name){
        return "Token_"+name;
   }
}
