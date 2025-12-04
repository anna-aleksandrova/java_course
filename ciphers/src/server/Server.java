package server;

import model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main server class.
 * Accepts new client connections and manages persistent data stores.
 *
 * @author Anna Aleksandrova
 * @version 1.1
 * @since 2025-10-20
 */
public class Server {

    private static final int PORT = 7777;
    private static final String USER_STORE_PATH = "data/server_storage/users.dat";
    private static final String MSG_STORE_PATH = "data/server_storage/messages.dat";

    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    private static Map<String, User> userStore;
    private static Map<String, byte[]> messageStore;

    /**
     * Entry point for the Server application.
     * <p>
     * This method initializes the server and keeps it running indefinitely to accept
     * incoming client connections. It performs the following steps:
     * <ol>
     * <li>Loads persistent data (users and messages) from disk into memory via {@code loadData()}.</li>
     * <li>Opens a {@link ServerSocket} on the specified {@code PORT}.</li>
     * <li>Enters an infinite loop to listen for connection requests.</li>
     * <li>When a client connects, accepts the {@link Socket} and delegates the processing
     * to a {@link ClientHandler} managed by a thread pool for concurrent execution.</li>
     * </ol>
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        loadData();

        System.out.println("Server is running...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Error on the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Registers a new user.
     * @param user The User object.
     * @return true if success, false if user already exists.
     */
    public static synchronized boolean registerUser(User user) {
        if (userStore.containsKey(user.getUsername())) {
            return false;
        }
        userStore.put(user.getUsername(), user);
        saveData();
        return true;
    }

    /**
     * Checks if a user exists for login.
     * @param username The username.
     * @return true if user exists, false otherwise.
     */
    public static synchronized boolean userExists(String username) {
        return userStore.containsKey(username);
    }

    /**
     * Stores an encrypted message for a user.
     * @param username The user.
     * @param encryptedData The message.
     */
    public static synchronized void storeMessage(String username, byte[] encryptedData) {
        messageStore.put(username, encryptedData);
        saveData();
    }

    /**
     * Retrieves the encrypted message for a user.
     * @param username The user.
     * @return The encrypted message as byte[], or null if not found.
     */
    public static synchronized byte[] getMessage(String username) {
        return messageStore.get(username);
    }

    /**
     * Loads user and message data from files into memory.
     */
    private static void loadData() {
        System.out.println("Loading data from files...");
        userStore = loadMapFromFile(USER_STORE_PATH);
        messageStore = loadMapFromFile(MSG_STORE_PATH);
        System.out.println("Loaded " + userStore.size() + " users and " + messageStore.size() + " messages.");
    }

    /**
     * Saves the current user and message maps to files.
     */
    private static synchronized void saveData() {
        saveMapToFile(userStore, USER_STORE_PATH);
        saveMapToFile(messageStore, MSG_STORE_PATH);
        System.out.println("Data is saved.");
    }

    /**
     * Helper to load a generic Map from a file.
     * @param path The file path.
     * @return The loaded Map, or a new ConcurrentHashMap if file not found.
     */
    @SuppressWarnings("unchecked")
    private static <K, V> Map<K, V> loadMapFromFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return new ConcurrentHashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<K, V>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Warning: Could not load data from " + path + ". Creating new map. Error: " + e.getMessage());
            return new ConcurrentHashMap<>();
        }
    }

    /**
     * Helper to save a generic Map to a file.
     * @param map The map to save.
     * @param path The file path.
     */
    private static synchronized <K, V> void saveMapToFile(Map<K, V> map, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(map);
        } catch (IOException e) {
            System.err.println("Error saving data to " + path + ": " + e.getMessage());
        }
    }
}