package crypto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

/**
 * Cryptography Manager.
 * Implementation of RSA and ElGamal.
 *
 * @author Anna Aleksandrova
 * @version 2.0
 * @since 17.10.2025
 */
public class CryptoManager {

    /**
     * Generates a public/private key pair for the specified encryption algorithm.
     * 
     * @param algorithm The name of the algorithm to use (e.g., "RSA" or "ElGamal").
     * @param keySize   The size of the key in bits (e.g., 2048, 4096).
     * @return A {@link KeyPair} containing the generated public and private keys.
     * @throws RuntimeException If the specified algorithm is not supported.
     */
    public static KeyPair generateKeyPair(String algorithm, int keySize) {
        if ("RSA".equals(algorithm)) {
            return generateRSAKeyPair(keySize);
        } else if ("ElGamal".equals(algorithm)) {
            return generateElGamalKeyPair(keySize);
        } else {
            throw new RuntimeException("Unknown algorithm: " + algorithm);
        }
    }

    /**
     * Encrypts plaintext data using the provided Public Key.
     *
     * @param data The plaintext to encrypt.
     * @param key  The Public Key to use for encryption (must be an instance of {@link RSAPublicKey} or {@link ElGamalPublicKey}).
     * @return A byte array containing the encrypted data (ciphertext).
     * @throws RuntimeException If the key type is unknown or encryption fails.
     */
    public static byte[] encrypt(String data, PublicKey key) {
        if (key instanceof RSAPublicKey) {
            return encryptRSA(data, (RSAPublicKey) key);
        } else if (key instanceof ElGamalPublicKey) {
            return encryptElGamal(data, (ElGamalPublicKey) key);
        }
        throw new RuntimeException("Unknown key type");
    }

    /**
     * Decrypts ciphertext data using the provided Private Key.
     * 
     * @param encryptedData The byte array containing the ciphertext.
     * @param key           The Private Key (must be an instance of {@link RSAPrivateKey} or {@link ElGamalPrivateKey}).
     * @return The plaintext string.
     * @throws RuntimeException If the key type is not supported or decryption fails.
     */
    public static String decrypt(byte[] encryptedData, PrivateKey key) {
        if (key instanceof RSAPrivateKey) {
            return decryptRSA(encryptedData, (RSAPrivateKey) key);
        } else if (key instanceof ElGamalPrivateKey) {
            return decryptElGamal(encryptedData, (ElGamalPrivateKey) key);
        }
        throw new RuntimeException("Unknown key type");
    }

    /**
     * Reconstructs a Public Key from its byte representation.
     *
     * @param algorithm   The algorithm type (RSA_4096 or EL_GAMAL).
     * @param encodedData The byte array containing the encoded key string.
     * @return A valid {@link PublicKey} object reconstructed from the data.
     */
    public static PublicKey decodePublicKey(Algorithm algorithm, byte[] encodedData) {
        String str = new String(encodedData, StandardCharsets.UTF_8);
        String[] parts = str.split(":");

        if (algorithm == Algorithm.RSA_4096) {
            return new RSAPublicKey(new BigInteger(parts[0]), new BigInteger(parts[1]));
        } else {
            return new ElGamalPublicKey(new BigInteger(parts[0]), new BigInteger(parts[1]), new BigInteger(parts[2]));
        }
    }

    // ------------------------ RSA ------------------------

    /**
     * Generates an RSA key pair.
     * <p>
     * Generates two large random prime numbers to calculate the modulus {@code n}
     * and the private exponent {@code d}. The public exponent {@code e} is fixed at 65537.
     *
     * @param keySize The total bit length of the modulus (e.g., 4096).
     * @return A {@link KeyPair} containing the {@link RSAPublicKey} and {@link RSAPrivateKey}.
     */
    private static KeyPair generateRSAKeyPair(int keySize) {
        SecureRandom random = new SecureRandom();
        //  Generate two large primes p and q (each half the key size)
        BigInteger p = BigInteger.probablePrime(keySize / 2, random);
        BigInteger q = BigInteger.probablePrime(keySize / 2, random);
        
        // Compute n = p * q
        BigInteger n = p.multiply(q);
        
        // Compute phi = (p-1) * (q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        
        // Set public exponent e (65537 is standart)
        BigInteger e = new BigInteger("65537");
        
        // Compute private exponent d = e^(-1) (mod phi)
        BigInteger d = e.modInverse(phi);

        return new KeyPair(new RSAPublicKey(n, e), new RSAPrivateKey(n, d));
    }

