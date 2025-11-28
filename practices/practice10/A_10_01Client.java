package practices.practice10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Socket;

public class A_10_01Client {
    public static void main(String[] args) {
        int port = 1114;
        String host = "localhost";

        Socket socket = new Socket(host, port);
        System.out.println("Connected to: " + socket.get);

        var flip = new BufferedReader(new FileReader(fileInp));
        
    }
}
