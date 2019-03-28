package mysql.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
public class PasswordUtil {
	public static String hashPassword(String password) 
			throws NoSuchAlgorithmException {
       	
		MessageDigest md = MessageDigest.getInstance("SHA-256"); 
		md.update(password.getBytes());
		byte[] mdArray = md.digest();
		String st = Base64.getEncoder().encodeToString(mdArray);
		return st;
	}
	
	public static String getSalt() {		 
		Random r = new SecureRandom();
		byte[] saltBytes = new byte[32];
		r.nextBytes(saltBytes);
		return Base64.getEncoder().encodeToString(saltBytes);
	}
	
	
}
