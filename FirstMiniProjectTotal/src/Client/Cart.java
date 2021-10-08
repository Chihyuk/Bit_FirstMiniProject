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
			System.out.println("��ٱ���");
			System.out.println("1. ��� | 2. �߰� | 3. ���� | 4. ���� | 0. ���� ");
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
				System.out.println("���� ȭ������ ���ư��ϴ�.");

				return;

			default:

				break;
			}
		}
	}

	public static void cartList() {

		//System.out.println("\"����Ȯ��\"");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM cart";

		try {
			con = Connect.makeConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// user id ��������
			String usid = BookSystem.userid;
			//System.out.println("user id ������");

			System.out.println("��ٱ��� ��ȣ | ���̵� | ��ǰ��ȣ | ����");
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
			System.out.println("����!");
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

		//System.out.println("���� Ȯ��");

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

			// user id ��������
			String usid = BookSystem.userid;

			// īƮ��ȣ �ڵ� ī���� ��Ű��
			int cartNo = 0;
			int temp = 0;
			while(rs_cart_select.next()) {
				temp = rs_cart_select.getInt("cart_id");
				if(cartNo < temp) {
					cartNo = temp;
				}
			}
			cartNo= cartNo+1;

			System.out.printf("��ǰ��ȣ: ");
			int item_num = s.nextInt();
			System.out.printf("����: ");
			int amount = s.nextInt();

			ps.setInt(1, cartNo);
			ps.setString(2, usid);
			ps.setInt(3, item_num);
			ps.setInt(4, amount);
			ps.executeUpdate();
			System.out.println("��ٱ��Ͽ� �߰��Ǿ����ϴ�.");
		}catch (Exception e) {	
			System.out.println("����!");
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
		//System.out.println("���� Ȯ��");
		Scanner s = new Scanner(System.in);
		Connection con = null;		
		PreparedStatement ps = null;
		String sql = "DELETE FROM CART WHERE cart_id = ?";
		int cart_id;
		try {
			con = Connect.makeConnection();
			ps = con.prepareStatement(sql);

			System.out.println("������ ��ٱ��� ��ȣ�� �Է����ּ��� : ");
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
		//System.out.println("���� Ȯ��");
		Connection con = Connect.makeConnection();

		// īƮ ����
		String sql_cart_select = "SELECT * FROM Cart";
		PreparedStatement pstmt_cart_select = con.prepareStatement(sql_cart_select);
		ResultSet rs_cart_select = pstmt_cart_select.executeQuery();

		// ���� īƮ
		String sql_cart_delet = "DELETE FROM CART WHERE item_num = ? and userid = ?";
		PreparedStatement pstmt_cart_delete = con.prepareStatement(sql_cart_delet);

		// user id ��������
		String usid = BookSystem.userid;
		System.out.println("user id ������");

		Order o = new Order();
		System.out.println("�ֹ� ��ü ����");

		int tempOrderNo = 0;
		int tempOrderqty = 0;
		int tempON[] = new int[30];
		int temp = 0;

		while(rs_cart_select.next()) {
			System.out.println("while ����");

			String loginUT = usid.trim();
			String selectUT = rs_cart_select.getString("USERID");

			if(loginUT.equals(selectUT)) {
				System.out.println(selectUT+ "��"+ loginUT+"���̵� ��ġ" );

				// ��ٱ��Ͽ� ���� ��ǰ��ȣ�� ���� ����
				tempOrderNo = rs_cart_select.getInt("Item_num");
				tempOrderqty = rs_cart_select.getInt("AMOUNT");

				// order class�� ������ �� ����
				o.order_mal_num = tempOrderNo;
				o.order_qty = tempOrderqty;

				// order class ����
				o.order();

				tempON[temp] = tempOrderNo;
				System.out.println("�迭�� ���� : " + tempON[temp]);
				temp++;
				System.out.println("�迭 �� : " + temp);
			}
		}
		// ��ٱ��� �׸� ����
		for (int i = 0; i < tempON.length; i++) {
			pstmt_cart_delete.setInt(1, tempON[i]);
			pstmt_cart_delete.setString(2, usid);
			pstmt_cart_delete.execute();
		}
		System.out.println("��ٱ��� �׸��� �����Ǿ����ϴ�.");
	}
}