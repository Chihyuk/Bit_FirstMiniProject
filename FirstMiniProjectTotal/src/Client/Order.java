package Client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import LoginMain.BookSystem;
import LoginMain.Connect;

public class Order {
	
	static int order_mal_num = 0;
	static int order_qty = 0;
	
	public static void order() throws SQLException {
		try {
			Connection con = Connect.makeConnection();
			Scanner s = new Scanner(System.in);
			// �ֹ����� ���̺� �� �ֹ��� ���� ����
			if(order_mal_num == 0) {
			System.out.println("�ֹ��� ��ǰ��ȣ�� �Է����ּ���");
			order_mal_num = s.nextInt();
			}

			// ���� �ҷ��� ��ǰ���� ���̺� ���� ����
			String sql_mal_info_select = "SELECT * FROM items";
			PreparedStatement pstmt_mal_info_select = con.prepareStatement(sql_mal_info_select);
			ResultSet rs_mal_info_select = pstmt_mal_info_select.executeQuery();

			// ���� ���� �ֹ����� ���̺� ���� ����
			String sql_order_manage_insert = "INSERT INTO ORDERS VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt_order_manage_insert = con.prepareStatement(sql_order_manage_insert);

			// ���� �ҷ��� �ֹ����� ���̺� ���� ����
			String sql_order_manage_select = "SELECT * FROM ORDERS";
			PreparedStatement pstmt_order_manage_select = con.prepareStatement(sql_order_manage_select);
			ResultSet rs_order_manage_select = pstmt_order_manage_select.executeQuery();

			/*// ���� ���� ������� ���̺� ���� ����
			String sql_sal_manage_insert = "INSERT INTO SALES VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt_sal_manage_insert = con.prepareStatement(sql_sal_manage_insert);*/

			// ���� �ҷ��� ������ ���̺� ���� ����
			String sql_inventory_manage_select = "SELECT * FROM inventory";
			PreparedStatement pstmt_inventory_manage_select = con.prepareStatement(sql_inventory_manage_select);
			ResultSet rs_inventory_manage_select = pstmt_inventory_manage_select.executeQuery();

			// ���� ����
			String tit = null;		// Ÿ��Ʋ ����
			String art = null;		// ��Ƽ��Ʈ ����
			int pri = 0;			// ���� ����

			// �ð� ����
			java.util.Date udat = new java.util.Date();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");
			java.sql.Timestamp sdat = new java.sql.Timestamp(udat.getTime());

			// �ֹ� ��ȣ ī����
			int order_count = 0;						// �ֹ���ȣ ���� ����
			//int sal_count = 0;						// �ǸŹ�ȣ ���� ����
			int temp = 0;								// Į�� �� ���� ����
			String usid = BookSystem.userid;			// �α��� �� ����� ���̵� ���� ����

			// Į�� �� ����
			while(rs_order_manage_select.next()) {
				temp = rs_order_manage_select.getRow();
			}
			order_count = temp + 10000;
			//sal_count = temp + 1;

			// ���� �ش� ��ǰ�� ��� Ȯ��
			int tempStock = 0;
			while(rs_inventory_manage_select.next()) {
				if(order_mal_num == rs_inventory_manage_select.getInt("item_num")) {
					tempStock = rs_inventory_manage_select.getInt("ITEM_QTY");
				}
			}

			// ��ǰ ��ȣ �ҷ�����
			int exist_mal_num = 0;		// �ش� ��ǰ�� �ִ��� ������ �Ǵ�
			while (rs_mal_info_select.next()) {
				if(order_mal_num == rs_mal_info_select.getInt("item_num")) {
					System.out.println("�����Ͻ� ��ǰ��ȣ�� ������ �Ʒ��� �����ϴ�.");
					if(rs_mal_info_select.getInt("cat_num") == 1) {
						System.out.println("�з� : ����" + "\t");
					} else {
						System.out.println("�з� : ����" + "\t");
					}
					System.out.println("��ǰ��ȣ : "+ rs_mal_info_select.getString("item_num") + "\t");
					System.out.println("���� : " + rs_mal_info_select.getString("TITLE") + "\t");
					System.out.println("��Ƽ��Ʈ : " + rs_mal_info_select.getString("ARTIST") + "\t");
					System.out.println("���ǻ� : " + rs_mal_info_select.getString("PUBlisher") + "\t");
					System.out.println("�帣 : " + rs_mal_info_select.getString("genre") + "\t");
					System.out.println("���� : " + rs_mal_info_select.getString("PRICE") + "\t");
					System.out.println("���� ���� ���� "+ tempStock + "�� �Դϴ�. ");

					tit = rs_mal_info_select.getString("TITLE");
					art = rs_mal_info_select.getString("ARTIST");
					pri = rs_mal_info_select.getInt("PRICE");
					exist_mal_num = exist_mal_num + 1;
				} 

			}
			if(exist_mal_num == 0) {
				System.out.println("������ ��ǰ��ȣ�� �ش�Ǵ� ��ǰ�� �����ϴ�.");
				return;
			}
			

			// �ֹ����� ���̺� �� ��ǰ ���� ���� ����
			if(order_qty == 0) {
			System.out.println("������ ������ �Է����ּ���");
			order_qty = s.nextInt();
			}
			
			// ��� 0���� ���� �ֹ� �������� ���� ���
			if(tempStock > 0 && (tempStock - order_qty) >= 0) {
				// �ֹ� ���� ���̺� �� �ֱ�
				pstmt_order_manage_insert.setInt(1, order_count);
				pstmt_order_manage_insert.setInt(2, order_mal_num);
				pstmt_order_manage_insert.setString(3, tit);
				pstmt_order_manage_insert.setString(4, art);
				pstmt_order_manage_insert.setInt(5, pri);
				pstmt_order_manage_insert.setString(6, usid);
				pstmt_order_manage_insert.setTimestamp(7, sdat);
				pstmt_order_manage_insert.setInt(8, order_qty);

				// �Ѿ� ����ȭ
				int total_Price = order_qty * pri;

				/*
				// ���� ���� ���̺� �� �ֱ�  => DB���� Ʈ���ŷ� ����
				pstmt_sal_manage_insert.setInt(1, sal_count);
				pstmt_sal_manage_insert.setInt(2, order_count);
				pstmt_sal_manage_insert.setTimestamp(3, sdat);
				pstmt_sal_manage_insert.setInt(4, order_qty);
				pstmt_sal_manage_insert.setInt(5, total_Price);
				*/

				// �����ͺ��̽��� Ŀ�� ��Ű��
				System.out.println("���Ÿ� ���Ͻô� ��ǰ�� " + tit +"�̸�");
				System.out.println("���Ÿ� ���Ͻô� ������ " + order_qty + "�� �̸�");
				System.out.println("�Ѿ��� " + total_Price +"�� �Դϴ�.");

				System.out.println("�ֹ��Ͻðڽ��ϱ�?(yes/no)");
				String ans = s.next();
				//System.out.println("�ֹ��Ͻðڽ��ϱ�?(ture/false)");
				//boolean ans = s.nextBoolean();

				if(ans.equals("yes") || ans.equals("YES")|| ans.equals("y") || ans.equals("Y")) {
					//if(ans) {
					pstmt_order_manage_insert.execute();
					//pstmt_sal_manage_insert.execute();	//DB Ʈ���ŷ� ����
					// �ֹ���ȣ Ȯ��
					System.out.println("�ֹ���ȣ�� " + order_count+" �Դϴ�.");
					System.out.println("�����մϴ� ���Ű� �Ϸ�Ǿ����ϴ�.");				
					System.out.println("�ʱ�ȭ������ ���ư��ϴ�.");
					
					// �ֹ� ��ȣ, ���� �ʱ�ȭ
					order_mal_num = 0;
					order_qty = 0;
				}
			} else if((tempStock - order_qty) < 0) {
				System.out.println("�ֹ� ������ �����ִ� ����� �����ϴ�.");
				
				// �ֹ� ��ȣ, ���� �ʱ�ȭ
				order_mal_num = 0;
				order_qty = 0;
				
			} else {
				System.out.println("�ڼ����� �׸��� ��� �����մϴ�.��");
				System.out.println("�޴� ���� ȭ������ ���ư��ϴ�.");
				
				// �ֹ� ��ȣ, ���� �ʱ�ȭ
				order_mal_num = 0;
				order_qty = 0;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		} 
	} 
}
