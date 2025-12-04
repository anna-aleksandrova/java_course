package crypto;

/**
 * Encrypting algorithms
 *
 * @author Anna Aleksandrova
 * @version 2.0
 * @since 17.10.2025
 */
public enum Algorithm {
    
    RSA_4096("RSA", 4096), 
    EL_GAMAL("ElGamal", 1024);

    private final String name;
    private final int keySize;

    Algorithm(String name, int keySize) {
        this.name = name;
        this.keySize = keySize;
    }

    public String getName() { 
        return name; 
    }
    public int getKeySize() { 
        return keySize; 
    }
}
