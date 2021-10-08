package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import LoginMain.Connect;

public class Search {
	public static void search() throws SQLException {
		// 상품번호 검색
		Connection con = Connect.makeConnection();
		Scanner s = new Scanner(System.in);
		boolean tf = true;

		while(tf) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("무엇을 출력할까요?");
			System.out.println("1. 전체출력 | 2. 상품번호 검색 | 3. 상품 제목 검색 | 4. 저자/가수 검색 | 5. 출판사/소속사 검색 | 6. 장르 검색 | 0. 초기화면");
			int want = s.nextInt();
			System.out.println("-----------------------------------------------------------");

			// 전체 테이블을 변수에 입력
			String sql = "SELECT * FROM items";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			try {
				switch (want) {
				case 1:
					System.out.println("전체를 출력합니다");

					while (rs.next()) {
						System.out.println("-----------------------------------------------------------");
						System.out.println("상품번호 : "+ rs.getString("item_NUM") + "\t");
						System.out.println("제목 : " + rs.getString("TITLE") + "\t");
						System.out.println("아티스트 : " + rs.getString("ARTIST") + "\t");
						System.out.println("출판사 : " + rs.getString("PUBlisher") + "\t");
						System.out.println("장르 : " + rs.getString("genre") + "\t");
						System.out.println("가격 : " + rs.getString("PRICE") + "\t");
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
					System.out.println("검색할 상품번호를 입력해주세요");
					int find_mal_num = s.nextInt();

					while (rs.next()) {
						if(find_mal_num == rs.getInt("item_num")) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("상품번호 : "+ rs.getString("item_NUM") + "\t");
							System.out.println("제목 : " + rs.getString("TITLE") + "\t");
							System.out.println("아티스트 : " + rs.getString("ARTIST") + "\t");
							System.out.println("출판사 : " + rs.getString("PUBlisher") + "\t");
							System.out.println("장르 : " + rs.getString("genre") + "\t");
							System.out.println("가격 : " + rs.getString("PRICE") + "\t");
						}
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
					s.nextLine();
					System.out.println("검색할 제목을 입력해주세요");
					String find_title = s.nextLine().toLowerCase().replaceAll(" ", "");

					while (rs.next()) {
						String gst = rs.getString("TITLE").toLowerCase().replaceAll(" ", "");
						if(find_title.equals(gst)) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("상품번호 : "+ rs.getString("item_NUM") + "\t");
							System.out.println("제목 : " + rs.getString("TITLE") + "\t");
							System.out.println("아티스트 : " + rs.getString("ARTIST") + "\t");
							System.out.println("출판사 : " + rs.getString("PUBlisher") + "\t");
							System.out.println("장르 : " + rs.getString("genre") + "\t");
							System.out.println("가격 : " + rs.getString("PRICE") + "\t");
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
					s.nextLine();
					System.out.println("검색할 저자/가수를 입력해주세요");
					String find_artist = s.nextLine().toLowerCase().replaceAll(" ", "");

					while (rs.next()) {
						String gsa = rs.getString("artist").toLowerCase().replaceAll(" ", "");
						if(find_artist.equals(gsa)) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("상품번호 : "+ rs.getString("item_NUM") + "\t");
							System.out.println("제목 : " + rs.getString("TITLE") + "\t");
							System.out.println("아티스트 : " + rs.getString("ARTIST") + "\t");
							System.out.println("출판사 : " + rs.getString("PUBlisher") + "\t");
							System.out.println("장르 : " + rs.getString("genre") + "\t");
							System.out.println("가격 : " + rs.getString("PRICE") + "\t");
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
					s.nextLine();
					System.out.println("검색할 출판사를 입력해주세요");
					String find_per = s.nextLine().toLowerCase().replaceAll(" ", "");
					System.out.println(find_per);

					while (rs.next()) {
						String gsp = rs.getString(5).toLowerCase().replaceAll(" ", "");
						if(find_per.equals(gsp)) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("상품번호 : "+ rs.getString("item_NUM") + "\t");
							System.out.println("제목 : " + rs.getString("TITLE") + "\t");
							System.out.println("아티스트 : " + rs.getString("ARTIST") + "\t");
							System.out.println("출판사 : " + rs.getString("PUBlisher") + "\t");
							System.out.println("장르 : " + rs.getString("genre") + "\t");
							System.out.println("가격 : " + rs.getString("PRICE") + "\t");
						}
					}

					System.out.println("더 검색하시겠습니까? (yes / no)");
					String ans5 = s.next();
					if(ans5.equals("yes") || ans5.equals("YES")|| ans5.equals("y") || ans5.equals("Y")) {
						break;

					} else {
						System.out.println("초기화면으로 돌아갑니다.");
						return;
					}

				case 6:
					s.nextLine();
					System.out.println("검색할 장르를 입력해주세요");
					String find_ser = s.nextLine().toLowerCase().replaceAll(" ", "");
					System.out.println(find_ser);

					while (rs.next()) {
						String gsg = rs.getString("genre").toLowerCase().replaceAll(" ", "");
						if(find_ser.equals(gsg)) {
							System.out.println("-----------------------------------------------------------");
							System.out.println("상품번호 : "+ rs.getString("item_NUM") + "\t");
							System.out.println("제목 : " + rs.getString("TITLE") + "\t");
							System.out.println("아티스트 : " + rs.getString("ARTIST") + "\t");
							System.out.println("출판사 : " + rs.getString("PUBlisher") + "\t");
							System.out.println("장르 : " + rs.getString("genre") + "\t");
							System.out.println("가격 : " + rs.getString("PRICE") + "\t");
						}
					}


					System.out.println("더 검색하시겠습니까? (yes / no)");
					String ans6 = s.next();
					if(ans6.equals("yes") || ans6.equals("YES")|| ans6.equals("y") || ans6.equals("Y")) {
						break;

					} else {
						System.out.println("초기화면으로 돌아갑니다.");
						return;
					}

				case 0:
					System.out.println("초기 화면으로 돌아갑니다.");
					return;

				default:
					System.out.println("부적절한 값을 입력하였습니다");
					System.out.println("상춤 검색 화면으로 돌아갑니다.");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}
}
