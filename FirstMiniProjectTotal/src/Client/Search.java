package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import LoginMain.Connect;

public class Search {
	public static void search() throws SQLException {
		// ��ǰ��ȣ �˻�
		Connection con = Connect.makeConnection();
		Scanner s = new Scanner(System.in);
		boolean tf = true;

		while(tf) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("������ ����ұ��?");
			System.out.println("1. ��ü��� | 2. ��ǰ��ȣ �˻� | 3. ��ǰ ���� �˻� | 4. ����/���� �˻� | 5. ���ǻ�/�Ҽӻ� �˻� | 6. �帣 �˻� | 0. �ʱ�ȭ��");
			int want = s.nextInt();
			System.out.println("-----------------------------------------------------------");

			// ��ü ���̺��� ������ �Է�
			String sql = "SELECT * FROM items";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			try {
				switch (want) {
				case 1:
					System.out.println("��ü�� ����մϴ�");

					while (rs.next()) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("��ǰ��ȣ : "+ rs.getString("item_NUM") + "\t");
						System.out.println("���� : " + rs.getString("TITLE") + "\t");
						System.out.println("��Ƽ��Ʈ : " + rs.getString("ARTIST") + "\t");
						System.out.println("���ǻ� : " + rs.getString("PUBlisher") + "\t");
						System.out.println("�帣 : " + rs.getString("genre") + "\t");
						System.out.println("���� : " + rs.getString("PRICE") + "\t");
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
					System.out.println("�˻��� ��ǰ��ȣ�� �Է����ּ���");
					int find_mal_num = s.nextInt();

					while (rs.next()) {
						if(find_mal_num == rs.getInt("item_num")) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("��ǰ��ȣ : "+ rs.getString("item_NUM") + "\t");
							System.out.println("���� : " + rs.getString("TITLE") + "\t");
							System.out.println("��Ƽ��Ʈ : " + rs.getString("ARTIST") + "\t");
							System.out.println("���ǻ� : " + rs.getString("PUBlisher") + "\t");
							System.out.println("�帣 : " + rs.getString("genre") + "\t");
							System.out.println("���� : " + rs.getString("PRICE") + "\t");
						}
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
					s.nextLine();
					System.out.println("�˻��� ������ �Է����ּ���");
					String find_title = s.nextLine().toLowerCase().replaceAll(" ", "");

					while (rs.next()) {
						String gst = rs.getString("TITLE").toLowerCase().replaceAll(" ", "");
						if(find_title.equals(gst)) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("��ǰ��ȣ : "+ rs.getString("item_NUM") + "\t");
							System.out.println("���� : " + rs.getString("TITLE") + "\t");
							System.out.println("��Ƽ��Ʈ : " + rs.getString("ARTIST") + "\t");
							System.out.println("���ǻ� : " + rs.getString("PUBlisher") + "\t");
							System.out.println("�帣 : " + rs.getString("genre") + "\t");
							System.out.println("���� : " + rs.getString("PRICE") + "\t");
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
					s.nextLine();
					System.out.println("�˻��� ����/������ �Է����ּ���");
					String find_artist = s.nextLine().toLowerCase().replaceAll(" ", "");

					while (rs.next()) {
						String gsa = rs.getString("artist").toLowerCase().replaceAll(" ", "");
						if(find_artist.equals(gsa)) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("��ǰ��ȣ : "+ rs.getString("item_NUM") + "\t");
							System.out.println("���� : " + rs.getString("TITLE") + "\t");
							System.out.println("��Ƽ��Ʈ : " + rs.getString("ARTIST") + "\t");
							System.out.println("���ǻ� : " + rs.getString("PUBlisher") + "\t");
							System.out.println("�帣 : " + rs.getString("genre") + "\t");
							System.out.println("���� : " + rs.getString("PRICE") + "\t");
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
					s.nextLine();
					System.out.println("�˻��� ���ǻ縦 �Է����ּ���");
					String find_per = s.nextLine().toLowerCase().replaceAll(" ", "");
					System.out.println(find_per);

					while (rs.next()) {
						String gsp = rs.getString(5).toLowerCase().replaceAll(" ", "");
						if(find_per.equals(gsp)) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("��ǰ��ȣ : "+ rs.getString("item_NUM") + "\t");
							System.out.println("���� : " + rs.getString("TITLE") + "\t");
							System.out.println("��Ƽ��Ʈ : " + rs.getString("ARTIST") + "\t");
							System.out.println("���ǻ� : " + rs.getString("PUBlisher") + "\t");
							System.out.println("�帣 : " + rs.getString("genre") + "\t");
							System.out.println("���� : " + rs.getString("PRICE") + "\t");
						}
					}

					System.out.println("�� �˻��Ͻðڽ��ϱ�? (yes / no)");
					String ans5 = s.next();
					if(ans5.equals("yes") || ans5.equals("YES")|| ans5.equals("y") || ans5.equals("Y")) {
						break;

					} else {
						System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
						return;
					}

				case 6:
					s.nextLine();
					System.out.println("�˻��� �帣�� �Է����ּ���");
					String find_ser = s.nextLine().toLowerCase().replaceAll(" ", "");
					System.out.println(find_ser);

					while (rs.next()) {
						String gsg = rs.getString("genre").toLowerCase().replaceAll(" ", "");
						if(find_ser.equals(gsg)) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("��ǰ��ȣ : "+ rs.getString("item_NUM") + "\t");
							System.out.println("���� : " + rs.getString("TITLE") + "\t");
							System.out.println("��Ƽ��Ʈ : " + rs.getString("ARTIST") + "\t");
							System.out.println("���ǻ� : " + rs.getString("PUBlisher") + "\t");
							System.out.println("�帣 : " + rs.getString("genre") + "\t");
							System.out.println("���� : " + rs.getString("PRICE") + "\t");
						}
					}


					System.out.println("�� �˻��Ͻðڽ��ϱ�? (yes / no)");
					String ans6 = s.next();
					if(ans6.equals("yes") || ans6.equals("YES")|| ans6.equals("y") || ans6.equals("Y")) {
						break;

					} else {
						System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
						return;
					}

				case 0:
					System.out.println("�ʱ� ȭ������ ���ư��ϴ�.");
					return;

				default:
					System.out.println("�������� ���� �Է��Ͽ����ϴ�");
					System.out.println("���� �˻� ȭ������ ���ư��ϴ�.");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}
}
