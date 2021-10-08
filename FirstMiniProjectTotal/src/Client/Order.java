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
			// 주문관리 테이블에 들어갈 주문번 변수 생성
			if(order_mal_num == 0) {
			System.out.println("주문할 상품번호를 입력해주세요");
			order_mal_num = s.nextInt();
			}

			// 값을 불러올 상품정보 테이블 변수 생성
			String sql_mal_info_select = "SELECT * FROM items";
			PreparedStatement pstmt_mal_info_select = con.prepareStatement(sql_mal_info_select);
			ResultSet rs_mal_info_select = pstmt_mal_info_select.executeQuery();

			// 값을 넣을 주문관리 테이블 변수 생성
			String sql_order_manage_insert = "INSERT INTO ORDERS VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt_order_manage_insert = con.prepareStatement(sql_order_manage_insert);

			// 값을 불러올 주문관리 테이블 변수 생성
			String sql_order_manage_select = "SELECT * FROM ORDERS";
			PreparedStatement pstmt_order_manage_select = con.prepareStatement(sql_order_manage_select);
			ResultSet rs_order_manage_select = pstmt_order_manage_select.executeQuery();

			/*// 값을 넣을 매출관리 테이블 변수 생성
			String sql_sal_manage_insert = "INSERT INTO SALES VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt_sal_manage_insert = con.prepareStatement(sql_sal_manage_insert);*/

			// 값을 불러올 재고관리 테이블 변수 생성
			String sql_inventory_manage_select = "SELECT * FROM inventory";
			PreparedStatement pstmt_inventory_manage_select = con.prepareStatement(sql_inventory_manage_select);
			ResultSet rs_inventory_manage_select = pstmt_inventory_manage_select.executeQuery();

			// 변수 생성
			String tit = null;		// 타이틀 변수
			String art = null;		// 아티스트 변수
			int pri = 0;			// 가격 변수

			// 시간 변수
			java.util.Date udat = new java.util.Date();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");
			java.sql.Timestamp sdat = new java.sql.Timestamp(udat.getTime());

			// 주문 번호 카운팅
			int order_count = 0;						// 주문번호 변수 생성
			//int sal_count = 0;						// 판매번호 변수 생성
			int temp = 0;								// 칼럼 수 저장 변수
			String usid = BookSystem.userid;			// 로그인 한 사용자 아이디 변수 저장

			// 칼럼 수 저장
			while(rs_order_manage_select.next()) {
				temp = rs_order_manage_select.getRow();
			}
			order_count = temp + 10000;
			//sal_count = temp + 1;

			// 현재 해당 상품의 재고 확인
			int tempStock = 0;
			while(rs_inventory_manage_select.next()) {
				if(order_mal_num == rs_inventory_manage_select.getInt("item_num")) {
					tempStock = rs_inventory_manage_select.getInt("ITEM_QTY");
				}
			}

			// 상품 번호 불러오기
			int exist_mal_num = 0;		// 해당 상품이 있는지 없는지 판단
			while (rs_mal_info_select.next()) {
				if(order_mal_num == rs_mal_info_select.getInt("item_num")) {
					System.out.println("선택하신 상품번호의 정보는 아래와 같습니다.");
					if(rs_mal_info_select.getInt("cat_num") == 1) {
						System.out.println("분류 : 도서" + "\t");
					} else {
						System.out.println("분류 : 음반" + "\t");
					}
					System.out.println("상품번호 : "+ rs_mal_info_select.getString("item_num") + "\t");
					System.out.println("제목 : " + rs_mal_info_select.getString("TITLE") + "\t");
					System.out.println("아티스트 : " + rs_mal_info_select.getString("ARTIST") + "\t");
					System.out.println("출판사 : " + rs_mal_info_select.getString("PUBlisher") + "\t");
					System.out.println("장르 : " + rs_mal_info_select.getString("genre") + "\t");
					System.out.println("가격 : " + rs_mal_info_select.getString("PRICE") + "\t");
					System.out.println("현재 남은 재고는 "+ tempStock + "개 입니다. ");

					tit = rs_mal_info_select.getString("TITLE");
					art = rs_mal_info_select.getString("ARTIST");
					pri = rs_mal_info_select.getInt("PRICE");
					exist_mal_num = exist_mal_num + 1;
				} 

			}
			if(exist_mal_num == 0) {
				System.out.println("선택한 상품번호에 해당되는 상품이 없습니다.");
				return;
			}
			

			// 주문관리 테이블에 들어갈 상품 수량 변수 생성
			if(order_qty == 0) {
			System.out.println("구매할 수량을 입력해주세요");
			order_qty = s.nextInt();
			}
			
			// 재고가 0보다 많고 주문 수량보다 많은 경우
			if(tempStock > 0 && (tempStock - order_qty) >= 0) {
				// 주문 관리 테이블에 값 넣기
				pstmt_order_manage_insert.setInt(1, order_count);
				pstmt_order_manage_insert.setInt(2, order_mal_num);
				pstmt_order_manage_insert.setString(3, tit);
				pstmt_order_manage_insert.setString(4, art);
				pstmt_order_manage_insert.setInt(5, pri);
				pstmt_order_manage_insert.setString(6, usid);
				pstmt_order_manage_insert.setTimestamp(7, sdat);
				pstmt_order_manage_insert.setInt(8, order_qty);

				// 총액 변수화
				int total_Price = order_qty * pri;

				/*
				// 매출 관리 테이블에 값 넣기  => DB에서 트리거로 구현
				pstmt_sal_manage_insert.setInt(1, sal_count);
				pstmt_sal_manage_insert.setInt(2, order_count);
				pstmt_sal_manage_insert.setTimestamp(3, sdat);
				pstmt_sal_manage_insert.setInt(4, order_qty);
				pstmt_sal_manage_insert.setInt(5, total_Price);
				*/

				// 데이터베이스로 커밋 시키기
				System.out.println("구매를 원하시는 상품은 " + tit +"이며");
				System.out.println("구매를 원하시는 수량은 " + order_qty + "개 이며");
				System.out.println("총액은 " + total_Price +"원 입니다.");

				System.out.println("주문하시겠습니까?(yes/no)");
				String ans = s.next();
				//System.out.println("주문하시겠습니까?(ture/false)");
				//boolean ans = s.nextBoolean();

				if(ans.equals("yes") || ans.equals("YES")|| ans.equals("y") || ans.equals("Y")) {
					//if(ans) {
					pstmt_order_manage_insert.execute();
					//pstmt_sal_manage_insert.execute();	//DB 트리거로 구현
					// 주문번호 확인
					System.out.println("주문번호는 " + order_count+" 입니다.");
					System.out.println("감사합니다 구매가 완료되었습니다.");				
					System.out.println("초기화면으로 돌아갑니다.");
					
					// 주문 번호, 수량 초기화
					order_mal_num = 0;
					order_qty = 0;
				}
			} else if((tempStock - order_qty) < 0) {
				System.out.println("주문 수량이 남아있는 재고보다 많습니다.");
				
				// 주문 번호, 수량 초기화
				order_mal_num = 0;
				order_qty = 0;
				
			} else {
				System.out.println("★선택한 항목의 재고가 부족합니다.★");
				System.out.println("메뉴 선택 화면으로 돌아갑니다.");
				
				// 주문 번호, 수량 초기화
				order_mal_num = 0;
				order_qty = 0;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		} 
	} 
}
