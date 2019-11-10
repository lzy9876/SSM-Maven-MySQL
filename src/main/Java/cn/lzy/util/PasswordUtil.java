package cn.lzy.util;

import java.security.MessageDigest;
import java.util.UUID;

public class PasswordUtil {
	
	public static String md5(String s) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] bytes = md.digest(s.getBytes("utf-8"));
	       //System.out.println(new String(bytes));
	        return toHex(bytes);
	    }
	    catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	private static String toHex(byte[] bytes) {

	    final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
	    StringBuilder ret = new StringBuilder(bytes.length * 2);
	    for (int i=0; i<bytes.length; i++) {
	        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	    }
	    return ret.toString();
	}
	
	public static String salt(){
		UUID uuid = UUID.randomUUID();
		String[] arr = uuid.toString().split("-");
		return arr[0];
	}
	
	
	public static void main(String[] args) {
//		System.out.println(md5("123"));
//		System.out.println(md5("1234567890"));
//		UUID uuid = UUID.randomUUID();
//		System.out.println(uuid);

		//测试---生成密码
		String salt = salt();
		String pass = md5(123 + salt);
		System.out.println("盐:"+salt);
		System.out.println("pass:"+pass);
	}
	
}
