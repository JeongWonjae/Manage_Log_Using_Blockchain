import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//input by user
		Scanner scan=new Scanner(System.in);
		System.out.print("[INFO] Input IP Address : ");
		String localIP = scan.nextLine();
		System.out.print("[INFO] Input Log Name : ");
		String logName=scan.nextLine();
		System.out.println("[INFO] Entered IP Address / Log Name : "+localIP+" / "+logName);
		System.out.println();
		
		kindOfLog.wtmp(logName, localIP);
		
		//need scan close
	}
}

/*
(�Ϸ�)�����̵� �����ڵ忡 �߰��ϱ�.
(�Ϸ�)�����ͺ��̽��� �α� ���ϸ��� �ؽ� �� ����ؼ� ������Ʈ�ϴ� �ڵ�.
(�Ϸ�)��Ŷ������ �ڵ� �޼ҵ�� ���� ����.
(�Ϸ�)�����̸��̶� ������, ������� ����ڷκ��� �޴°ɷ� ����.
(�Ϸ�)�α����ϸ��� �޼ҵ� �����
(�Ϸ�)��Ŷ �������� ������ 200�� �ƴϸ� �ٽ� ������
�α����ϸ��� �޼ҵ� ä���.
�������� �����ڵ� ��ť���ڵ��ϱ�.
���� �ؽ� ���������� �ڵ� �߰�.
GUI ȭ�� �߰�.
 */