package homeworks.hw10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class B_10_01_Server {
    
    public static void main(String[] args) throws IOException {
        int port = 14110;

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server running on " + server.getLocalSocketAddress());
            while (true) {
                Socket conn = server.accept();
                System.out.println("Client connected: " + conn.getRemoteSocketAddress());

                var reader = new BufferedReader(
                    new InputStreamReader(
                        conn.getInputStream(), StandardCharsets.UTF_8)
                    );
                var writer = new PrintStream(
                    conn.getOutputStream(), true, StandardCharsets.UTF_8
                );

                while (true) {
                    String msg = reader.readLine();
                    System.out.println("Received: " + msg);
                    if (msg == null) break;
                    String[] str_nums = msg.trim().split("\\s+");
                    ArrayList<Integer> nums = new ArrayList<>();
                    for (String n : str_nums) {
                        nums.add(Integer.parseInt(n));
                    }
                    int min = Collections.min(nums);
                    int max = Collections.max(nums);
                    writer.println("min: " + min + ", max: " + max);
                    System.out.println("Sent: min: " + min + ", max: " + max);
                }
                conn.close();
                System.out.println("Client disconnected: " + conn.getRemoteSocketAddress());
            }
        }
    }
}
