package FileOriented;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmallBinaryFiles 
{
    
    private static String INPUT_FILE_PATH = "";
    private final static String OUTPUT_FILE_PATH = "";
    int Size;
    private int preCheckKey;
    private int fileInteger;
    
    public SmallBinaryFiles(){}
    
    public SmallBinaryFiles(String filePath) throws IOException
    {
        INPUT_FILE_PATH = filePath;    
        byte[] bytes = readSmallBinaryFile(INPUT_FILE_PATH);
        //System.out.println("Small - size of file read in:" + bytes.length);
        preCheckKey = getBitsInteger(bytes);
        System.out.println("30 bits key in decimal : " + preCheckKey);
        Size = (bytes.length-1)*8;
        //System.out.println("File Size"+" = "+" "+Size);
        test(bytes);
    }
    
    public int getPreCheckKey()
    {
        return preCheckKey;
    }
    
    private byte[] readSmallBinaryFile(String aFileName) throws IOException 
    {
        Path path = Paths.get(aFileName);
        return Files.readAllBytes(path);
    }

    private void writeSmallBinaryFile(byte[] aBytes, String aFileName) throws IOException 
    {
        Path path = Paths.get(aFileName);
        Files.write(path, aBytes);
    }
    
    private int getBitsInteger(byte[] bytes)
    {
        String result = "";
        String output = "";
        for(byte b : bytes)
        {
            for(int i = 0; i < 8; i++)
                result += (b & (1 << i)) == 0 ? "0" : "1";
        }
        for(int i=0;i<result.length();i++)
        {
            char c = result.charAt(i);
            if(c == '1')
            {
                output = result.substring(i, 30);
                System.out.println("30 bits key in binary : "+ output);
                break;
            }
        }
        return Integer.parseInt(output, 2);
    }
    
    public void test(byte[] bytes)
    {
        String result = "";
        for(byte b : bytes)
        {
            for(int i = 0; i < 8; i++)
                result += (b & (1 << i)) == 0 ? "0" : "1";
        }
        fileInteger = Integer.parseInt(result, 2);
    }
    
    public int getFileInteger()
    {
        return fileInteger;
    }
}  
