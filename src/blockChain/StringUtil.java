package blockChain;
// import SHA256 for generating digital signature
import java.security.MessageDigest;
public class StringUtil {
	// Apply SHA256
	public static String applySha256(String input) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			// Apply sha256 to the input
			byte[] hash = messageDigest.digest(input.getBytes("UTF-8"));
			// Hold the hash in hexadecimal
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
