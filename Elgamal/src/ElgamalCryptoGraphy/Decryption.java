package ElgamalCryptoGraphy;

import KeyOriented.FastExponentiation;

public class Decryption 
{
    private static FastExponentiation fastExponentiation = null;
    
    public long decrypt(long a, long b, int prime, int secretKey)
    {
        if(fastExponentiation == null)
        {
            fastExponentiation = new FastExponentiation();
        }
        
        return fastExponentiation.multiplyModulo(a, prime-1-secretKey, prime, b);
    }
}
