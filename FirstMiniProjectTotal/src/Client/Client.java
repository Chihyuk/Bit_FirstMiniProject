package Client;

import java.sql.SQLException;
import java.util.Scanner;

public class Client {
	public static void clientMain() throws SQLException {
		Order mainOrder = new Order();
		Search mainSerch = new Search();
		Cart mainCart = new Cart();
		
		Scanner s = new Scanner(System.in);
		while(true) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("� �޴��� �̿��Ͻðڽ��ϱ�?");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. ��ǰ �˻� | 2. ��ǰ �ֹ� | 3. ��ٱ��� | 0. �α׾ƿ�");
			int key = s.nextInt();
			try {
				switch (key) {
				case 1:
					System.out.println("��ǰ �˻� �������Դϴ�.");
					System.out.println("-----------------------------------------------------------");

					mainSerch.search();
					
					break;

				case 2:
					System.out.println("��ǰ �ֹ� �������Դϴ�.");
					System.out.println("-----------------------------------------------------------");
					
					mainOrder.order();
					break;
					
				case 3:
					mainCart.cart();
					
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
