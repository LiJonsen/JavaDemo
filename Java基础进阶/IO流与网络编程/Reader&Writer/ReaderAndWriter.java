package com.atguigu.IO_Test;
import org.junit.Test;
import java.io.*;
public class ReaderAndWriter {
    /**
     * 实现读取test.txt文件内容，并且创建一个新的newTest.txt内容与test.txt一致的文件；（复制粘贴）
     */
    @Test
    public void test(){
        // 1.1 实例化File类对象
        File file = new File("src/com/atguigu/IO_Test/test.txt");
        File newFile = new File("src/com/atguigu/IO_Test/newTest.txt");
        FileReader fr = null; //读取流实例对象
        FileWriter fw = null; //写入流实例对象
        String str = null; // 读取到test.txt的内容
        // 1.2 创建读取文件流
        try {
            fr = new FileReader(file);
            // 1.3 读取test.txt文件内容
            char[] cbuf = new char[5];
            int len;
            while((len=fr.read(cbuf)) != -1){
                if(str==null)
                    str = new String(cbuf,0,len);
                else
                    str += new String(cbuf,0,len);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.1 创建写入文件流
        try {
            fw = new FileWriter(newFile);
            // 2.2 在newTest.txt写入读取到的内容
            if(str != null)
                fw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3.1 读取文件流资源关闭
        if(fr != null){
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 3.2 写入文件流资源关闭
        if(fw != null){
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
