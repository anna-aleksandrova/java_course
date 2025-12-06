package test;

import crypto.Algorithm;
import crypto.CryptoManager;
import model.User;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for class User.
 * Covers constructors and getters.
 *
 * @author Anna Aleksandrova
 * @version 1.0
 * @since 06.11.2025
 */
public class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        KeyPair kp = CryptoManager.generateKeyPair("RSA", 4096);
        PublicKey pubKey = kp.getPublic();
        String username = "TestUser";
        Algorithm algo = Algorithm.RSA_4096;

        User user = new User(username, pubKey, algo);

        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(pubKey, user.getPublicKey());
        assertEquals(algo, user.getAlgorithm());
    }
}
