package practices.practice10

import java.net.Socket;
import java.io.*;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;

public class A_10_01Server {
    public static void main(String[] args) throws IOException {
        int port = 1114;

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server is running on: " + server.getLocalPort());

            Socket conn = server.accept();
            
            System.out.println("Disconnected from: " + conn.getRemoteSocketAddress());

            var reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

            var writer = new PrintWriter(conn.getOutputStream(), true, StandardCharsets.UTF_8);

            int i = 0;
            while (true) {
                String mes = reader.readLine();
                System.out.println("Received: " + mes);
                if (mes == null) break;
                mes = ++i + " " + mes;

                System.out.println();
            }
        }
    }
}