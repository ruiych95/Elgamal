package KeyOriented;

public class GCD 
{
    public int GCD(int a, int b) 
    {
        if (b==0) return a;
        else return GCD(b,a%b);
    }
}
