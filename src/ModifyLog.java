import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class ModifyLog {

	static String getFileHash(String fileName) throws IOException{
		
		InputStream is=null;
		String fileHash="";
		File target=new File(fileName);
		
		try
		{
			MessageDigest digest=MessageDigest.getInstance("SHA-256");
			is=new FileInputStream(target);
			byte[] buffer=new byte[1024];
			int readBytes=0;
			
			while((readBytes=is.read(buffer))>-1)
			{
				digest.update(buffer, 0, readBytes);
			}
			
			StringBuilder builder=new StringBuilder();
			byte[] dig=digest.digest();
			
			for(byte i:dig)
			{
				builder.append(Integer.toHexString(0xff &i));
			}
			fileHash=builder.toString();
			
		} catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		} finally
		{
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return fileHash;
	}
	
	static String openFileHash(String fileName) {
		
		String fileHash="";
		
		return fileHash;
	}
	
	static String[] wtmp(String[] allLogArr) {
			
			String[] log=new String[1024];
			
			
			return log;
	}
}