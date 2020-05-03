package com.atguigu.IO_Test;

import org.junit.Test;

import java.io.*;

/**
 * 对象流示例
 */
public class ObjectInputAndOutputStream {
    public static void main(String[] args) {
        ObjectInputAndOutputStream test = new ObjectInputAndOutputStream();
        test.testObjectOutputStream();
        test.testObjectInputStream();
    }

    /**
     * 序列化-读取对象流
     */
    public void testObjectInputStream(){
        ObjectInputStream ois = null;
        try{
            // 1. 创建节点流、对象流
            ois = new ObjectInputStream(new FileInputStream("src/com/atguigu/IO_Test/testJson.dat"));
            // 2. 读取对象流
            Person p = (Person) ois.readObject();
            System.out.println(p);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            // 3. 资源关闭
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 反序列化-保存对象流
     */
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;
        try{
            // 1. 创建节点流、对象流
            oos = new ObjectOutputStream(new FileOutputStream("src/com/atguigu/IO_Test/testJson.dat"));

            // 2. 保存对象流
            oos.writeObject(new Person("Josen",23));
            oos.flush();//刷新
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            // 3. 资源关闭
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
