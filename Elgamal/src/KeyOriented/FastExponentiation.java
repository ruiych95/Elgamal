package KeyOriented;

public class FastExponentiation 
{
    public long modulo(long a,long b,long c) 
    {
        long z;
        long x=1L;
        long y=a;
        while(b > 0)
        {
            if(b%2 == 1)
            {
                x=(x*y)%c;
            }
            y = (y*y)%c; // squaring the base
            b /= 2;
        }
        //return (int) x%c;
        return (x%c);
    }
    
    public long multiplyModulo(long a,long b,long c, long d) 
    {    
        long x=1L;
        long y=a;
        while(b > 0)
        {
            if(b%2 == 1)
            {
                x=(x*y)%c;
            }
            y = (y*y)%c; // squaring the base
            b /= 2;
        }
        //return Math.floorMod((x*d), c);
        //return (((int) x)*d)%c;
        return ((x*d)%c);
    }
}
