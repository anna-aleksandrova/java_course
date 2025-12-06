package test;

import crypto.Algorithm;
import crypto.CryptoManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the CryptoManager class.
 * Verifies ElGamal (Custom) and RSA (Standard) logic, I/O.
 *
 * @author Anna Aleksandrova
 * @version 1.0
 * @since 04.11.2025
 */
public class CryptoManagerTest {

    private final String testMessage = "Hello, world!";

    @TempDir
    Path tempDir;

    @Test
    void testElGamalCycle() {
        KeyPair keyPair = CryptoManager.generateKeyPair(
            Algorithm.EL_GAMAL.getName(), 
            Algorithm.EL_GAMAL.getKeySize()
        );
        assertEquals("ElGamal", keyPair.getPublic().getAlgorithm());

        byte[] encrypted = CryptoManager.encrypt(testMessage, keyPair.getPublic());
        String decrypted = CryptoManager.decrypt(encrypted, keyPair.getPrivate());
        assertEquals(testMessage, decrypted);
    }

    @Test
    void testRSACycle() {
        System.out.println("Testing RSA-4096 cycle (this may take time) ...");

        KeyPair keyPair = CryptoManager.generateKeyPair(
            Algorithm.RSA_4096.getName(), 
            Algorithm.RSA_4096.getKeySize()
        );
        assertEquals("RSA", keyPair.getPublic().getAlgorithm());

        byte[] encrypted = CryptoManager.encrypt(testMessage, keyPair.getPublic());
        
        String decrypted = CryptoManager.decrypt(encrypted, keyPair.getPrivate());
        assertEquals(testMessage, decrypted);

        System.out.println("RSA-4096 cycle passed.");
    }

    @Test
    void testKeyDecoding() {
        KeyPair elGamalKeys = CryptoManager.generateKeyPair("ElGamal", 1024);
        byte[] encoded = elGamalKeys.getPublic().getEncoded();
        
        PublicKey decoded = CryptoManager.decodePublicKey(Algorithm.EL_GAMAL, encoded);
        assertNotNull(decoded);
        assertEquals("ElGamal", decoded.getAlgorithm());

        System.out.println("Generating RSA-4096 key for decoding test...");
        KeyPair rsaKeys = CryptoManager.generateKeyPair(
            Algorithm.RSA_4096.getName(), 
            Algorithm.RSA_4096.getKeySize()
        );
        byte[] rsaBytes = rsaKeys.getPublic().getEncoded();
        
        PublicKey decodedRSA = CryptoManager.decodePublicKey(Algorithm.RSA_4096, rsaBytes);
        assertNotNull(decodedRSA);
        assertEquals("RSA", decodedRSA.getAlgorithm());
        
        System.out.println("Key decoding passed.");
    }

    @Test
    void testSaveAndLoadKeys() {
        KeyPair kp = CryptoManager.generateKeyPair("ElGamal", 1024);
        Path pubPath = tempDir.resolve("key.pub");
        Path privPath = tempDir.resolve("key.priv");

        CryptoManager.saveKey(kp.getPublic(), pubPath.toString());
        CryptoManager.saveKey(kp.getPrivate(), privPath.toString());

        PublicKey loadedPub = CryptoManager.loadPublicKey(pubPath.toString());
        PrivateKey loadedPriv = CryptoManager.loadPrivateKey(privPath.toString());

        assertNotNull(loadedPub);
        assertNotNull(loadedPriv);
    }
}
