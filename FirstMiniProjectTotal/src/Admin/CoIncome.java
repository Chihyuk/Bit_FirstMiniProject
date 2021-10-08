package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import LoginMain.Connect;

public class CoIncome {
	public static void income () throws SQLException {
		Connection con = Connect.makeConnection();
		Scanner s = new Scanner(System.in);

		String sql_order_manage_select = "select * from sales";
		PreparedStatement pstmt_order_manage_select = con.prepareStatement(sql_order_manage_select);
		ResultSet rs_order_manage_select = pstmt_order_manage_select.executeQuery();

		System.out.println("� ������ Ȯ���ϰ� �����Ű���?");
		System.out.println("1. �ϸ��� | 2. ������ | 3. ������ | 0. �ʱ�ȭ������ �̵���");
		int selectSal = s.nextInt();
		try {
			switch (selectSal) {
			case 1:
				System.out.println("�˻��ϰ� ���� ���� �Է����ּ��� (yyyy-mm-dd)");
				String myinputD = s.next();
				int priceDaySum = 0;
				while(rs_order_manage_select.next()) {
					Date saveD = rs_order_manage_select.getDate("order_date");

					SimpleDateFormat tfD = new SimpleDateFormat("yyyy-MM-dd");
					String tSD = tfD.format(saveD);

					if(myinputD.equals(tSD)) {
						priceDaySum = priceDaySum + rs_order_manage_select.getInt("price");
					}
				}
				System.out.println("�ϸ��� �Ѿ� : " + priceDaySum);			
				break;
			case 2:
				System.out.println("�˻��ϰ� ���� ���� �Է����ּ��� (yyyy-mm)");
				String myinputM = s.next();
				int priceMonSum = 0;
				while(rs_order_manage_select.next()) {
					Date saveM = rs_order_manage_select.getDate("order_date");

					SimpleDateFormat tfM = new SimpleDateFormat("yyyy-MM");
					String tSM = tfM.format(saveM);

					if(myinputM.equals(tSM)) {
						priceMonSum = priceMonSum + rs_order_manage_select.getInt("price");
					}
				}
				System.out.println("������ �Ѿ� : " + priceMonSum);			
				break;
			case 3:
				System.out.println("�˻��ϰ� ���� ���� �Է����ּ��� (yyyy)");
				String myinputY = s.next();
				int priceYearSum = 0;
				while(rs_order_manage_select.next()) {
					Date saveY = rs_order_manage_select.getDate("order_date");

					SimpleDateFormat tfY = new SimpleDateFormat("yyyy");
					String tSY = tfY.format(saveY);

					if(myinputY.equals(tSY)) {
						priceYearSum = priceYearSum + rs_order_manage_select.getInt("price");
					}
				}
				System.out.println("������ �Ѿ� : " + priceYearSum);		
				break;

			case 0:
				System.out.println("������ �ʱ� ȭ������ ���ư��ϴ�.");
				return;
			default:
				System.out.println("�� �� �Է��Ͽ����ϴ�.");
				System.out.println("���� Ȯ�� �������� ���ư��ϴ�.");
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
