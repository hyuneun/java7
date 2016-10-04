package pack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class test extends JFrame implements ActionListener {
	JTextField tf = new JTextField("", 15);
	JTextField tf2 = new JTextField("", 15);
	JTextField tf3 = new JTextField("", 15);
	JTextArea ta = new JTextArea("", 5, 20);
	JRadioButton b1 = new JRadioButton("text");
	JRadioButton b2 = new JRadioButton("html");
	ButtonGroup g = new ButtonGroup();
	JButton btn = new JButton("전송");
	JButton btn2 = new JButton("선택");
	String imgPath;
	File file;
	
	int count;
	String pp;
	String password = "gusdms12!";
	String toMail = "mm2004ee@gmail.com";
	String fromName = "홍길동";

	Properties props = new Properties();
	MyAuth auth = new MyAuth(toMail, password);
	Session ssi = Session.getDefaultInstance(props, auth);
	MimeMessage msg = new MimeMessage(ssi);
	
	Multipart mul = new MimeMultipart();
	MimeBodyPart bp = new MimeBodyPart();

	public test() {
		JPanel panel = new JPanel(new GridLayout(6, 0));
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();

		g.add(b1);
		g.add(b2);
		tf3.setEditable(false);
		panel2.add(new JLabel("받는 Email : "));
		panel2.add(tf);
		panel.add(panel2);

		panel3.add(new JLabel("제목"));
		panel3.add(tf2);
		panel.add(panel3);

		panel4.add(new JLabel("속성 : "));
		panel4.add(b1);
		panel4.add(b2);
		panel.add(panel4);

		panel5.add(new JLabel("내용 : "));
		JScrollPane sc = new JScrollPane(ta);
		panel5.add(sc);
		panel.add(panel5);

		panel6.add(new JLabel("첨부파일 : "));
		panel6.add(tf3);
		panel6.add(btn2);
		panel.add(panel6);
		panel.add(btn);

		add("Center", panel);

		setBounds(200, 200, 700, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btn.addActionListener(this);
		btn2.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);

		try {
			// 서버에대한설정

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");

			// 인증부분

			// msg.setContent(ta.getText(),"text/plain; charset=utf-8");
			// //일반텍스트로넘김
			// msg.setContent(content,"text/html; charset=utf-8"); //html 로넘김

			// 첨부파일
			// 

		} catch (Exception e) {
			System.out.println("asdsssss" + e);
		}
	}

	public static void main(String[] args) {
		new test();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (b1.isSelected()) {
			
			pp = "text/plain; charset=utf-8";
			
		} else if (b2.isSelected()) {
		
			pp = "text/html; charset=utf-8";

			
		}

		if (e.getSource().equals(btn2)) {
			JFileChooser cho = new JFileChooser("C:/work/sou/");
			cho.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result = cho.showOpenDialog(this);
			
			if(result == JFileChooser.CANCEL_OPTION){
				file = null;
			}else{
				file = cho.getSelectedFile();
				imgPath = file.getPath().replace("\\", "/");
				tf3.setText(imgPath);
				count++;

			}
		}

		if (e.getSource().equals(btn)) {

			try {
				// 메세지내용

				msg.setHeader("content-type", "text/plain; charset=utf-8");
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(tf.getText(), fromName, "utf-8"));

				// 보낼때
				msg.setSubject(tf2.getText());
				msg.setContent(ta.getText(), pp);
				msg.setSentDate(new Date());
				
				//첨부
				if(count != 0){
				bp.setText(ta.getText(), "utf-8");
				FileDataSource fd = new FileDataSource(new
						File(imgPath));
				bp.setDataHandler(new DataHandler(fd));
				bp.setFileName(fd.getName());
				mul.addBodyPart(bp);
				msg.setContent(mul);
				
				}
				
				Transport.send(msg);
				System.out.println("메일전송완료");

			} catch (Exception e2) {
				System.out.println("전송실패" + e2);
			}

		}
	}

}
