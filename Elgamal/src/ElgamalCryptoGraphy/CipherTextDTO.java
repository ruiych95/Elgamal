package ElgamalCryptoGraphy;

import java.util.HashMap;
import java.util.List;

public class CipherTextDTO 
{
    HashMap<Long, List<Long>> cipherText = new HashMap<>();

    public HashMap<Long, List<Long>> getCipherText() {
        return cipherText;
    }

    public void setCipherText(HashMap<Long, List<Long>> cipherText2) {
        this.cipherText = cipherText2;
    }
    
    public void setCipherTextKey(int a)
    {
        
    }
    
    public void setCipherTextValue()
    {
        
    }
    
    /*public int getCipherTextKey()
    {
    return
    }
    
    public List<Integer> getCipherTextValue()
    {
    return
    }*/
}
