package Admin;

import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
	public static void adminMain() throws SQLException {
		CoIncome ci = new CoIncome();
		Scanner s = new Scanner(System.in);

		System.out.println("안녕하세요 관리자님");
		while(true) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("어떤 메뉴를 이용하시겠습니까?");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 매출 관리 | 2. 재고 관리 | 3. 재고 주문 | 0. 로그아웃");
			int key = s.nextInt();
			try {
				switch (key) {
				case 1:
					System.out.println("매출 관리 페이지입니다.");
					System.out.println("-----------------------------------------------------------");
					ci.income();

					break;

				case 2:
					System.out.println("재고 관리 페이지입니다.");
					stockManage.stockMan();
					break;

				case 3:
					System.out.println("재고 주문 페이지입니다.");
					OrderStock.orderStock();
					break;
					
				case 0:
					System.out.println("이용해주셔서 감사합니다.");
					System.out.println("로그아웃 합니다.");
					return;

				default:
					System.out.println("잘 못 입력하였습니다.");
					System.out.println("처음 화면으로 돌아갑니다.");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
}
