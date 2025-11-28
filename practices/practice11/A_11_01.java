package practices.practice11;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.util.Scanner;
import java.util.regex.Pattern;

public class A_11_01 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner in = new Scanner(System.in);

        String site = "https://slovnyk.ua";
        String word = in.next();

        String url = site + "/?swrd=" + URLEncoder.encode(word, StandartCharsets.UTF_8);
        System.out.println(url);
        in.close();
    }

    public static String parser(String html) {
        Pattern g = Pattern.compile(
            "<div> class "
        );
        Matcher m = p.matcher(g);
        return "";
    }

    public static String getHTML(String url) {
        URI url = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().header("user-agent", "Hello!").build();

        HttpResponse<String> response = client.send
    }
    
}
