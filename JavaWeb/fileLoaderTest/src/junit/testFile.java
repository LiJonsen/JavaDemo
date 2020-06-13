package junit;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName testFile
 * @Description TODO
 * @Author Josen
 * @Date 2020/6/8 16:53
 * @Version 1.0
 **/
public class testFile {
    @Test
    public void testing(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
             fis = new FileInputStream("web/WEB-INF/static/test.pdf");
//             fos = new FileOutputStream("test_copy_pdf");

            System.out.println(fis.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
