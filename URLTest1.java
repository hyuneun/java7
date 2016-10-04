import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class URLTest1 {

	public URLTest1(String str) {
		try {
			URL url = new URL(str);
			
			InputStream in = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String ss;
			while((ss = br.readLine()) != null){
				System.out.println(ss);
			}
			in.close();
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		new URLTest1("http://www.naver.com:80/index.html");

	}

}
;