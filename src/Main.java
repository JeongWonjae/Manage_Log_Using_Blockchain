import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String fileName="A://testfile/wtmp.txt"; //need update, input by user
		String localIP="192.168.11.104"; //need update, input by user
		String serverPath="/jsp/storeLog.jsp";
		
		String currentFileHash=ModifyLog.getFileHash(fileName);
		String lastFileHash="";
		String log[]=new String[1024];
		ArrayList<BlockStructure> logchain=new ArrayList<BlockStructure>();
		
		//for testing. remove later//
		String lastLogFileHash="13d87338d5abf7d1fb1fbffee23c6f1a6e4416fd21c6ff0c59ed5dd56122bb";
		
		//compare file hash.
		/*
		if(ConnectionMysql.isExist("select * from fileHash", "fileName", fileName)==true)
		{
			lastFileHash=ModifyLog.lastFileHash(fileName);
		}else
		{
			lastFileHash=ModifyLog.getFileHash(fileName);
		}
		*/
		
		log=OpenLog.wtmp(fileName);
		
		if(!currentFileHash.equals(lastFileHash))
		{
			logchain=Block.createBlock(localIP , log);
			
			for(BlockStructure bk : logchain)
			{
				ForwardPacket.sendServer(bk , localIP, serverPath);
			}
			
			//save file hash
			if(ConnectionMysql.isExist("select * from fileHash", "fileName", fileName)==true)
			{
				ModifyLog.updateFileHash(fileName, currentFileHash);
			}else
			{
				ModifyLog.saveFileHash(fileName, currentFileHash);
			}
		}
	}
}

/*
(�Ϸ�)�����̵� �����ڵ忡 �߰��ϱ�.
(�Ϸ�)�����ͺ��̽��� �α� ���ϸ��� �ؽ� �� ����ؼ� ������Ʈ�ϴ� �ڵ�.
(�Ϸ�)��Ŷ������ �ڵ� �޼ҵ�� ���� ����.
�����̸��̶� ������, ������� ����ڷκ��� �޴°ɷ� ����.
�������� �����ڵ� ��ť���ڵ��ϱ�.
(�Ϸ�)��Ŷ �������� ������ 200�� �ƴϸ� �ٽ� ������
 */


/*
int i=1;
for(BlockStructure lkp : logchain) {
	System.out.println("["+i+"]"+"LOG:"+lkp.log+" Hash:"+lkp.hash+" pHash:"+lkp.allocatePreviousHash+" <time:"+lkp.timeStamp+">");
	i++;
}*/