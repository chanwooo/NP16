package lab6_uris_http;

import java.io.*;
import java.net.*;

public class searchForGoogle {

	public static void main(String[] args) {
		String target = "";
		for (int i = 0; i < args.length; i++) {
			target += args[i] + " ";
		}
		target = target.trim();
		QueryString query = new QueryString();
		query.add("q", target);
		try {
			//		URL u = new URL("https://search.naver.com/search.naver?query=hongik");
			URL u = new URL("https://www.google.co.kr/search?q=hongik");  

			HttpURLConnection huc = (HttpURLConnection)u.openConnection(); 
			huc.setRequestMethod("GET");  
			huc.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");  
			//BufferedReader reader = new BufferedReader(new InputStreamReader(huc.getInputStream(),"utf-8"));
			System.out.println(u);

			try (InputStream in = new BufferedInputStream(huc.getInputStream())) {
				InputStreamReader theHTML = new InputStreamReader(in);
				int c;
				while ((c = theHTML.read()) != -1) {
					System.out.print((char) c);
				}
			}
		} catch (MalformedURLException ex) {
			System.err.println(ex);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
