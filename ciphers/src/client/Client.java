package client;

import crypto.Algorithm;
import crypto.CryptoManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Scanner;

/**
 * Main client class.
 * Connects to the server, handles user interaction, and manages
 * encryption/decryption tasks.
 * Supports input from console or file.
 *
 * @author Anna Aleksandrova
 * @version 1.2
 * @since 15.10.2025
 */
public class Client {

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 7777;
    private static final String KEY_STORAGE_PATH = "data/client_keys/";
    private static final String OUTPUT_LOG_FILE = "client_output.txt";
    private static final String TEST_INPUT_FILE = "test/NZ_test.dat";

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private Scanner inputScanner;
    private PrintWriter fileLogger;

    private String username;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public static void main(String[] args) {
        try {
            Files.createDirectories(Paths.get(KEY_STORAGE_PATH));
        } catch (IOException e) {
            System.err.println("Can't create key storage directory: " + e.getMessage());
            return;
        }

        Client client = new Client();
        client.run();
    }

    /**
     * Starts the client application.
     * Asks for mode (console/file), connects to server, runs the main loop, exits.
     */
    public void run() {
        try {
            fileLogger = new PrintWriter(new FileWriter(OUTPUT_LOG_FILE), true);
        } catch (IOException e) {
            System.err.println("Can't create log file.");
            return;
        }

        Scanner in_console = new Scanner(System.in);
        log("Select mode:");
        log("1. Console input");
        log("2. File input (" + TEST_INPUT_FILE + ")");
        System.out.print(">>> "); 
        String mode = in_console.nextLine();
        
        try {
            if ("2".equals(mode)) {
                File file = new File(TEST_INPUT_FILE);
                if (!file.exists()) {
                    log("Error: Test file not found at " + TEST_INPUT_FILE);
                    return;
                }
                this.inputScanner = new Scanner(new FileInputStream(file));
                log("------- RUNNING IN FILE MODE --------");
            } else if ("1".equals(mode)) {
                this.inputScanner = in_console;
                log("------- RUNNING IN CONSOLE MODE --------");
            } else {
                throw new IOException("Expected input: 1 or 2, got: " + mode);
            }

            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String welcomeMessage = in.readLine();
            log("Server: " + welcomeMessage);

            authenticateUser();
            if (this.username != null) mainMenu();

        } catch (UnknownHostException e) {
            log("Server not found: " + e.getMessage());
        } catch (IOException e) {
            log("I/O error: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    /**
     * Helper method to log messages to both console and file.
     */
    private void log(String message) {
        System.out.println(message);
        if (fileLogger != null) fileLogger.println(message);
    }

    /**
     * Displays the main authentication menu.
     * <p> Works until a successful login/registration occurs or the user explicitly exits.
     * <p> Invalid inputs trigger a retry message.
     *
     * @throws IOException If an I/O error occurs during network communication in the helper methods.
     */
    private void authenticateUser() throws IOException {
        while (true) {
            log("\n------- Menu -------");
            log("1. Register a new user");
            log("2. Login as existing user");
            log("3. Exit");

            if (!inputScanner.hasNextLine()) break;
            String choice = inputScanner.nextLine();
            log("User input: " + choice);

            if ("1".equals(choice)) {
                if (register()) break;
            } else if ("2".equals(choice)) {
                if (login()) break;
            } else if ("3".equals(choice)) {
                out.println("EXIT");
                break;
            } else {
                log("Invalid option, please try again.");
            }
        }
    }

    /**
     * Handles the new user registration process:
     * prompts the user for a username and encryption algorithm,
     * generates a new key pair, saves the keys locally, and sends a registration
     * request with the public key to the server.
     *
     * @return {@code true} if the server confirms successful registration; 
     *         {@code false} if the username is empty, or keys already exist, or the server returns an error.
     * @throws IOException If an I/O error occurs during key saving or network communication.
     */
    private boolean register() throws IOException {
        log("Enter new username: ");
        if (!inputScanner.hasNextLine()) return false;
        String user = inputScanner.nextLine();
        log("User input: " + user);

        if (user.isEmpty()) return false;

        if (Files.exists(Paths.get(getPrivateKeyPath(user)))) {
            log("Error: Keys for this user already exist. Try logging in.");
            return false;
        }

        Algorithm chosenAlgorithm = chooseAlgorithm();
        
        log("Generating key pair (" + chosenAlgorithm.getName() + ")...");
        KeyPair keyPair = CryptoManager.generateKeyPair(chosenAlgorithm.getName(), chosenAlgorithm.getKeySize());
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
        this.username = user;

        CryptoManager.saveKey(this.privateKey, getPrivateKeyPath(user));
        CryptoManager.saveKey(this.publicKey, getPublicKeyPath(user));
        log("Keys are generated and saved locally.");

        String encodedPublicKey = Base64.getEncoder().encodeToString(this.publicKey.getEncoded());
        // Protocol: REGISTER:<username>:<algorithm>:<base64_public_key>
        out.println("REGISTER:" + user + ":" + chosenAlgorithm.name() + ":" + encodedPublicKey);

        String serverResponse = in.readLine();
        log("Server: " + serverResponse);
        
        return serverResponse != null && serverResponse.startsWith("OK: User registered");
    }

    /**
     * Handles the user login process by verifying key existence.
     *
     * @return {@code true} if the user is successfully logged in;
     * {@code false} if the username is missing or keys do not exist.
     * @throws IOException If a network error occurs while reading/writing to the server.
     */
    private boolean login() throws IOException {
        log("Enter username: ");
        if (!inputScanner.hasNextLine()) return false;
        String user = inputScanner.nextLine();
        log("User input: " + user);

        try {
            this.privateKey = CryptoManager.loadPrivateKey(getPrivateKeyPath(user));
            this.publicKey = CryptoManager.loadPublicKey(getPublicKeyPath(user));
            this.username = user;
            
            log("Keys loaded from files. Logging in...");
            out.println("LOGIN:" + user);
            String response = in.readLine();
            log("Server: " + response);
            return response != null && response.startsWith("OK");
        } catch (RuntimeException e) {
            log("Login failed: Could not find keys for user '" + user + "'. Please register first.");
            return false;
        }
    }

    /**
     * Displays the main dashboard for an authenticated user.
     *
     * <p>Provides the following options:
     * <ul>
     * <li><b>1. Send (encrypt) a new message:</b> Calls {@code sendMessage()} to encrypt and send a message to the server.</li>
     * <li><b>2. Get (decrypt) my message:</b> Calls {@code getMessage()} to decrypt the latest message send to the server.</li>
     * <li><b>3. Exit:</b> Sends the "EXIT" command to the server and logs out.</li>
     * </ul>
     *
     * @throws IOException If a network error occurs during message transmission or reception.
     */
    private void mainMenu() throws IOException {
        while (true) {
            log("\n--- " + this.username + "'s menu ---");
            log("1. Send (encrypt) a new message");
            log("2. Get (decrypt) my message");
            log("3. Back to client's menu");
            log("4. Exit");

            if (!inputScanner.hasNextLine()) break;
            String choice = inputScanner.nextLine();
            log("User input: " + choice);

            if ("1".equals(choice)) {
                sendMessage();
            } else if ("2".equals(choice)) {
                getMessage();
            } else if ("3".equals(choice)) {
                authenticateUser();
            } else if ("4".equals(choice)) {
                out.println("EXIT");
                break;
            } else {
                log("Invalid option.");
            }
        }
    }

    /**
     * Encrypts and sends a text message to the server.
     *
     * @throws IOException If a network error occurs while sending the data or reading the server response.
     */
    private void sendMessage() throws IOException {
        log("Enter message (will be encrypted with your public key):");
        if (!inputScanner.hasNextLine()) return;
        String message = inputScanner.nextLine();
        log("User input: " + message);

        byte[] encryptedData = CryptoManager.encrypt(message, this.publicKey);
        String base64Encrypted = Base64.getEncoder().encodeToString(encryptedData);

        out.println("SEND:" + base64Encrypted);
        String response = in.readLine();
        log("Server: " + response);
    }

    /**
     * Retrieves and decrypts the user's message from the server.
     *
     * @throws IOException If a network error occurs during the transaction.
     */
    private void getMessage() throws IOException {
        out.println("GET");
        String serverResponse = in.readLine();
        
        if (serverResponse != null && serverResponse.startsWith("DATA:")) {
            String base64Encrypted = serverResponse.substring(5);
            byte[] encryptedData = Base64.getDecoder().decode(base64Encrypted);

            String decryptedMessage = CryptoManager.decrypt(encryptedData, this.privateKey);
            log("--- Your Decrypted Message ---");
            log(decryptedMessage);
            log("--------------------------------");
        } else {
            log("Server: " + serverResponse);
        }
    }

    /**
     * Prompts the user to select an encryption algorithm via the console.
     *
     * <p> Offers a choice between EL_GAMAL (1) and RSA_4096 (2).
     * If the user input is invalid, empty, or if the scanner has no lines,
     * the method defaults to {@code Algorithm.EL_GAMAL}.
     *
     * @return The selected {@link Algorithm} enum constant.
     */
    private Algorithm chooseAlgorithm() {
        log("Choose an algorithm:");
        log("1. " + Algorithm.EL_GAMAL.name());
        log("2. " + Algorithm.RSA_4096.name());
        
        while (true) {
            if (!inputScanner.hasNextLine()) return Algorithm.EL_GAMAL;
            
            String choice = inputScanner.nextLine();
            log("User input: " + choice);

            if ("2".equals(choice)) {
                return Algorithm.RSA_4096;
            }
            if ("1".equals(choice) || choice.isEmpty()) {
                return Algorithm.EL_GAMAL;
            }
            log("Invalid selection. Defaulting to ElGamal.");
            return Algorithm.EL_GAMAL;
        }
    }

    /**
     * Constructs the absolute file path for a user's private key.
     *
     * @param user The username for which to retrieve the key path.
     * @return A String representing the full file path to the private key.
     */
    private String getPrivateKeyPath(String user) {
        return KEY_STORAGE_PATH + user + ".priv";
    }

    /**
     * Constructs the absolute file path for a user's public key.
     *
     * @param user The username for which to retrieve the key path.
     * @return A String representing the full file path to the public key.
     */
    private String getPublicKeyPath(String user) {
        return KEY_STORAGE_PATH + user + ".pub";
    }

    /**
     * Closes all open files and network connections.
     */
    private void closeResources() {
        try {
            if (socket != null) socket.close();
            if (inputScanner != null) inputScanner.close();
            if (fileLogger != null) fileLogger.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Disconnected.");
    }
}
