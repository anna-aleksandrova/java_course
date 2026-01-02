package practices.practice10;

import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;

        try (Socket socket = new Socket(host, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = "Hello, Server!";
            out.println(message);
            System.out.println("Sent to server: " + message);

            String response = in.readLine();
            System.out.println("Received from server: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
