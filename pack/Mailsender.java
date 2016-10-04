package pack;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailsender {

	public static void main(String[] args) {
		String password = "gusdms12!";
		String toMail = "mm2004ee@gmail.com";
		String fromName = "홍길동";
		String subject = "제목";
		//String content = "안녕하세요";
		String content = "<html><body>html 형식으로 전달<br><img src='http://dbscthumb.phinf.naver.net/2881_000_24/20160720010019515_QJ17UUKMC.jpg/20160719_0620_GA.jpg?type=w646_fst;;92;true' /></body></html>";
		
		Mailsender ms = new Mailsender();
		ms.sendMail(toMail, password, fromName, subject, content);

	}

	public void sendMail(String toMail,String password,String fromName,String subject,String content){
		try {
			//서버에대한설정
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");
			
			//인증부분
			MyAuth auth = new MyAuth(toMail, password);
			Session ssi = Session.getDefaultInstance(props,auth);
			
			//메세지내용
			MimeMessage msg = new MimeMessage(ssi);
			msg.setHeader("content-type","text/html; charset=utf-8");
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress("mm2004ee@gmail.com",fromName,"utf-8"));
			
			//보낼때
			msg.setSubject(subject);
			//msg.setContent(content,"text/plain; charset=utf-8"); //일반텍스트로넘김
			msg.setContent(content,"text/html; charset=utf-8"); //html 로넘김
			msg.setSentDate(new Date());
			
			
			//첨부파일
			Multipart mul = new MimeMultipart();
			MimeBodyPart bp = new MimeBodyPart();
			bp.setText(content, "utf-8");
			mul.addBodyPart(bp);
			
			FileDataSource fd = new FileDataSource(new File("c:/work/kbs.dmp"));
			bp.setDataHandler(new DataHandler(fd));
			bp.setFileName(fd.getName());
			mul.addBodyPart(bp);
			msg.setContent(mul);
			
			//보내기
			Transport.send(msg);
			System.out.println("메일전송완료");
			
			
		} catch (Exception e) {
			System.out.println("asd" + e);
		}
	}
}
