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
		
		//logchain start
		kindOfLog.wtmpNmessages(logName, localIP);
		kindOfLog.wtmpNmessages(logName2, localIP);
		
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
~����~�α����ϸ��� ��� �߰�.
~����~�α����ϸ��� �޼ҵ� ä���.
�������� �����ڵ� ��ť���ڵ��ϱ�.
GUI ȭ�� �߰�.
 */