package ElgamalCryptoGraphy;

import KeyOriented.FastExponentiation;
import KeyOriented.GCD;
import java.util.Random;

public class Encryption 
{
    public static int k = 0;
    public long[] encrypt(int prime, int g, int y, int input)
    {
        FastExponentiation fastExponentiation = new FastExponentiation();
        if(k==0)
        {
            k = new Random().nextInt((prime - 1) + 1) + 1;
            //System.out.println("K before while : "+k);
            GCD gcd = new GCD();
            while(gcd.GCD(k, prime - 1) != 1)
            {
                k = new Random().nextInt((prime - 1) + 1) + 1;
            }
        }
        System.out.println("K : " + k);
        //System.out.println("K checking must be 1 : " + gcd.GCD(k, prime - 1));
        //System.out.println("K after while : "+k);
        long a = fastExponentiation.modulo(g, k, prime);
        long b = fastExponentiation.encryptModulo(y, k, prime, input);
        long[] cipherText = new long[]{a, b};
        return cipherText;
    }
}