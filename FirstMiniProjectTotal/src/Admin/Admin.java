package Admin;

import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
	public static void adminMain() throws SQLException {
		CoIncome ci = new CoIncome();
		Scanner s = new Scanner(System.in);

		System.out.println("�ȳ��ϼ��� �����ڴ�");
		while(true) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("� �޴��� �̿��Ͻðڽ��ϱ�?");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. ���� ���� | 2. ��� ���� | 3. ��� �ֹ� | 0. �α׾ƿ�");
			int key = s.nextInt();
			try {
				switch (key) {
				case 1:
					System.out.println("���� ���� �������Դϴ�.");
					System.out.println("-----------------------------------------------------------");
					ci.income();

					break;

				case 2:
					System.out.println("��� ���� �������Դϴ�.");
					stockManage.stockMan();
					break;

				case 3:
					System.out.println("��� �ֹ� �������Դϴ�.");
					OrderStock.orderStock();
					break;
					
				case 0:
					System.out.println("�̿����ּż� �����մϴ�.");
					System.out.println("�α׾ƿ� �մϴ�.");
					return;

				default:
					System.out.println("�� �� �Է��Ͽ����ϴ�.");
					System.out.println("ó�� ȭ������ ���ư��ϴ�.");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
}
