package practices.practice11;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A_11_02 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "https://slovnyk.ua/?swrd=";
		Scanner in = new Scanner(System.in, "UTF-8");
		
		System.out.print("Введіть слово: ");
		String word = in.next();

		url = url + URLEncoder.encode(word, "UTF-8");
		System.out.println(url);
		String page = getHTML(url);
		
		System.out.println(getDecl(page));
		
		in.close();
	}
	
	public static ArrayList<ArrayList<String> > getDecl(String page) {
		Pattern p = Pattern.compile("<div class=\"toggle-content\".*?>(.*?)</div>", Pattern.MULTILINE | Pattern.DOTALL);
		Matcher m = p.matcher(page);

		ArrayList<ArrayList<String> > res = new ArrayList<>();
		if (!m.find()) {
			System.err.println("Нема :(");
			return res;
		}
		if (!m.find() || !m.group(1).contains("іменник")) {
			System.err.println("Не іменник!");
			return res;
		}
		String section = m.group(1);
		
		p = Pattern.compile("<th .*?>(.*?)</th.*?>", Pattern.MULTILINE | Pattern.DOTALL);
		m = p.matcher(section);
		res.add(new ArrayList<>());
		while (m.find()) {
			res.get(0).add(m.group(1));
		}
		p = Pattern.compile("<td.*?>(.*?)</td.*?>", Pattern.MULTILINE | Pattern.DOTALL);
		m = p.matcher(section);
		
		int i = 0;
		while (m.find()) {
			if (i % 3 == 0) {
				res.add(new ArrayList<>());
				i = 0;
			}
			res.getLast().add(m.group(1));
			i++;
		}
		
		return res;
	}
	
	public static String getHTML(String urlString) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(urlString))
	        .build();
		
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.err.println("Status: " + response.statusCode());
			return response.body();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	   return "";
	}
}
