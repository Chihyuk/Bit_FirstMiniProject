package Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import LoginMain.BookSystem;
import LoginMain.Connect;

public class Cart {
	public static void cart() throws SQLException {
		Scanner s = new Scanner(System.in);

		while(true) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("장바구니");
			System.out.println("1. 목록 | 2. 추가 | 3. 삭제 | 4. 구매 | 0. 종료 ");
			System.out.println("-----------------------------------------------------------");
			int key = s.nextInt();

			switch (key) {
			case 1:
				cartList();

				break;

			case 2:
				cartAdd();

				break;

			case 3:
				cartDelete();

				break;

			case 4:
				cartOrder();

				break;

			case 0:
				System.out.println("이전 화면으로 돌아갑니다.");

				return;

			default:

				break;
			}
		}
	}

	public static void cartList() {

		//System.out.println("\"실행확인\"");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM cart";

		try {
			con = Connect.makeConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// user id 가져오기
			String usid = BookSystem.userid;
			//System.out.println("user id 가져옴");

			System.out.println("장바구니 번호 | 아이디 | 상품번호 | 수량");
			while(rs.next()){
				String loginUT = usid.trim();
				String selectUT = rs.getString("USERID");
				if(loginUT.equals(selectUT)) {
					int cart_id = rs.getInt("cart_id"); 
					String userid = rs.getString("userid"); 
					int item_num = rs.getInt("item_num"); 
					int amount = rs.getInt("amount");  
					System.out.println(cart_id + " | " + userid + " | " + item_num + " | " + amount);
				}	
			}
		} catch (Exception e) {
			System.out.println("실패!");
			System.out.println(e.getMessage());
		} finally {
			try{
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				//e.getMessage();
			}
		}

	}

	public static void cartAdd() {

		//System.out.println("실행 확인");

		Scanner s = new Scanner(System.in);
		String sql = "INSERT INTO cart(cart_id, userid, item_num, amount) VALUES(?, ?, ?, ?)";
		Connection con = null;		
		PreparedStatement ps = null;



		try {
			con = Connect.makeConnection();
			ps = con.prepareStatement(sql);

			String sql_cart_select = "SELECT * FROM cart";
			PreparedStatement pstmt_cart_select = con.prepareStatement(sql_cart_select);
			ResultSet rs_cart_select = pstmt_cart_select.executeQuery();

			// user id 가져오기
			String usid = BookSystem.userid;

			// 카트번호 자동 카운팅 시키기
			int cartNo = 0;
			int temp = 0;
			while(rs_cart_select.next()) {
				temp = rs_cart_select.getInt("cart_id");
				if(cartNo < temp) {
					cartNo = temp;
				}
			}
			cartNo= cartNo+1;

			System.out.printf("제품번호: ");
			int item_num = s.nextInt();
			System.out.printf("수량: ");
			int amount = s.nextInt();

			ps.setInt(1, cartNo);
			ps.setString(2, usid);
			ps.setInt(3, item_num);
			ps.setInt(4, amount);
			ps.executeUpdate();
			System.out.println("장바구니에 추가되었습니다.");
		}catch (Exception e) {	
			System.out.println("실패!");
			e.printStackTrace();
		} finally {
			try{
				ps.close();
				con.close();
			} catch(Exception e ) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void cartDelete() throws SQLException {
		//System.out.println("실행 확인");
		Scanner s = new Scanner(System.in);
		Connection con = null;		
		PreparedStatement ps = null;
		String sql = "DELETE FROM CART WHERE cart_id = ?";
		int cart_id;
		try {
			con = Connect.makeConnection();
			ps = con.prepareStatement(sql);

			System.out.println("삭제할 장바구니 번호를 입력해주세요 : ");
			cart_id = s.nextInt();

			ps.setInt(1, cart_id);
			ps.execute(); 
			//System.out.println(r);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				ps.close();
				con.close();
			} catch(Exception e) {	 
			}
		}
		
	}

	public static void cartOrder() throws SQLException {
		//System.out.println("실행 확인");
		Connection con = Connect.makeConnection();

		// 카트 선택
		String sql_cart_select = "SELECT * FROM Cart";
		PreparedStatement pstmt_cart_select = con.prepareStatement(sql_cart_select);
		ResultSet rs_cart_select = pstmt_cart_select.executeQuery();

		// 지울 카트
		String sql_cart_delet = "DELETE FROM CART WHERE item_num = ? and userid = ?";
		PreparedStatement pstmt_cart_delete = con.prepareStatement(sql_cart_delet);

		// user id 가져오기
		String usid = BookSystem.userid;
		System.out.println("user id 가져옴");

		Order o = new Order();
		System.out.println("주문 객체 생성");

		int tempOrderNo = 0;
		int tempOrderqty = 0;
		int tempON[] = new int[30];
		int temp = 0;

		while(rs_cart_select.next()) {
			System.out.println("while 들어옴");

			String loginUT = usid.trim();
			String selectUT = rs_cart_select.getString("USERID");

			if(loginUT.equals(selectUT)) {
				System.out.println(selectUT+ "와"+ loginUT+"아이디 일치" );

				// 장바구니에 넣은 상품번호와 수량 저장
				tempOrderNo = rs_cart_select.getInt("Item_num");
				tempOrderqty = rs_cart_select.getInt("AMOUNT");

				// order class의 변수에 값 저장
				o.order_mal_num = tempOrderNo;
				o.order_qty = tempOrderqty;

				// order class 실행
				o.order();

				tempON[temp] = tempOrderNo;
				System.out.println("배열에 저장 : " + tempON[temp]);
				temp++;
				System.out.println("배열 수 : " + temp);
			}
		}
		// 장바구니 항목 삭제
		for (int i = 0; i < tempON.length; i++) {
			pstmt_cart_delete.setInt(1, tempON[i]);
			pstmt_cart_delete.setString(2, usid);
			pstmt_cart_delete.execute();
		}
		System.out.println("장바구니 항목이 삭제되었습니다.");
	}
}