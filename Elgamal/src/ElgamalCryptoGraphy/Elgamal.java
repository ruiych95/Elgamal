package ElgamalCryptoGraphy;

import FileOriented.LargeBinaryFiles;
import FileOriented.SmallBinaryFiles;
import KeyOriented.FastExponentiation;
import KeyOriented.KeyGeneration;
import KeyOriented.PrimeChecking;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Elgamal 
{
    public static void main(String[] args) throws IOException, Exception 
    {
        String filePath = "C:\\Test\\dog.jpg";
        LargeBinaryFiles largeBinary = new LargeBinaryFiles();
        byte[] bytes = largeBinary.readToArray(filePath);
        PrimeChecking primeChecking = new PrimeChecking();
        int preCheckKey = largeBinary.get30BitsKeyInteger(bytes);
        List<Integer> fileInteger = largeBinary.getFileBlocks();
        while(!primeChecking.lehManTest(preCheckKey))
        {
            preCheckKey += 1;
        }
        KeyGeneration keyGeneration = new KeyGeneration();
        keyGeneration.secretKey(preCheckKey);
        int secretKey = keyGeneration.getSecretKey();
        keyGeneration.publicKey(preCheckKey, secretKey);
        int[] publicKey = keyGeneration.getPublicKey();
        System.out.println("Public key is : " + publicKey[0] + ", " + publicKey[1] + ", " + publicKey[2]);
        System.out.println("Secret key is : " + secretKey);
        
        /*Encryption*/
        Encryption encryption = new Encryption();
        List<Long> bValues = new ArrayList<>();
        long aValues = 0;
        HashMap<Long, List<Long>> cipherTextHM = new HashMap<>();
        int logRound = 1;
        for(int file : fileInteger)
        {
            long[] cipherTextBlock = encryption.encrypt(publicKey[0], publicKey[1], publicKey[2], file);
            System.out.println("Put hashmap round " + logRound);
            System.out.println("a value : " + cipherTextBlock[0] + " b value : " + cipherTextBlock[1]);
            bValues.add(cipherTextBlock[1]);
            aValues = cipherTextBlock[0];
            logRound++;
        }
        cipherTextHM.put(aValues, bValues);
        CipherTextDTO cipherTextDTO = new CipherTextDTO();
        cipherTextDTO.setCipherText(cipherTextHM);
        List<Long> bValv = cipherTextHM.get(aValues);
        for(long test : bValv)
        {
            System.out.println("a value : "+cipherTextHM.keySet().toArray()[0]+" b value : " + test);
        }
        
        /*Decryption*/
        List<Long> plainText = new ArrayList<>();
        Decryption decryption = new Decryption();
        HashMap<Long, List<Long>> plainTextHM = new HashMap<>();
        plainTextHM = cipherTextDTO.getCipherText();
        for(long test : bValv)
        {
            plainText.add(decryption.decrypt((long)plainTextHM.keySet().toArray()[0], test, publicKey[0], secretKey));
            //System.out.println("a value : "+cipherTextHM.keySet().toArray()[0]+" b value : " + test);
        }
        int logRound2 = 1;
        String plainTextString = "";
        for(long test : plainText)
        {
            String binaryString = Long.toBinaryString(test);
            plainTextString += binaryString;
            System.out.println("File block "+logRound2+"  Base10 value : " + test +" Binary : "+ binaryString);
            logRound2++;
        }
        
    }
    
}
