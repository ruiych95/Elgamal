package KeyOriented;

import java.util.Random;

public class PrimeChecking 
{
    public boolean lehManTest(int oddNumber)
    {
        FastExponentiation fastExponentiation = new FastExponentiation();
        if(oddNumber%2==0)
            return false;
        int randomNumInOddSet = new Random().nextInt(((oddNumber-1) - 2) + 1) + 2;
        //System.out.println("Randomed Number is"+" "+ randomNumInOddSet);
        GCD gcd = new GCD();
        if(gcd.GCD(randomNumInOddSet, oddNumber)!=1)
        {
            //System.out.println("GCD checked "+oddNumber+" is not a prime");
            return false;
        }
        else
        {
            int e = (oddNumber - 1 )/2;
            //int result = ((int)Math.pow(randomNumInOddSet,e)%oddNumber);
            long result = fastExponentiation.modulo(randomNumInOddSet, e, oddNumber);
            //System.out.println("result : "+result);
            if(result == 1 || result == -1 || result == oddNumber-1)
            {
                //System.out.println(oddNumber+" is a prime");
                return true;
            }
            else 
            {
                //System.out.println(oddNumber+" is not a prime");
                return false;
            }
        }
    }
}
