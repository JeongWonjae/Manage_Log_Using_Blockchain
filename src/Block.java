import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Block {

	static ArrayList<BlockStructure> createBlock(String ip,String allLogArr[]) throws SQLException {
		
		ArrayList<BlockStructure> bchain=new ArrayList<BlockStructure>();
		
		for(int i=0;i<allLogArr.length;i++) 
		{
			if(allLogArr[i]!=null)
			{
				BlockStructure block=new BlockStructure(ip, allLogArr[i], i);
				bchain.add(block);
			}
		}
		return bchain;
	}
}

class BlockStructure{
	
	String ip;
	String log;
	String hash;
	String allocatePreviousHash;
	long timeStamp;
	int importanceLevel;
	static String previousHash="first block";
	
	BlockStructure(String ip, String log, int i) throws SQLException{
		this.ip=ip;
		this.log=log;
		
		if(ConnectionMysql.isExist("select * from logchain", "hash", null)==true && i==0)
		{
			previousHash=ConnectionMysql.getLastPreviousHash();
		}else
		{
		}
		
		this.allocatePreviousHash=previousHash;
		this.hash=Hash.makeHash(log, allocatePreviousHash);
		previousHash=hash;
		this.timeStamp=new Date().getTime();
		//this.importanceLevel=importanceLevel;
	}
}
//(+)take out 'previousHash' to Database
 