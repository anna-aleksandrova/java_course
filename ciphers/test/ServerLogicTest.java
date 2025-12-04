package test;

import crypto.Algorithm;
import crypto.CryptoManager;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Server;

import java.lang.reflect.Field;
import java.security.KeyPair;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Server static logic.
 * Uses Reflection to initialize private static maps for testing
 * without starting the network socket.
 *
 * @author Anna Aleksandrova
 * @version 1.0
 * @since 04.11.2025
 */
public class ServerLogicTest {

    @BeforeEach
    void setUpServerState() throws Exception {
        // Use Reflection to reset the static maps in Server class
        // This allows us to test logic without running main()
        
        Field userStoreField = Server.class.getDeclaredField("userStore");
        userStoreField.setAccessible(true);
        userStoreField.set(null, new ConcurrentHashMap<String, User>());

        Field msgStoreField = Server.class.getDeclaredField("messageStore");
        msgStoreField.setAccessible(true);
        msgStoreField.set(null, new ConcurrentHashMap<String, byte[]>());
    }

    @Test
    void testRegisterUser() {
        KeyPair kp = CryptoManager.generateKeyPair("RSA", 4096);
        User user = new User("TestLogicUser", kp.getPublic(), Algorithm.RSA_4096);

        boolean firstReg = Server.registerUser(user);
        assertTrue(firstReg, "First registration should succeed");

        boolean secondReg = Server.registerUser(user);
        assertFalse(secondReg, "Duplicate registration should fail");
    }

    @Test
    void testUserExists() {
        KeyPair kp = CryptoManager.generateKeyPair("RSA", 4096);
        User user = new User("ExistingUser", kp.getPublic(), Algorithm.RSA_4096);
        Server.registerUser(user);

        assertTrue(Server.userExists("ExistingUser"));
        assertFalse(Server.userExists("NonExistentUser"));
    }

    @Test
    void testStoreAndGetMessage() {
        String username = "MsgUser";
        byte[] msgData = new byte[]{10, 20, 30};

        Server.storeMessage(username, msgData);

        byte[] retrieved = Server.getMessage(username);
        assertArrayEquals(msgData, retrieved);
        assertNull(Server.getMessage("Nobody"));
    }
}
