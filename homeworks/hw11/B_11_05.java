package homeworks.hw11;

import java.util.Scanner;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B_11_05 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введіть назву міста: ");
        String word = in.next();

        String url = "https://ua.sinoptik.ua/" + 
                     URLEncoder.encode("погода", StandardCharsets.UTF_8) + "-" + 
                     URLEncoder.encode(word, StandardCharsets.UTF_8) + "/";

        String html = getHTML(url);
        String result = parse(html);
        System.out.println(result);
        in.close();
    }

    public static String getHTML(String url) {
        HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();
        
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
            .GET()
            .header("user-agent", "Hello!")
            .build();

        try {
            HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String parse(String html) {
        StringBuilder sb = new StringBuilder();
        
        Pattern p = Pattern.compile(
            "(?s)<span class=\\\"RSWdP9mW.*?\\\">(.*?)</span>" + 
            "<span class=\\\"yQxWb1P4\\\">(.*?)</span>" +
            ".*?" +
            "мін.</p><p>(.*?)</p>" +
            ".*?" +
            "макс.</p><p>(.*?)</p>"
        );
        
        Matcher m = p.matcher(html);

        while (m.find()) {
            sb.append(m.group(1)).append(" ")
            .append(m.group(2)).append(": min: ")
            .append(m.group(3)).append(", max: ")
            .append(m.group(4)).append("\n");
        }

        return sb.toString();
    }
}
