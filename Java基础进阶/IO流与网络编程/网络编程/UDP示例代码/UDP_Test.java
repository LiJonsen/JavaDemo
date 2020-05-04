package com.atguigu.InetTest;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * UDP协议DatagramSocket和DatagramPacket类的使用
 * 创建一个UDP协议Socket,在客户端向服务端发送一条消息；
 */
public class UDP_Test {
    // 发送者
    @Test
    public void sender() throws IOException {
        // 1. 创建UDP连接
        DatagramSocket ds = new DatagramSocket();
        // 2. 指定连接服务端的地址
        byte[] data = "UDP发送者的消息内容".getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        // 2.1 将要发送的数据打包
        DatagramPacket datagramPacket = new DatagramPacket(data, 0, data.length, inet, 9090);

        // 2.2 发送数据
        ds.send(datagramPacket);
        // 3. 资源关闭
        ds.close();
    }
    // 接受者
    @Test
    public void receiver() throws IOException {
        // 1. 创建服务端UDP Socket
        DatagramSocket ds = new DatagramSocket(9090);
        byte[] buffer = new byte[1024];
        // 2.1 接收客户端发送的数据
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
        ds.receive(packet);
        // 2.2 将数据转换成String
        String str = new String(packet.getData(),0,packet.getLength());
        System.out.println(str);
        // 3. 资源关闭
        ds.close();
    }

}
