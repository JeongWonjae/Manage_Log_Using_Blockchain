import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		//input by user
		Scanner scan=new Scanner(System.in);
		System.out.print("[INFO] Input IP Address : ");
		String localIP = scan.nextLine();
		System.out.print("[INFO] Input Log Name : ");
		String logName=scan.nextLine();
		System.out.println("[INFO] Entered IP Address / Log Name : "+localIP+" / "+logName);
		System.out.println();
		scan.close();
		*/
		String localIP="192.168.11.104";
		String logName="wtmp";
		String logName2="messages";
		String logName3="boot";
		String logName4="auth";
		String logName5="daemon";
		String logName6="user";
		String logName7="dpkg";
		
		//logchain start
		kindOfLog.EnrollLogBlock(logName, localIP);
		kindOfLog.EnrollLogBlock(logName2, localIP);
		kindOfLog.EnrollLogBlock(logName3, localIP);
		kindOfLog.EnrollLogBlock(logName4, localIP);
		kindOfLog.EnrollLogBlock(logName5, localIP);
		kindOfLog.EnrollLogBlock(logName6, localIP);
		kindOfLog.EnrollLogBlock(logName7, localIP);

	}
}

/*
(�Ϸ�)�����̵� �����ڵ忡 �߰��ϱ�.
(�Ϸ�)�����ͺ��̽��� �α� ���ϸ��� �ؽ� �� ����ؼ� ������Ʈ�ϴ� �ڵ�.
(�Ϸ�)��Ŷ������ �ڵ� �޼ҵ�� ���� ����.
(�Ϸ�)�����̸��̶� ������,������� ����ڷκ��� �޴°ɷ� ����.
(�Ϸ�)�α����ϸ��� �޼ҵ� �����
(�Ϸ�)��Ŷ �������� ������ 200�� �ƴϸ� �ٽ� ������
(�Ϸ�)���� �ؽ� ���������� �ڵ� �߰�.
(�Ϸ�)���ڿ� �ɷ����� �ڵ� �߰�.
(�Ϸ�)������ ��Ŷ�� �α� ���� ���� �߰�.
(�Ϸ�)�α� �߰��� �� ���� ��ٸ���
(�Ϸ�)����~�α����ϸ��� ��� �߰�.
(�Ϸ�)�α����ϸ��� �޼ҵ� ä���.
(�Ϸ�)fileName->filePath �� �����ϱ�.
(�Ϸ�)timeStamp���ϴ¹�� ����.
�������� �����ڵ� ��ť���ڵ��ϱ�.
GUI ȭ�� �߰�.
�����ڵ忡�� sendedFlag->sentFlag�� �����ϱ�.
 */