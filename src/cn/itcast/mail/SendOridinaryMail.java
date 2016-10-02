package cn.itcast.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送一封普通邮件
 * @author Jie.Yuan
 *
 */
public class SendOridinaryMail {

	public void testSend() throws Exception {
		
		//0. 邮件参数
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", "smtp");	// 指定协议
		prop.put("mail.smtp.host", "smtp.gmail.com");		// 主机   stmp.qq.com
		prop.put("mail.smtp.port", 465);					// 端口
		prop.put("mail.smtp.auth", "true");				// 用户密码认证
		prop.put("mail.debug", "true");					// 调试模式
		
		//1. 创建一个邮件的会话
//		Session session = Session.getDefaultInstance(prop);
		
		// Session管理与stmp的链接信息  
        Session session = Session.getDefaultInstance(prop, new Authenticator(){  
            protected PasswordAuthentication getPasswordAuthentication(){  
                return new PasswordAuthentication("memiracle@sohu.com","XXXX");  
            }  
        });  
		
		//2. 创建邮件体对象 (整封邮件对象)
		MimeMessage message = new MimeMessage(session);
		//3. 设置邮件体参数: 
		//3.1 标题
		message.setSubject("我的第一封邮件	");
		//3.2 邮件发送时间
		message.setSentDate(new Date());
		//3.3 发件人
		message.setSender(new InternetAddress("jianhua4lee@gmail.com"));
		//3.4 接收人
		message.setRecipient(RecipientType.TO, new InternetAddress("lijianhua_157@163.com"));
		//3.5内容
		message.setText("你好，已经发送成功！  正文....");  // 简单纯文本邮件
		// 邮件中含有超链接
		//message.setText("<a href='#'>百度</a>");
		message.setContent("<a href='www.baidu.com'>百度</a>", "text/html;charset=UTF-8");
		
		message.setSentDate(new Date());
		message.saveChanges();   // 保存邮件(可选)
		
		//4. 发送
//		Transport trans = session.getTransport();
//		trans.connect("zhangsan", "888");
		// 发送邮件
//		trans.sendMessage(message, message.getAllRecipients());
//		trans.close();
		Transport.send(message);  
	}
	public static void main(String[] args) {
		try {
			new SendOridinaryMail().testSend();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}














