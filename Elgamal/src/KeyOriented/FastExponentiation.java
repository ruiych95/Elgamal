package KeyOriented;

public class FastExponentiation 
{
    public int modulo(int a,int b,int c) 
    {
        long x=1;
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
        return (int)x%c;
    }
    
    public long encryptModulo(long a,long b,long c, long d) 
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
        //return Math.floorMod((((int) x)*d), c);
        //return (((int) x)*d)%c;
        return (x*d)%c;
    }
    
    
    /*long x = 1073102903l*1073102840l;
    System.out.println("X : " + x);
    int a = 1073102903;
    int b = 1073102840;
    String aLong = a+"";
    String bLong = b+"";
    long asdf = Long.parseUnsignedLong(aLong);
    long ghjk = Long.parseUnsignedLong(bLong);
    long y = asdf*ghjk;
    System.out.println("Y : " + y);
    long z = a*b;
    System.out.println("Z : " + z);*/
        
        
}
