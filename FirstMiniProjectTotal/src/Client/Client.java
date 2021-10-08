package Client;

import java.sql.SQLException;
import java.util.Scanner;

public class Client {
	public static void clientMain() throws SQLException {
		Order mainOrder = new Order();
		Search mainSerch = new Search();
		Cart mainCart = new Cart();
		
		Scanner s = new Scanner(System.in);
		while(true) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("어떤 메뉴를 이용하시겠습니까?");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. 상품 검색 | 2. 상품 주문 | 3. 장바구니 | 0. 로그아웃");
			int key = s.nextInt();
			try {
				switch (key) {
				case 1:
					System.out.println("상품 검색 페이지입니다.");
					System.out.println("-----------------------------------------------------------");

					mainSerch.search();
					
					break;

				case 2:
					System.out.println("상품 주문 페이지입니다.");
					System.out.println("-----------------------------------------------------------");
					
					mainOrder.order();
					break;
					
				case 3:
					mainCart.cart();
					
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
