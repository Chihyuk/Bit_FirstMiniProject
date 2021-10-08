package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import LoginMain.Connect;

public class stockManage {
	static void stockMan() throws SQLException {
		try {

			while(true) {
				Connection con = Connect.makeConnection();
				Scanner s= new Scanner(System.in);

				Statement stmt = con.createStatement();
				String sql = "select * FROM inventory join items using(item_num)";
				ResultSet rs_sto_info_select = stmt.executeQuery(sql);
				System.out.println("� �޴��� �̿��Ͻðڽ��ϱ�?");
				System.out.println("1. ��ǰ��ȣ�� ��� Ȯ�� | 2. ��ü ��� Ȯ�� | 3. ��ǰ��ȣ�� ��� Ȯ�� | 4. ��ǰ������ ��� Ȯ�� | 5. �ֹ��� �ʿ��� ��� Ȯ�� | 0. �ʱ�ȭ������ �̵�");
				int key = s.nextInt();
				switch (key) {

				case 1:
					System.out.println("��ǰ ��ȣ�� ��� ��µ˴ϴ�.");

					while (rs_sto_info_select.next()) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("��ǰ��ȣ : "+ rs_sto_info_select.getString("item_NUM") + "\t");
						System.out.println("������ : " + rs_sto_info_select.getString("ITEM_QTY") + "\t");
					}


					System.out.println("�� �˻��Ͻðڽ��ϱ�? (yes / no)");
					String ans1 = s.next();
					if(ans1.equals("yes") || ans1.equals("YES")|| ans1.equals("y") || ans1.equals("Y")) {
						break;

					} else {
						System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
						return;
					}

				case 2:
					System.out.println("��ü ��ǰ�� �������մϴ�.");
					while(rs_sto_info_select.next()) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("��ǰ��ȣ : "+ rs_sto_info_select.getString("item_NUM") + "\t");
						System.out.println("������ : " + rs_sto_info_select.getString("ITEM_QTY") + "\t");
						System.out.println("���� : " + rs_sto_info_select.getString("TITLE") + "\t");
						System.out.println("��Ƽ��Ʈ : " + rs_sto_info_select.getString("ARTIST") + "\t");
						System.out.println("���ǻ� : " + rs_sto_info_select.getString("PUBlisher") + "\t");
						System.out.println("�帣 : " + rs_sto_info_select.getString("genre") + "\t");
						System.out.println("���� : " + rs_sto_info_select.getString("PRICE") + "\t");
					}

					System.out.println("�� �˻��Ͻðڽ��ϱ�? (yes / no)");
					String ans2 = s.next();
					if(ans2.equals("yes") || ans2.equals("YES")|| ans2.equals("y") || ans2.equals("Y")) {
						break;

					} else {
						System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
						return;
					}


				case 3:
					System.out.println("��� Ȯ���ϰ� ���� ��ǰ�� ��ȣ�� �Է����ּ���");
					int mal_num = s.nextInt();
					while (rs_sto_info_select.next()) {
						if(mal_num == rs_sto_info_select.getInt("item_num")) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("��ǰ��ȣ : "+ rs_sto_info_select.getString("item_NUM") + "\t");
							System.out.println("������ : " + rs_sto_info_select.getString("ITEM_QTY") + "\t");
							System.out.println("���� : " + rs_sto_info_select.getString("TITLE") + "\t");
							System.out.println("��Ƽ��Ʈ : " + rs_sto_info_select.getString("ARTIST") + "\t");
							System.out.println("���ǻ� : " + rs_sto_info_select.getString("PUBlisher") + "\t");
							System.out.println("�帣 : " + rs_sto_info_select.getString("genre") + "\t");
							System.out.println("���� : " + rs_sto_info_select.getString("PRICE") + "\t");
						}
					}
					System.out.println("�� �˻��Ͻðڽ��ϱ�? (yes / no)");
					String ans3 = s.next();
					if(ans3.equals("yes") || ans3.equals("YES")|| ans3.equals("y") || ans3.equals("Y")) {
						break;

					} else {
						System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
						return;
					}

				case 4:
					System.out.println("��� Ȯ���ϰ� ���� ��ǰ�� �̸� �Է����ּ���");
					s.nextLine();
					String find_title = s.nextLine().toLowerCase().replaceAll(" ", "");
					//System.out.println(find_title);
					while (rs_sto_info_select.next()) {
						String gs = rs_sto_info_select.getString("TITLE").toLowerCase().replaceAll(" ", "");
						if(find_title.equals(gs)) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("��ǰ��ȣ : "+ rs_sto_info_select.getString("item_NUM") + "\t");
							System.out.println("������ : " + rs_sto_info_select.getString("ITEM_QTY") + "\t");
							System.out.println("���� : " + rs_sto_info_select.getString("TITLE") + "\t");
							System.out.println("��Ƽ��Ʈ : " + rs_sto_info_select.getString("ARTIST") + "\t");
							System.out.println("���ǻ� : " + rs_sto_info_select.getString("PUBlisher") + "\t");
							System.out.println("�帣 : " + rs_sto_info_select.getString("genre") + "\t");
							System.out.println("���� : " + rs_sto_info_select.getString("PRICE") + "\t");
						}
					}


					System.out.println("�� �˻��Ͻðڽ��ϱ�? (yes / no)");
					String ans4 = s.next();
					if(ans4.equals("yes") || ans4.equals("YES")|| ans4.equals("y") || ans4.equals("Y")) {
						break;

					} else {
						System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
						return;
					}

				case 5:
					System.out.println("�� �� ������ ����� ��ǰ�� Ȯ���ұ��?");
					int st = s.nextInt();
					while (rs_sto_info_select.next()) {
						int iqt = rs_sto_info_select.getInt("item_qty");
						if(st >= iqt) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("��ǰ��ȣ : "+ rs_sto_info_select.getString("item_NUM") + "\t");
							System.out.println("������ : " + rs_sto_info_select.getString("ITEM_QTY") + "\t");
							System.out.println("���� : " + rs_sto_info_select.getString("TITLE") + "\t");
						}
					}

				case 0:
					System.out.println("������ �ʱ� �������� �̵��մϴ�");
					return;

				default:
					System.out.println("�߸��� ���� �Է��Ͽ����ϴ�.");
					System.out.println("ó�� ȭ������ ���ư��ϴ�.");
					break;
				}
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}

	}
}
