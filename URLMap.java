import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class URLMap extends JFrame {
	JPanel panel = new JPanel();
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();
	JButton btns = new JButton("검색");
	JButton btnm = new JButton("지도보기");

	public URLMap() {
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// 1행
		JLabel lb = new JLabel("입력 : ");
		lb.setBounds(30, 15, 100, 15);
		panel.add(lb);

		tf1.setBounds(100, 15, 255, 20);
		tf1.setColumns(10);
		panel.add(tf1);

		btns.setBounds(380, 10, 100, 25);
		panel.add(btns);

		// 2행
		JLabel lb2 = new JLabel("주소 : ");
		lb2.setBounds(30, 45, 100, 15);
		panel.add(lb2);

		tf2.setBounds(100, 45, 255, 20);
		tf2.setColumns(10);
		tf2.setEditable(false);
		panel.add(tf2);

		btnm.setBounds(380, 40, 100, 25);
		panel.add(btnm);

		// 3행
		JLabel lb3 = new JLabel("위도 : ");
		lb3.setBounds(30, 75, 100, 15);
		panel.add(lb3);

		tf3.setBounds(100, 75, 255, 20);
		tf3.setColumns(10);
		tf3.setEditable(false);
		panel.add(tf3);

		// 4행
		JLabel lb4 = new JLabel("경도 : ");
		lb4.setBounds(30, 105, 100, 15);
		panel.add(lb4);

		tf4.setBounds(100, 105, 255, 20);
		tf4.setColumns(10);
		tf4.setEditable(false);
		panel.add(tf4);

		setBounds(100, 100, 500, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addevent();
	}

	private void addevent() {
		btns.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String search = tf1.getText();
				String search1 = "https://maps.googleapis.com/maps/api/geocode/xml?address=";
				String search2 = "&language=ko";
				String search_make = search1 + search + search2;

				// System.out.println(search_make);

				try {
					BufferedReader br = null;
					URL url = new URL(search_make);
					br = new BufferedReader(new InputStreamReader(url.openStream()));
					// System.out.println(br.readLine());

					String line = null, address = null, lat = null, lng = null;

					while ((line = br.readLine()) != null) {
						// System.out.println(line);
						// 주소얻기
						if (line.contains("formatted_address")) {
							line = line.replace("<formatted_address>", "");
							line = line.replace("</formatted_address>", "");
							address = line.trim();
						} else if (line.contains("<lat>")) {
							line = line.replace("<lat>", "");
							line = line.replace("</lat>", "");
							lat = line.trim();
						} else if (line.contains("<lng>")) {
							line = line.replace("<lng>", "");
							line = line.replace("</lng>", "");
							lng = line.trim();
							break;
						}
					}
					tf2.setText(address);
					tf3.setText(lat);
					tf4.setText(lng);
				} catch (Exception e2) {
					System.out.println(e2);
				}

			}
		});
		btnm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String lat = tf3.getText();
				String lng = tf4.getText();
				String url = "http://www.google.co.kr/maps/@" + lat + "," + lng + ",18z";
				
				//브라우저 실행(ProcessBuilder)실행파일열때
				ProcessBuilder pb = new ProcessBuilder("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe",url);
				 try {
					pb.start();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

	}

	public static void main(String[] args) {
		new URLMap();

	}

}