    /**
     * Encrypts data using the RSA algorithm.
     *
     * @param data The plaintext message to encrypt.
     * @param key  The custom {@link RSAPublicKey} containing modulus {@code n} and exponent {@code e}.
     * @return A byte array representing the encrypted string.
     * @throws RuntimeException If the length of the message is too large for the specified key size.
     */
    private static byte[] encryptRSA(String data, RSAPublicKey key) {
        BigInteger n = key.getN();
        BigInteger e = key.getE();

        BigInteger m = new BigInteger(1, data.getBytes(StandardCharsets.UTF_8));

        if (m.compareTo(n) >= 0) throw new RuntimeException("Message too long for RSA key size");

        BigInteger c = m.modPow(e, n);

        return c.toString().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Decrypts data using the RSA algorithm.
     *
     * @param data The encrypted byte array (representing the ciphertext).
     * @param key  The custom {@link RSAPrivateKey} containing modulus {@code n} and private exponent {@code d}.
     * @return The original plaintext string.
     */
    private static String decryptRSA(byte[] data, RSAPrivateKey key) {
        BigInteger n = key.getN();
        BigInteger d = key.getD();

        String textNum = new String(data, StandardCharsets.UTF_8);
        BigInteger c = new BigInteger(textNum);

        BigInteger m = c.modPow(d, n);

        return new String(m.toByteArray(), StandardCharsets.UTF_8);
    }


    // ------------------------ EL-GAMAL ------------------------

    /**
     * Generates an ElGamal key pair based on the Discrete Logarithm Problem.
     *
     * @param keySize The bit length of the prime modulus (e.g., 1024).
     * @return A {@link KeyPair} containing the custom {@link ElGamalPublicKey} and {@link ElGamalPrivateKey}.
     */
    private static KeyPair generateElGamalKeyPair(int keySize) {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(keySize, random);
        BigInteger g = new BigInteger(keySize - 1, random);
        BigInteger x = new BigInteger(keySize - 1, random);
        BigInteger y = g.modPow(x, p);

        PublicKey pub = new ElGamalPublicKey(p, g, y);
        PrivateKey priv = new ElGamalPrivateKey(p, x);
        return new KeyPair(pub, priv);
    }

    /**
     * Encrypts data using the ElGamal algorithm.
     *
     * @param data The plaintext message to encrypt.
     * @param key  The custom {@link ElGamalPublicKey} containing parameters p, g, and y.
     * @return A byte array containing the packed string representation "c1|c2".
     * @throws RuntimeException If the length of the message is larger than the prime modulus {@code p}.
     */
    private static byte[] encryptElGamal(String data, ElGamalPublicKey key) {
        BigInteger p = key.getP();
        BigInteger g = key.getG();
        BigInteger y = key.getY();
        
        BigInteger m = new BigInteger(1, data.getBytes(StandardCharsets.UTF_8));
        if (m.compareTo(p) >= 0) throw new RuntimeException("Message too long for ElGamal key.");

        SecureRandom random = new SecureRandom();
        BigInteger k = new BigInteger(p.bitLength() - 1, random);

        BigInteger c1 = g.modPow(k, p);
        BigInteger s = y.modPow(k, p);
        BigInteger c2 = m.multiply(s).mod(p);

        String packed = c1.toString() + "|" + c2.toString();
        return packed.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Decrypts data using the ElGamal algorithm.
     *
     * @param data The encrypted byte array (representing the packed "c1|c2" string).
     * @param key  The custom {@link ElGamalPrivateKey} containing parameters p and x.
     * @return The original plaintext string.
     */
    private static String decryptElGamal(byte[] data, ElGamalPrivateKey key) {
        String packed = new String(data, StandardCharsets.UTF_8);
        String[] parts = packed.split("\\|");
        BigInteger c1 = new BigInteger(parts[0]);
        BigInteger c2 = new BigInteger(parts[1]);

        BigInteger p = key.getP();
        BigInteger x = key.getX();

        BigInteger s = c1.modPow(x, p);
        BigInteger s_inv = s.modInverse(p);
        BigInteger m = c2.multiply(s_inv).mod(p);

        return new String(m.toByteArray(), StandardCharsets.UTF_8);
    }

    // ------------------------ FILE I/O ------------------------
    /**
     * Serializes and saves a cryptographic Key to a file.
     * 
     * @param key      The key object to save.
     * @param filePath The file system path where the key should be stored.
     * @throws RuntimeException If an I/O error occurs while writing to the file.
     */
    public static void saveKey(Key key, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(key);
        } catch (IOException e) {
            throw new RuntimeException("Error saving key: " + e.getMessage());
        }
    }

    /**
     * Deserializes and loads a Public Key from a file.
     *
     * @param filePath The path to the file containing the serialized key.
     * @return The reconsructed {@link PublicKey} object.
     * @throws RuntimeException If the file cannot be read or the object is not a valid PublicKey.
     */
    public static PublicKey loadPublicKey(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (PublicKey) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading public key: " + e.getMessage());
        }
    }

