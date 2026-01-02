package practices.practice10;

import java.io.*;
import java.lang.module.FindException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class A_10_01_Client {
    
    public static void main(String[] args) throws IOException {
        int port = 14110;
        String host = "localhost";

        String fileInp = "practices/practice10/input.txt";
        String fileOut = "practices/practice10/output.txt";

        Socket socket = new Socket(host, port);
        System.out.println("Connected to: " + socket.getRemoteSocketAddress());

        var reader = new BufferedReader (
            new InputStreamReader(
                socket.getInputStream(), StandardCharsets.UTF_8
            )
        );
        var writer = new PrintStream(
            socket.getOutputStream(), true, StandardCharsets.UTF_8
        );
        var fInp = new BufferedReader(new FileReader(fileInp, StandardCharsets.UTF_8));
        var fOut = new BufferedWriter(new FileWriter(fileOut, StandardCharsets.UTF_8));

        while (true) {
            String msg = fInp.readLine();
            if (msg == null) break;
            writer.println(msg);
            System.out.println("Sent: " + msg);
            msg = reader.readLine();
            System.out.println("Received: " + msg);
            fOut.write(msg + "\n");
        }

        fInp.close();
        fOut.close();
        socket.close();
        System.out.println("Disconnected from: " + socket.getRemoteSocketAddress());
    }
}
