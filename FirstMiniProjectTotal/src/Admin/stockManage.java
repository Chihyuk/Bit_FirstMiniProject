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
				System.out.println("어떤 메뉴를 이용하시겠습니까?");
				System.out.println("1. 상품번호와 재고만 확인 | 2. 전체 재고 확인 | 3. 상품번호로 재고 확인 | 4. 상품명으로 재고 확인 | 5. 주문이 필요한 재고 확인 | 0. 초기화면으로 이동");
				int key = s.nextInt();
				switch (key) {

				case 1:
					System.out.println("상품 번호와 재고만 출력됩니다.");

					while (rs_sto_info_select.next()) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("상품번호 : "+ rs_sto_info_select.getString("item_NUM") + "\t");
						System.out.println("재고수량 : " + rs_sto_info_select.getString("ITEM_QTY") + "\t");
					}


					System.out.println("더 검색하시겠습니까? (yes / no)");
					String ans1 = s.next();
					if(ans1.equals("yes") || ans1.equals("YES")|| ans1.equals("y") || ans1.equals("Y")) {
						break;

					} else {
						System.out.println("초기화면으로 돌아갑니다.");
						return;
					}

				case 2:
					System.out.println("전체 상품의 재고를출력합니다.");
					while(rs_sto_info_select.next()) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("상품번호 : "+ rs_sto_info_select.getString("item_NUM") + "\t");
						System.out.println("재고수량 : " + rs_sto_info_select.getString("ITEM_QTY") + "\t");
						System.out.println("제목 : " + rs_sto_info_select.getString("TITLE") + "\t");
						System.out.println("아티스트 : " + rs_sto_info_select.getString("ARTIST") + "\t");
						System.out.println("출판사 : " + rs_sto_info_select.getString("PUBlisher") + "\t");
						System.out.println("장르 : " + rs_sto_info_select.getString("genre") + "\t");
						System.out.println("가격 : " + rs_sto_info_select.getString("PRICE") + "\t");
					}

					System.out.println("더 검색하시겠습니까? (yes / no)");
					String ans2 = s.next();
					if(ans2.equals("yes") || ans2.equals("YES")|| ans2.equals("y") || ans2.equals("Y")) {
						break;

					} else {
						System.out.println("초기화면으로 돌아갑니다.");
						return;
					}


				case 3:
					System.out.println("재고를 확인하고 싶은 상품의 번호를 입력해주세요");
					int mal_num = s.nextInt();
					while (rs_sto_info_select.next()) {
						if(mal_num == rs_sto_info_select.getInt("item_num")) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("상품번호 : "+ rs_sto_info_select.getString("item_NUM") + "\t");
							System.out.println("재고수량 : " + rs_sto_info_select.getString("ITEM_QTY") + "\t");
							System.out.println("제목 : " + rs_sto_info_select.getString("TITLE") + "\t");
							System.out.println("아티스트 : " + rs_sto_info_select.getString("ARTIST") + "\t");
							System.out.println("출판사 : " + rs_sto_info_select.getString("PUBlisher") + "\t");
							System.out.println("장르 : " + rs_sto_info_select.getString("genre") + "\t");
							System.out.println("가격 : " + rs_sto_info_select.getString("PRICE") + "\t");
						}
					}
					System.out.println("더 검색하시겠습니까? (yes / no)");
					String ans3 = s.next();
					if(ans3.equals("yes") || ans3.equals("YES")|| ans3.equals("y") || ans3.equals("Y")) {
						break;

					} else {
						System.out.println("초기화면으로 돌아갑니다.");
						return;
					}

				case 4:
					System.out.println("재고를 확인하고 싶은 상품의 이름 입력해주세요");
					s.nextLine();
					String find_title = s.nextLine().toLowerCase().replaceAll(" ", "");
					//System.out.println(find_title);
					while (rs_sto_info_select.next()) {
						String gs = rs_sto_info_select.getString("TITLE").toLowerCase().replaceAll(" ", "");
						if(find_title.equals(gs)) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("상품번호 : "+ rs_sto_info_select.getString("item_NUM") + "\t");
							System.out.println("재고수량 : " + rs_sto_info_select.getString("ITEM_QTY") + "\t");
							System.out.println("제목 : " + rs_sto_info_select.getString("TITLE") + "\t");
							System.out.println("아티스트 : " + rs_sto_info_select.getString("ARTIST") + "\t");
							System.out.println("출판사 : " + rs_sto_info_select.getString("PUBlisher") + "\t");
							System.out.println("장르 : " + rs_sto_info_select.getString("genre") + "\t");
							System.out.println("가격 : " + rs_sto_info_select.getString("PRICE") + "\t");
						}
					}


					System.out.println("더 검색하시겠습니까? (yes / no)");
					String ans4 = s.next();
					if(ans4.equals("yes") || ans4.equals("YES")|| ans4.equals("y") || ans4.equals("Y")) {
						break;

					} else {
						System.out.println("초기화면으로 돌아갑니다.");
						return;
					}

				case 5:
					System.out.println("몇 개 이하인 재고의 상품을 확인할까요?");
					int st = s.nextInt();
					while (rs_sto_info_select.next()) {
						int iqt = rs_sto_info_select.getInt("item_qty");
						if(st >= iqt) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("상품번호 : "+ rs_sto_info_select.getString("item_NUM") + "\t");
							System.out.println("재고수량 : " + rs_sto_info_select.getString("ITEM_QTY") + "\t");
							System.out.println("제목 : " + rs_sto_info_select.getString("TITLE") + "\t");
						}
					}

				case 0:
					System.out.println("관리자 초기 페이지로 이동합니다");
					return;

				default:
					System.out.println("잘못된 값을 입력하였습니다.");
					System.out.println("처음 화면으로 돌아갑니다.");
					break;
				}
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}

	}
}
