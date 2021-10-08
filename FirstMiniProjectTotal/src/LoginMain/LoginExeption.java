package LoginMain;

import java.util.regex.Pattern;

public class LoginExeption {
	public void idFormat(String str) throws AuthenException{
		if(str.length()<5 || str.length()>15){

			throw new AuthenException("5~15ÀÚ ÀÌ³»ÀÇ ¾ÆÀÌµð¸¸ °¡´ÉÇÕ´Ï´Ù");
		}
		
		int cnt1=0;

		int cnt2=0;

		
		for(int i=0;i<str.length();i++){

			char ch = str.charAt(i);

			if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))

				cnt1++;

			else if(ch>='0' && ch<='9')

				cnt2++;
		}

		
		if(cnt1==0 || cnt2==0)

			throw new AuthenException("¾ÆÀÌµð´Â ¿µ¹®ÀÚ¿Í ¼ýÀÚ¸¦ È¥¿ëÇØ¼­ ¸¸µé¾îÁÖ¼¼¿ä");	
	}

	

    //ºñ¹Ð¹øÈ£ È®ÀÎ
	public void pwCheck(String pw1, String pw2) throws AuthenException{

		int cnt1=0;
		int cnt2=0;

		for(int i=0;i<pw1.length();i++){
			char ch = pw1.charAt(i);
			if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))

				cnt1++;

			else if(ch>='0' && ch<='9')

				cnt2++;
		}

		
		if(cnt1==0 || cnt2==0)
			throw new AuthenException("ºñ¹Ð¹øÈ£´Â ¿µ¹®ÀÚ¿Í ¼ýÀÚ¸¦ È¥¿ëÇØ¼­ ¸¸µé¾îÁÖ¼¼¿ä");	

		
		if(!pw1.equals(pw2))
			throw new AuthenException("ºñ¹Ð¹øÈ£°¡ ´Ù¸¨´Ï´Ù");	

	}





    //ÀÌ¸§ È®ÀÎ
	public void nameCheck(String name) throws AuthenException {

		boolean check = Pattern.matches("^[¤¡-¤¾°¡-ÆR]*$", name);

		if (!check)
			throw new AuthenException("¡ØÀÌ¸§Àº ÇÑ±Û·Î ÀÔ·ÂÇØÁÖ¼¼¿ä");

	 }
}

