package ElgamalCryptoGraphy;

import FileOriented.LargeBinaryFiles;
import KeyOriented.KeyGeneration;
import KeyOriented.PrimeChecking;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Elgamal 
{
    public static void main(String[] args) throws IOException, Exception 
    {
        /*File read*/
        String filePath = "C:\\Test\\test11.exe";
        LargeBinaryFiles largeBinary = new LargeBinaryFiles();
        byte[] inputByteArray = largeBinary.readToArray(filePath);
        /*for(byte b : inputByteArray)
        {
            System.out.println("File origal byte : " + b);
        }*/
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        /*Key generation*/
        int primeInteger = largeBinary.get30BitsInteger(inputByteArray);
        List<Integer> plainText30BitsBlocks = largeBinary.getFileBlocks();
        
        for(int block : plainText30BitsBlocks)
        {
            if(block>primeInteger)
            primeInteger = block;
        }
        
        PrimeChecking primeChecking = new PrimeChecking();
        while(!primeChecking.lehManTest(primeInteger))
        {
            primeInteger += 1;
        }
        KeyGeneration keyGeneration = new KeyGeneration();
        keyGeneration.secretKey(primeInteger);
        int secretKey = keyGeneration.getSecretKey();
        keyGeneration.publicKey(primeInteger, secretKey);
        int[] publicKey = keyGeneration.getPublicKey();
        //System.out.println("Public key is : " + publicKey[0]/*p*/ + ", " + publicKey[1]/*g*/ + ", " + publicKey[2]/*y*/);
        //System.out.println("Secret key is : " + secretKey/*u*/);
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        /*Encryption*/
        Encryption encryption = new Encryption();
        long cipherA = 0;
        List<Long> cipherB = new ArrayList<>();
        HashMap<Long, List<Long>> cipherTextHM = new HashMap<>();
        long[] cipherTextBlock;
        int logRound = 0;
        for(int block : plainText30BitsBlocks)
        {
            cipherTextBlock = encryption.encrypt(publicKey[0], publicKey[1], publicKey[2], block);
            cipherA = cipherTextBlock[0]/*a*/;
            cipherB.add(cipherTextBlock[1]/*b*/);
            /*if(block>1073102840)
            {
            System.out.println("block : "+block);
            System.out.println("a : "+cipherTextBlock[0]);
            System.out.println("b : "+cipherTextBlock[1]);
            }*/
            //System.out.println("Ciphertext block : "+logRound+" a value : "+cipherTextBlock[0]+" b value : "+cipherTextBlock[1]);
            logRound++;
        }
        cipherTextHM.put(cipherA, cipherB);
        CipherTextDTO cipherTextDTO = new CipherTextDTO();
        cipherTextDTO.setCipherText(cipherTextHM);
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        /*Decryption*/
        Decryption decryption = new Decryption();
        List<Long> plainText = new ArrayList<>();
        HashMap<Long, List<Long>> plainTextHM = cipherTextDTO.getCipherText();
        List<Long> bValuesDecrypt = plainTextHM.get(cipherA);
        
        for(long b : bValuesDecrypt)
        {
            plainText.add(decryption.decrypt((long)plainTextHM.keySet().toArray()[0], b, publicKey[0], secretKey));
            //System.out.println("a value : "+cipherTextHM.keySet().toArray()[0]+" b value : " + b);
        }
        int logRound2 = 0;
        String plainTextString = "";
        for(long plain : plainText)
        {
            plainTextString += String.format("%30s", Long.toBinaryString(plain)).replace(' ', '0');
            //System.out.println("File block "+logRound2+" : "+String.format("%30s", Long.toBinaryString(plain)).replace(' ', '0')+"  Base10 : " + plain);
            logRound2++;
        }
        /*System.out.println("Base10");
        for(int i = 0; i<plainText.size(); i++)
        {
        if((plainText30BitsBlocks.get(i)^plainText.get(i))!=0)
        {
        System.out.println(plainText30BitsBlocks.get(i)+" "+plainText.get(i));
        }
        }
        System.out.println();*/
        
        StringBuilder stringBuilder = new StringBuilder(plainTextString);
        stringBuilder.delete(plainTextString.length()-largeBinary.getPaddingAmount(), plainTextString.length());
        byte[] outputByteArray = new byte[inputByteArray.length];
        int outputIndex = 0;
        for(int subStringIndex = 0; subStringIndex <  stringBuilder.length(); subStringIndex += 8)
        {
            outputByteArray[outputIndex] = (byte) Integer.parseInt(stringBuilder.substring(subStringIndex, subStringIndex+8), 2);
            outputIndex++;
        }
        
        for(int i = 0; i<outputByteArray.length; i++)
        {
            if((inputByteArray[i]^outputByteArray[i])!=0)
            {
                System.out.println(i+" "+inputByteArray[i] + " " + outputByteArray[i]);
            }
        }
        
        Path path = Paths.get("C:\\Test\\xxx.exe");
        Files.write(path, outputByteArray);
    }
}
