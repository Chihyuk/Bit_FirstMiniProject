package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import LoginMain.Connect;

public class OrderStock {
	public static void orderStock() throws SQLException {
		Connection con = Connect.makeConnection();
		Scanner s = new Scanner(System.in);

		// ���� �ҷ��� ������ ���̺� ���� ����
		String sql_inventory_manage_select = "SELECT * FROM inventory";
		PreparedStatement pstmt_inventory_manage_select = con.prepareStatement(sql_inventory_manage_select);
		ResultSet rs_inventory_manage_select = pstmt_inventory_manage_select.executeQuery();
		
		// ���� ���� ������ ���̺� ���� ����
		String sql_inventory_manage_insert = "UPDATE inventory SET ITEM_QTY = ? WHERE ITEM_NUM = ?";
		PreparedStatement pstmt_inventory_manage_insert = con.prepareStatement(sql_inventory_manage_insert);

		// ���� ���� �ֹ����� ���̺� ���� ����
		String sql_purchase_manage_insert = "INSERT INTO purchase VALUES (?, ?, ?)";
		PreparedStatement pstmt_purchase_manage_insert = con.prepareStatement(sql_purchase_manage_insert);
		
		try {
			System.out.println("�ֹ��Ͻ� ��ǰ�� ��ȣ�� �Է����ּ���");
			int itno = s.nextInt();

			// ���� �ش� ��ǰ�� ��� Ȯ��
			int temp = 0;
			while(rs_inventory_manage_select.next()) {
				if(itno == rs_inventory_manage_select.getInt("item_num")) {
					temp = rs_inventory_manage_select.getInt("ITEM_QTY");
				}
			}

			System.out.println("���� ������ ���� " + temp + "�� �Դϴ�.");

			System.out.println("�ֹ��Ͻ� ��ǰ�� ������ �Է����ּ���");
			int itqty = s.nextInt();

			// �ֹ��ϴ� ��� �հ�
			int realqty = itqty + temp;
			pstmt_inventory_manage_insert.setInt(1, realqty);
			pstmt_inventory_manage_insert.setInt(2, itno);
			
			// �ð� ���� ����
			java.util.Date udat = new java.util.Date();
			java.sql.Timestamp sdat = new java.sql.Timestamp(udat.getTime());
			
			// ���Ÿ�� ���̺� �� �Է�
			pstmt_purchase_manage_insert.setInt(1, itno);
			pstmt_purchase_manage_insert.setInt(2, itqty);
			pstmt_purchase_manage_insert.setTimestamp(3, sdat);
			

			// Ŀ��
			pstmt_inventory_manage_insert.execute();
			pstmt_purchase_manage_insert.execute();

			System.out.println("�ֹ��� �Ϸ�Ǿ����ϴ�");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
