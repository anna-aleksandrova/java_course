package practices.practice10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class A_10_01_Server {
    
    public static void main(String[] args) throws IOException{
        int port = 14110;

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server running on " + server.getLocalSocketAddress());
            while (true) {
                Socket conn = server.accept();
                System.out.println("Client connected: " + conn.getRemoteSocketAddress());

                var reader = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream(), StandardCharsets.UTF_8
                        ));
                var writer = new PrintStream(
                    conn.getOutputStream(), true, StandardCharsets.UTF_8
                );

                int i = 0;
                while (true) {
                    String msg = reader.readLine();
                    System.out.println("Received: " + msg);
                    if (msg == null) break;
                    msg = ++i + " " + msg;
                    writer.println(msg);
                    System.out.println("Sent: " + msg);
                }

                conn.close();
                System.out.println("Client disconnected: " + conn.getRemoteSocketAddress());
            }
        }
    }
}
