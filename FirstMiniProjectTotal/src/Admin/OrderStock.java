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

		// 값을 불러올 재고관리 테이블 변수 생성
		String sql_inventory_manage_select = "SELECT * FROM inventory";
		PreparedStatement pstmt_inventory_manage_select = con.prepareStatement(sql_inventory_manage_select);
		ResultSet rs_inventory_manage_select = pstmt_inventory_manage_select.executeQuery();
		
		// 값을 넣을 재고관리 테이블 변수 생성
		String sql_inventory_manage_insert = "UPDATE inventory SET ITEM_QTY = ? WHERE ITEM_NUM = ?";
		PreparedStatement pstmt_inventory_manage_insert = con.prepareStatement(sql_inventory_manage_insert);

		// 값을 넣을 주문관리 테이블 변수 생성
		String sql_purchase_manage_insert = "INSERT INTO purchase VALUES (?, ?, ?)";
		PreparedStatement pstmt_purchase_manage_insert = con.prepareStatement(sql_purchase_manage_insert);
		
		try {
			System.out.println("주문하실 상품의 번호를 입력해주세요");
			int itno = s.nextInt();

			// 현재 해당 상품의 재고 확인
			int temp = 0;
			while(rs_inventory_manage_select.next()) {
				if(itno == rs_inventory_manage_select.getInt("item_num")) {
					temp = rs_inventory_manage_select.getInt("ITEM_QTY");
				}
			}

			System.out.println("현재 보유한 재고는 " + temp + "개 입니다.");

			System.out.println("주문하실 상품의 개수를 입력해주세요");
			int itqty = s.nextInt();

			// 주문하는 재고 합계
			int realqty = itqty + temp;
			pstmt_inventory_manage_insert.setInt(1, realqty);
			pstmt_inventory_manage_insert.setInt(2, itno);
			
			// 시간 변수 생성
			java.util.Date udat = new java.util.Date();
			java.sql.Timestamp sdat = new java.sql.Timestamp(udat.getTime());
			
			// 구매목록 테이블에 값 입력
			pstmt_purchase_manage_insert.setInt(1, itno);
			pstmt_purchase_manage_insert.setInt(2, itqty);
			pstmt_purchase_manage_insert.setTimestamp(3, sdat);
			

			// 커밋
			pstmt_inventory_manage_insert.execute();
			pstmt_purchase_manage_insert.execute();

			System.out.println("주문이 완료되었습니다");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
