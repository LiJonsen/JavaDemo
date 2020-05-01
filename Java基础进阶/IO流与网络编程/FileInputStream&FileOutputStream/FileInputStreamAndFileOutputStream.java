package com.atguigu.IO_Test;

import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 输入FileInputStream、输出FileOutputStream流实现图片复制粘贴功能
 */
public class FileInputStreamAndFileOutputStream {
    @Test
    public void testing(){
        // 1.1 实例化File类对象
        File file = new File("src/com/atguigu/IO_Test/images.jpg");
        File newFile = new File("src/com/atguigu/IO_Test/newImages.jpg");
        FileInputStream fr = null; //读取流实例对象
        FileOutputStream fw = null; //写入流实例对象
        List<Byte> bytes = new ArrayList<>(); // 读取到images.jpg的内容
        // 1.2 创建读取文件流
        try {
            fr = new FileInputStream(file);
            // 1.3 读取test.txt文件内容
            byte[] cbuf = new byte[5];
            int len;
            while((len=fr.read(cbuf)) != -1){
                for(int i=0;i<len;i++){
                    bytes.add(cbuf[i]);
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.1 创建写入文件流
        try {
            int size = bytes.size();
            if(size>0){
                fw = new FileOutputStream(newFile);
                // 2.2 在newImages.jpg写入读取到的字节
                byte[] data = new byte[size];
                int index = 0;
                Iterator<Byte> iterator = bytes.iterator();
                while (iterator.hasNext()){
                    data[index] = iterator.next();
                    index++;
                }
                System.out.println("复制图片完成!");
                fw.write(data);
            }
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
