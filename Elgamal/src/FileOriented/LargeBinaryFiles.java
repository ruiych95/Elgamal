package FileOriented;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LargeBinaryFiles 
{
    private String fileDataBinary = "";
    
    public byte[] readToArray(String filePath) throws Exception 
    {
        InputStream inputStream = null;
        byte[] outputBytes = new byte[0];
        try
        {
            inputStream = new BufferedInputStream(new FileInputStream(filePath));
            // the length of a buffer can vary
            int bufferLength = 20000*1024;
            byte[] buffer = new byte[bufferLength];
            byte[] tmp = null;
            int length    = 0;
            while((length = inputStream.read(buffer,0,bufferLength)) != -1)
            {
                 // extend array
                 tmp = new byte[outputBytes.length + length];
                 // copy data
                 System.arraycopy(outputBytes,0,tmp,0,outputBytes.length);
                 System.arraycopy(buffer,0,tmp,outputBytes.length,length);
                 outputBytes = tmp;
                 tmp = null;          
            }
        }
        finally
        {
            // always close the stream
            if (inputStream != null) try{ inputStream.close();}catch (Exception e){}
        }
        return outputBytes;
    }
     
    public int get30BitsKeyInteger(byte[] bytes)
    {
        String result = "";
        String output = "";
        for(byte b : bytes)
        {
            /*for(int i = 0; i < 8; i++)
            result += (b & (1 << i)) == 0 ? "0" : "1";*/
            result += toBinaryString(b);
        }
        fileDataBinary = result;
        //System.out.println("Result : " + result);
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
    
    public String toBinaryString(byte b) 
    {
        StringBuilder stringBuilder = new StringBuilder("00000000");
        for (int bit = 0; bit < 8; bit++) 
        {
            if (((b >> bit) & 1) > 0) 
            {
                stringBuilder.setCharAt(7 - bit, '1');
            }
        }
        return stringBuilder.toString();
    }
    
    public List<Integer> getFileBlocks()
    {
        String padding = "";
        List<Integer> fileBlock = new ArrayList<>();
        int logRound = 1;
        for(int index = 0; index < fileDataBinary.length(); index += 30)
        {
            try
            {
               fileBlock.add(Integer.parseInt(fileDataBinary.substring(index, index+30), 2));
               System.out.println("File block " + logRound + " : " + fileDataBinary.substring(index, index+30) + "  Base10 : " + Integer.parseInt(fileDataBinary.substring(index, index+30), 2));
            }
            catch(IndexOutOfBoundsException e)//padding
            {
                int paddingAmount = (index+30) - fileDataBinary.length();
                for(int paddingIndex = 0; paddingIndex < paddingAmount; paddingIndex++)
                {
                    padding += "0";
                }
                fileBlock.add(Integer.parseInt(fileDataBinary.substring(index, fileDataBinary.length()) + padding, 2));
                System.out.println("Last block " + logRound + " : " + fileDataBinary.substring(index, fileDataBinary.length()) + padding + " (Padding : "+padding+")"+ "  Base10 : " + Integer.parseInt(fileDataBinary.substring(index, fileDataBinary.length()) + padding, 2));
            }
            logRound++;
        }
        return fileBlock;
    }

}
