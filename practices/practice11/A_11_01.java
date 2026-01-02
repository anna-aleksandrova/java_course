package practices.practice11;

import java.util.Scanner;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class A_11_01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String site = "https://slovnyk.ua";
        String word = in.next();
        String url = site + "/?swrd=" + URLEncoder.encode(word, StandardCharsets.UTF_8);

        String html = getHTML(url);
        String result = parse(html);
        System.out.println(result);
        in.close();
    }

    public static String parse(String html) {
        Pattern p = Pattern.compile(
            "<div class=\"toggle-content\".*?>(.*?)</div>",
            Pattern.DOTALL 
        );
        Matcher m = p.matcher(html);
        if (m.find()) {
            return m.group(1).trim().replaceAll("<.*?>", "");
        }
        return "";
    }

    public static String getHTML(String url) {
        URI uri = URI.create(url);
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder(uri)
            .GET()
            .header("user-agent", "Hello!")
            .build();
            HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
