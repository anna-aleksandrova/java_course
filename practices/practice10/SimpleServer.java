package practices.practice10;

import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            System.out.println("Waiting for a client to connect...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client has connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine = in.readLine();
            System.out.println("Received from client: " + inputLine);

            out.println("Message received: " + inputLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

