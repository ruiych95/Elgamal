package FileOriented;

import ElgamalCryptoGraphy.CipherTextDTO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectRW 
{
    public CipherTextDTO fileReader(String filePath)
    {
        CipherTextDTO cipherTextDTO = new CipherTextDTO();
        try
        {
            FileInputStream fis = new FileInputStream(filePath+"\\cipher.elgamal");
            ObjectInputStream ois = new ObjectInputStream(fis);
            CipherTextDTO result = (CipherTextDTO) ois.readObject();
            ois.close();
        }
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
	} 
        catch (IOException e) 
        {
            e.printStackTrace();
	} 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
	}
        return cipherTextDTO;
    }
    
    public void fileWriter(CipherTextDTO cipherTextDTO, String filePath)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(filePath+"\\cipher.elgamal");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cipherTextDTO);
            oos.close();
        }
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
	} 
        catch (IOException e) 
        {
            e.printStackTrace();
	}
    }
}
