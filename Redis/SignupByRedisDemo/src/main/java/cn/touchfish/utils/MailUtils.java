package cn.touchfish.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName MailUtils
 * @Description 邮件工具类
 * @Author Josen
 * @Create 2020/8/12 12:08
 */
public class MailUtils {
    private String email;// 收件人邮箱
    private String url;// 平台验证码
    private String name; // 用户名称
    // 发件人电子邮箱地址
    // qq邮箱
    private static final String QQ_HOST = "smtp.qq.com";
    private static final String FROM_QQ_MAIL = "jiaosenli@163.com";
    // 腾讯企业邮箱
    private static final String TX_COMPANY_HOST = "smtp.exmail.qq.com";
    private static final String FROM_TX_COMPANY_MAIL = "josen@touchfish.cn";

    // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)
    // 163邮箱
    private static final String HOST_163 = "smtp.163.com";
    private static final String FROM_163_MAIL = "jiaosenli@163.com";

    // 邮件平台授权码
    private static final String QQ_AUTH_CODE = "aoyrvwpgrafehhhg";
    private static final String TX_COMPANY_AUTH_CODE = "As,.82560423";
    private static final String AUTH_CODE_163 = "ZXUAOTDWPXOXKCDU";
    public MailUtils(){

    }

    public MailUtils(String email, String url, String name) {
        this.email = email;
        this.url = url;
        this.name = name;
    }

    public void sendMail() {
        // 1.创建连接对象javax.mail.Session
        // 2.创建邮件对象 javax.mail.Message
        // 3.发送一封激活邮件
        String from = FROM_TX_COMPANY_MAIL;
        String host = TX_COMPANY_HOST;

        //Properties properties = System.getProperties();// 获取系统属性
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");//协议
        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");// 打开认证
        properties.setProperty("mail.smtp.port", "465"); // 使用465端口

        //使用SSL，企业邮箱必需！
        //开启安全协议，如果出错显示类不存在，就更新mail的jar包
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        properties.put("mail.smtp.ssl.enable", "true");// 打开SSL
        properties.put("mail.smtp.ssl.socketFactory", sf);

        try {
            //QQ邮箱需要下面这段代码，163邮箱不需要
//            MailSSLSocketFactory sf = new MailSSLSocketFactory();
//            sf.setTrustAllHosts(true);
//            properties.put("mail.smtp.ssl.enable", "true");
//            properties.put("mail.smtp.ssl.socketFactory", sf);


            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_TX_COMPANY_MAIL, TX_COMPANY_AUTH_CODE); // 发件人邮箱账号、授权码
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from,"TouchFish"));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("<Testing> - 账号激活");
            // 2.4设置邮件内容

            String content = "<h2>Hello "+name+"!</h2></br></br>" +
                    "<p>注册成功~</p></br></br>" +
                    "<p><a href='"+url+"'>点我激活</a></p>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
            System.out.println("邮件成功发送!");
        } catch (Exception e) {
            System.out.println("邮件发送失败!");
            e.printStackTrace();
        }
    }
}
