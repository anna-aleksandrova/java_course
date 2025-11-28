package homeworks.hw10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class B_10_01_Client {
    public static void main(String[] args) throws IOException {
        int port = 14110;
        String host = "localhost";

        String fileInp = "homeworks/hw10/input.txt";

        Socket sock = new Socket(host, port);
        System.out.println("Connected to: " + sock.getRemoteSocketAddress());

        var reader = new BufferedReader(
            new InputStreamReader(
                sock.getInputStream(), StandardCharsets.UTF_8
        ));
        var writer = new PrintStream(
            sock.getOutputStream(), true, StandardCharsets.UTF_8
        );

        var fInp = new BufferedReader(new FileReader(fileInp, StandardCharsets.UTF_8));

        while (true) {
            String msg = fInp.readLine();
            if (msg == null)
                break;
            writer.println(msg);
            System.out.println("Sent: " + msg);
            msg = reader.readLine();
            System.out.println("Received: " + msg);
        }

        fInp.close();
        sock.close();
        System.out.println("Disconnected from: " + sock.getRemoteSocketAddress());
    }
}
