package server;

import crypto.Algorithm;
import crypto.CryptoManager;
import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Base64;

/**
 * Handler for an individual client.
 * Runs in a separate thread and handles the client-server protocol.
 *
 * @author Anna Aleksandrova
 * @version 1.1
 * @since 01.11.2025
 */
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String currentUsername;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.currentUsername = null;
    }

    /**
     * Handles a specific client connection.
     */
    @Override
    public void run() {
        try (
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            this.out = out;
            this.in = in;

            out.println("You're at the server.");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client (" + clientSocket.getInetAddress() + "): " + inputLine);
                handleCommand(inputLine);
                if ("EXIT".equalsIgnoreCase(inputLine)) break;
            }

        } catch (IOException e) {
            System.err.println("ClientHandler error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client disconnected: " + clientSocket.getInetAddress());
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    /**
     * Parses the raw command string received from the client and calls the appropriate function to handle it.
     * 
     * @param command The full raw command string received from the socket (e.g., "LOGIN:User1").
     */
    private void handleCommand(String command) {
        String[] parts = command.split(":", 2);
        String action = parts[0].toUpperCase();

        try {
            switch (action) {
                case "REGISTER": handleRegister(parts[1]); break;
                case "LOGIN": handleLogin(parts[1]); break;
                case "SEND": handleSend(parts[1]); break;
                case "GET": handleGet(); break;
                case "EXIT": break;
                default: out.println("ERROR: Unknown command.");
            }
        } catch (Exception e) {
            System.err.println("Error processing command '" + action + "': " + e.getMessage());
            e.printStackTrace();
            out.println("ERROR: Invalid command format or server error.");
        }
    }

    /**
     * Processes a registration request from the client.
     * <p>
     * Parses the received data string to extract the username, selected algorithm,
     * and the Base64-encoded public key. It reconstructs the public key object,
     * attempts to register the new user in the server's storage, and automatically logs
     * the user in in case of success.
     *
     * @param data The raw data string in the format {@code "username:algorithm:base64Key"}.
     * @throws Exception If key decoding fails or the data format is invalid.
     */
    private void handleRegister(String data) throws Exception {
        String[] parts = data.split(":", 3);
        String username = parts[0];
        Algorithm algorithm = Algorithm.valueOf(parts[1]);
        String base64Key = parts[2];

        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        PublicKey publicKey = CryptoManager.decodePublicKey(algorithm, keyBytes);

        User newUser = new User(username, publicKey, algorithm);
        if (Server.registerUser(newUser)) {
            this.currentUsername = username;
            out.println("OK: User registered and logged in.");
        } else {
            out.println("ERROR: Username already exists.");
        }
    }

    /**
     * Processes a login request from the client.
     * <p>
     * Checks if the provided username exists in the server's user registry.
     * If the user is found, the session is authenticated by setting the {@code currentUsername}.
     *
     * @param username The username provided by the client.
     */
    private void handleLogin(String username) {
        if (Server.userExists(username)) {
            this.currentUsername = username;
            out.println("OK: Logged in as " + username);
        } else {
            out.println("ERROR: User not found.");
        }
    }

    /**
     * Processes a request to store an encrypted message on the server.
     *
     * @param base64Data The encrypted message encoded as a Base64 string.
     */
    private void handleSend(String base64Data) {
        if (this.currentUsername == null) {
            out.println("ERROR: You must be logged in to send a message.");
            return;
        }
        byte[] encryptedData = Base64.getDecoder().decode(base64Data);
        Server.storeMessage(this.currentUsername, encryptedData);
        out.println("OK: Message stored successfully.");
    }

    /**
     * Processes a request to retrieve an encrypted message for the current user.
     * <p>
     * If the user is not logged in or no message is found, an {@code ERROR} message is sent instead.
     */
    private void handleGet() {
        if (this.currentUsername == null) {
            out.println("ERROR: You must be logged in to get a message.");
            return;
        }
        byte[] encryptedData = Server.getMessage(this.currentUsername);
        if (encryptedData != null) {
            String base64Encrypted = Base64.getEncoder().encodeToString(encryptedData);
            out.println("DATA:" + base64Encrypted);
        } else {
            out.println("ERROR: No message found for user " + this.currentUsername);
        }
    }
}
