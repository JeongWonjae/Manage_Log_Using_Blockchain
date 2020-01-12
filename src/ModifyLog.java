import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
	
	static String lastFileHash(String fileName) throws SQLException { //need update
		
		String fileHash="";
		String query="";
		query="select hash from filehash where fileName='"+fileName+"'";
		fileHash=ConnectionMysql.queryReturnString(query, "hash");
		
		return fileHash;
	}
	
	static void saveFileHash(String fileName, String fileHash) throws SQLException {
		ConnectionMysql.insertIntoFileHash(fileName, fileHash);
	}
	
	static void updateFileHash(String fileName, String fileHash) throws SQLException{
		ConnectionMysql.updateSetFileHash(fileName, fileHash);
	}
	
}