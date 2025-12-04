package model;

import crypto.Algorithm;
import java.io.Serializable;
import java.security.PublicKey;

/**
 * Data model for a User.
 * Stores information the server knows about a client.
 *
 * @author Anna Aleksandrova
 * @version 1.1
 * @since 2025-10-15
 */
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L; 

    private String username;
    private PublicKey publicKey;
    private Algorithm algorithm; 

    /**
     * User constructor.
     * @param username The login name.
     * @param publicKey The public key.
     * @param algorithm The algorithm chosen by the user.
     */
    public User(String username, PublicKey publicKey, Algorithm algorithm) {
        this.username = username;
        this.publicKey = publicKey;
        this.algorithm = algorithm;
    }

    public String getUsername() { return username; }
    public PublicKey getPublicKey() { return publicKey; }
    public Algorithm getAlgorithm() { return algorithm; }
}
