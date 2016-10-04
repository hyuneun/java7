import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test extends JFrame implements ActionListener{
	JTextArea tf = new JTextArea();
	JButton btn = new JButton("확인");
	JTextField te = new JTextField("",20);

	
	public Test() {
		
		
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("URL : "));
		panel.add(te);
		panel.add(btn);
		tf.setEditable(false);
		JScrollPane sc = new JScrollPane(tf);
		add("North",panel);
		add("Center",sc);
		btn.addActionListener(this);
		
		setBounds(200, 200, 400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			URL url = new URL(te.getText());
			
			InputStream in = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String ss;
			while((ss = br.readLine()) != null){
				tf.append(ss + "\n");
			}
			
			in.close();
			br.close();
			
		} catch (Exception e2) {
			System.out.println(e2);
			
		}
		
	}
	
	public static void main(String[] args) {
		new Test();

	}

}
;