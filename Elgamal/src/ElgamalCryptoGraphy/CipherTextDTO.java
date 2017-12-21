package ElgamalCryptoGraphy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class CipherTextDTO implements Serializable
{
    HashMap<Long, List<Long>> cipherText = new HashMap<>();

    public HashMap<Long, List<Long>> getCipherText() {
        return cipherText;
    }

    public void setCipherText(HashMap<Long, List<Long>> cipherText2) {
        this.cipherText = cipherText2;
    }
}
