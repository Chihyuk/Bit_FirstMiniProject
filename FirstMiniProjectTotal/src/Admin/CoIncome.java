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

		System.out.println("어떤 매출을 확인하고 싶으신가요?");
		System.out.println("1. 일매출 | 2. 월매출 | 3. 연매출 | 0. 초기화면으로 이동ㄴ");
		int selectSal = s.nextInt();
		try {
			switch (selectSal) {
			case 1:
				System.out.println("검색하고 싶은 일을 입력해주세요 (yyyy-mm-dd)");
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
				System.out.println("일매출 총액 : " + priceDaySum);			
				break;
			case 2:
				System.out.println("검색하고 싶은 일을 입력해주세요 (yyyy-mm)");
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
				System.out.println("월매출 총액 : " + priceMonSum);			
				break;
			case 3:
				System.out.println("검색하고 싶은 일을 입력해주세요 (yyyy)");
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
				System.out.println("연매출 총액 : " + priceYearSum);		
				break;

			case 0:
				System.out.println("관리자 초기 화면으로 돌아갑니다.");
				return;
			default:
				System.out.println("잘 못 입력하였습니다.");
				System.out.println("매출 확인 페이지로 돌아갑니다.");
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
