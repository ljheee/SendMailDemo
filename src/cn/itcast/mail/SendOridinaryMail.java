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
 * ����һ����ͨ�ʼ�
 * @author Jie.Yuan
 *
 */
public class SendOridinaryMail {

	public void testSend() throws Exception {
		
		//0. �ʼ�����
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", "smtp");	// ָ��Э��
		prop.put("mail.smtp.host", "smtp.gmail.com");		// ����   stmp.qq.com
		prop.put("mail.smtp.port", 465);					// �˿�
		prop.put("mail.smtp.auth", "true");				// �û�������֤
		prop.put("mail.debug", "true");					// ����ģʽ
		
		//1. ����һ���ʼ��ĻỰ
//		Session session = Session.getDefaultInstance(prop);
		
		// Session������stmp��������Ϣ  
        Session session = Session.getDefaultInstance(prop, new Authenticator(){  
            protected PasswordAuthentication getPasswordAuthentication(){  
                return new PasswordAuthentication("memiracle@sohu.com","XXXX");  
            }  
        });  
		
		//2. �����ʼ������ (�����ʼ�����)
		MimeMessage message = new MimeMessage(session);
		//3. �����ʼ������: 
		//3.1 ����
		message.setSubject("�ҵĵ�һ���ʼ�	");
		//3.2 �ʼ�����ʱ��
		message.setSentDate(new Date());
		//3.3 ������
		message.setSender(new InternetAddress("jianhua4lee@gmail.com"));
		//3.4 ������
		message.setRecipient(RecipientType.TO, new InternetAddress("lijianhua_157@163.com"));
		//3.5����
		message.setText("��ã��Ѿ����ͳɹ���  ����....");  // �򵥴��ı��ʼ�
		// �ʼ��к��г�����
		//message.setText("<a href='#'>�ٶ�</a>");
		message.setContent("<a href='www.baidu.com'>�ٶ�</a>", "text/html;charset=UTF-8");
		
		message.setSentDate(new Date());
		message.saveChanges();   // �����ʼ�(��ѡ)
		
		//4. ����
//		Transport trans = session.getTransport();
//		trans.connect("zhangsan", "888");
		// �����ʼ�
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














