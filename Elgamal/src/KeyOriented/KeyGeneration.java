package KeyOriented;

import java.util.Random;

public class KeyGeneration 
{
    private int secretKey;
    private int[] publicKey;
    
    public void publicKey(int prime, int u)
    {
        publicKey = new int[3];
        int exponentiated = (prime - 1)/2;
        int alpha = new Random().nextInt((prime - 2) + 1) + 2;
        while(((long)Math.pow(alpha, exponentiated)%prime) == 1)
        {
            alpha = new Random().nextInt((prime - 2) + 1) + 2;
        }
        //System.out.println("Publickey g value checking must not equal 1 : " + ((int)Math.pow(alpha, exponentiated)%prime));
        int y = new FastExponentiation().modulo(alpha, u, prime);
        publicKey[0] = prime;
        publicKey[1] = alpha;
        publicKey[2] = y;
    }
    
    public void secretKey(int prime)
    {
        secretKey = new Random().nextInt((prime - 1) + 1) + 1;
    }

    public int getSecretKey() {
        return secretKey;
    }

    public int[] getPublicKey() {
        return publicKey;
    }
    
}
