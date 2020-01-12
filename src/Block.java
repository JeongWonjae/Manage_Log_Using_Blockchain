import java.util.ArrayList;
import java.util.Date;

public class Block {

	static ArrayList<BlockStructure> createBlock(String ip,String allLogArr[]) {
		
		ArrayList<BlockStructure> bchain=new ArrayList<BlockStructure>();
		
		for(int i=0;i<allLogArr.length;i++) 
		{
			if(allLogArr[i]!=null)
			{
				BlockStructure block=new BlockStructure(ip, allLogArr[i]);
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
	
	BlockStructure(String ip, String log){
		this.ip=ip;
		this.log=log;
		/*
		if(isexist ~~ pHash){
			getreturnString() ~~ gethash
		}else{
			previousHash="first block";
		} 
		 */
		this.allocatePreviousHash=previousHash;
		this.hash=Hash.makeHash(log, allocatePreviousHash);
		previousHash=hash;
		this.timeStamp=new Date().getTime();
		//this.importanceLevel=importanceLevel;
	}
}
//(+)take out 'previousHash' to Database
 