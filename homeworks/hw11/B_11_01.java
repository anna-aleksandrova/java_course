package homeworks.hw11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class B_11_01 {
    public static void main(String[] args) {
        String url = "https://www.timeanddate.com/worldclock/ukraine/kyiv";
        String html = getHTML(url);
        String result = parse(html);
        System.out.println("Time from the site: " + result);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
        String local = LocalTime.now().format(f);
        System.out.println("Local time: " + local);

        if (result.equals(local)) System.out.println("The same.");
        else System.out.println("Not the same.");
    }

    public static String parse(String html) {
        Pattern p = Pattern.compile("id=\\\"?ct\\\"? class=\\\"?h1\\\"?>(.*?)</span>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(html);

        if (m.find()) return m.group(1).trim();
        else return "";
    }

    public static String getHTML(String url) {
        URI uri = URI.create(url);
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder(uri).GET().header("user-agent", "Hello!").build();
            HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
