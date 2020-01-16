package controller;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Hash {

	static String makeHash(String log, String pHash) {
		
		String hash="";
		int nonce=0;
		Random rn=new Random();
		
		//System.out.println("pHash:"+pHash);
		try {
			do {
				nonce=rn.nextInt();
				MessageDigest digest=MessageDigest.getInstance("SHA-256");
				log=log+pHash+nonce;
				digest.update(log.getBytes());
				byte byteData[]=digest.digest();
				StringBuffer sb=new StringBuffer();
				for(int i=0;i<byteData.length;i++) {
					sb.append(Integer.toString((byteData[i]&0xff)+0x100,16).substring(1));
				}
				hash=sb.toString();
			}while(!(hash.substring(0,2)).equals("00"));
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			hash=null;
		}
		return hash;
	}
}