    /**
     * Deserializes and loads a Private Key from a file.
     *
     * @param filePath The path to the file containing the serialized key.
     * @return The reconstructed {@link PublicKey} object.
     * @throws RuntimeException If the file cannot be read or the object is not a valid PublicKey.
     */
    public static PrivateKey loadPrivateKey(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (PrivateKey) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading private key: " + e.getMessage());
        }
    }
}

/**
 * Implementation of the RSA Public Key.
 * <p>
 * Holds the mathematical components of an RSA public key:
 * the modulus ({@code n}) and the public exponent ({@code e}).
 *
 * @author Anna Aleksandrova
 * @version 1.0
 * @since 21.10.2025
 */
class RSAPublicKey implements PublicKey {
    private static final long serialVersionUID = 1L;
    private BigInteger n, e;
    public RSAPublicKey(BigInteger n, BigInteger e) { 
        this.n = n; 
        this.e = e; 
    }
    public BigInteger getN() { return n; }
    public BigInteger getE() { return e; }
    @Override public String getAlgorithm() { return "RSA"; }
    @Override public String getFormat() { return "X.509"; }
    @Override public byte[] getEncoded() { return (n + ":" + e).getBytes(); }
}

/**
 * Implementation of the RSA Private Key.
 * <p>
 * Holds the mathematical components of an RSA private key:
 * the modulus ({@code n}) and the private exponent ({@code d}).
 *
 * @author Anna Aleksandrova
 * @version 1.0
 * @since 21.10.2025
 */
class RSAPrivateKey implements PrivateKey {
    private static final long serialVersionUID = 1L;
    private BigInteger n, d;
    public RSAPrivateKey(BigInteger n, BigInteger d) { 
        this.n = n; 
        this.d = d; 
    }
    public BigInteger getN() { return n; }
    public BigInteger getD() { return d; }
    @Override public String getAlgorithm() { return "RSA"; }
    @Override public String getFormat() { return "PKCS#8"; }
    @Override public byte[] getEncoded() { return (n + ":" + d).getBytes(); }
}

/**
 * Implementation of the ElGamal Public Key.
 *
 * @author Anna Aleksandrova
 * @version 1.0
 * @since 23.10.2025
 */
class ElGamalPublicKey implements PublicKey {
    private static final long serialVersionUID = 1L;
    private BigInteger p, g, y;
    public ElGamalPublicKey(BigInteger p, BigInteger g, BigInteger y) { 
        this.p = p; 
        this.g = g; 
        this.y = y; 
    }
    public BigInteger getP() { return p; }
    public BigInteger getG() { return g; }
    public BigInteger getY() { return y; }
    @Override public String getAlgorithm() { return "ElGamal"; }
    @Override public String getFormat() { return "X.509"; }
    @Override public byte[] getEncoded() { return (p + ":" + g + ":" + y).getBytes(); }
}

/**
 * Implementation of the ElGamal Private Key.
 *
 * @author Anna Aleksandrova
 * @version 1.0
 * @since 23.10.2025
 */
class ElGamalPrivateKey implements PrivateKey {
    private static final long serialVersionUID = 1L;
    private BigInteger p, x;
    public ElGamalPrivateKey(BigInteger p, BigInteger x) { 
        this.p = p; 
        this.x = x; 
    }
    public BigInteger getP() { return p; }
    public BigInteger getX() { return x; }
    @Override public String getAlgorithm() { return "ElGamal"; }
    @Override public String getFormat() { return "PKCS#8"; }
    @Override public byte[] getEncoded() { return (p + ":" + x).getBytes(); }
}
