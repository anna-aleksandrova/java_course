package test;

import crypto.Algorithm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Algorithm enum.
 * Verifies that algorithm constants return correct parameters.
 *
 * @author Anna Aleksandrova
 * @version 1.0
 * @since 06.11.2025
 */
public class AlgorithmTest {

    @Test
    void testAlgorithmProperties() {
        Algorithm rsa = Algorithm.RSA_4096;
        assertEquals("RSA", rsa.getName());
        assertEquals(4096, rsa.getKeySize());

        Algorithm elGamal = Algorithm.EL_GAMAL;
        assertEquals("ElGamal", elGamal.getName());
        assertEquals(1024, elGamal.getKeySize());
    }

    @Test
    void testValueOf() {
        Algorithm algo = Algorithm.valueOf("EL_GAMAL");
        assertEquals(Algorithm.EL_GAMAL, algo);
        
        algo = Algorithm.valueOf("RSA_4096");
        assertEquals(Algorithm.RSA_4096, algo);
    }
}
