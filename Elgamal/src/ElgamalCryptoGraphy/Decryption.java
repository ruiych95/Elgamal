package ElgamalCryptoGraphy;

import KeyOriented.FastExponentiation;
import java.util.HashMap;

public class Decryption 
{
    public long decrypt(long a, long b, int prime, int secretKey)
    {
        FastExponentiation fastExponentiation = new FastExponentiation();
        long plainText = fastExponentiation.encryptModulo(a, (prime-1-secretKey), prime, b);
        return plainText;
    }
}
