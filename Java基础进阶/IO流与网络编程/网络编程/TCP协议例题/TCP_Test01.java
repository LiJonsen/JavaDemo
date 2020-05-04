package com.atguigu.InetTest;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * TCP例题一：
 * 客户端发送一条消息给服务端，服务端接收后打印到控制台；
 */
public class TCP_Test01 {
    // 客户端
    @Test
    public void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        try{
            // 获取本机地址
            InetAddress inet = InetAddress.getLocalHost();
            // 1. 创建TCP连接
            socket = new Socket(inet,9090);
            // 2. 创建Socket输出流，并向服务端发送消息
            outputStream = socket.getOutputStream();
            outputStream.write("Hello，这是一条来自客户端的问候消息".getBytes());
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            // 3. 资源关闭
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // 服务器端
    @Test
    public void server(){
        ServerSocket ss = null;
        Socket accept = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            // 1. 创建服务端TCP协议Socket连接
            ss = new ServerSocket(9090);
            accept = ss.accept();
            // 2. 接受客户端数据
            inputStream = accept.getInputStream();
            // 2.1 使用ByteArrayOutputStream类，避免中文出现乱码
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while((len=inputStream.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }

            System.out.println("服务端接收消息打印："+baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(ss!=null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(accept!=null){
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
