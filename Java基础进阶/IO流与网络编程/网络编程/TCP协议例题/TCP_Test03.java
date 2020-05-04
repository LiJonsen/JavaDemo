package com.atguigu.InetTest;
import org.junit.Test;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP例题三：
 * 客户端发送文件给服务端，服务端将文件保存到本地、
 * 并且返回“发送成功”给客户端，然后关闭所有资源连接
 * 涉及数据阻塞；
 * （正常开发中，需要通过try-catch-finally处理异常和close）
 */
public class TCP_Test03 {
    @Test
    public void client() throws IOException {
        // 1. 创建客户端Socket连接
        Socket socket = new Socket(InetAddress.getLocalHost(),9090);
        OutputStream outputStream = socket.getOutputStream();
        // 2. 读取本地图片文件
        FileInputStream fileInputStream = new FileInputStream("src/com/atguigu/InetTest/sub.png");
        byte[] buffer = new byte[1024];
        int len;
        // 2.1 将读取图片所有字节缓存到baos缓冲区中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while((len=fileInputStream.read(buffer))!=-1){
            baos.write(buffer,0,len);
        }
        // 2.2 输出给ServerSocket
        outputStream.write(baos.toByteArray());
        // 2.3 关闭Socket输出流
        socket.shutdownOutput();
        // 2.4 接收ServerSocket的反馈
        InputStream inputStream = socket.getInputStream();
        byte[] buff = new byte[1024];
        int len2;
        String str = null;
        while ((len2=inputStream.read(buff))!=-1){
            String data = new String(buff,0,len2);
            str = str != null ? (str+=data) : data;
        }
        System.out.println("服务端反馈："+str);
        // 3. 资源关闭
        socket.close();
        outputStream.close();
        fileInputStream.close();
        baos.close();
    }

    @Test
    public void server() throws IOException {
        // 1. 创建服务端Socket
        ServerSocket ss = new ServerSocket(9090);
        Socket accept = ss.accept();
        // 2. 获取客户端数据
        InputStream inputStream = accept.getInputStream();
        byte[] buffer = new byte[1024];
        int len;
        // 2.1 将读取图片所有字节缓存到baos缓冲区中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while((len=inputStream.read(buffer))!=-1){
            baos.write(buffer,0,len);
        }
        // 2.2 向客户端发送消息，反馈图片已收到
        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("图片已收到，谢谢".getBytes());

        // 2.3 将图片保存到本地
        FileOutputStream fos = new FileOutputStream("src/com/atguigu/InetTest/subTest.png");
        fos.write(baos.toByteArray());
        System.out.println("收到来自："+accept.getInetAddress().getHostAddress()+"发送的图片！");
        // 3. 资源关闭
        ss.close();
        accept.close();
        fos.close();
        baos.close();
        inputStream.close();
    }
}
