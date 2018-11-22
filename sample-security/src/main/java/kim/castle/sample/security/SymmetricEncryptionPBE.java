package kim.castle.sample.security;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class SymmetricEncryptionPBE {
	
	public static void main(String[] args) {

		// 采用BC方式，如注释此行，则采用JDK方式
		Security.addProvider(new BouncyCastleProvider());

		// 初始化盐
		SecureRandom random = new SecureRandom();
		byte[] salt = random.generateSeed(8);

		// 口令密钥
		String password = "aaabbbcccSwtewe@@aa";

		Key convertedSecretKey = initConvertedSecretKey(password);
		byte[] encrypted = encryptDES("who's your daddy?", convertedSecretKey, salt);
		System.out.println(new String(Hex.encode(encrypted)));
		System.out.println(decryptDES(encrypted, convertedSecretKey, salt));
	}

	private static Key initConvertedSecretKey(String password) {
		try {
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			return factory.generateSecret(pbeKeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] encryptDES(String content, Key convertedSecretKey, byte[] salt) {
		try {
			PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, convertedSecretKey, pbeParameterSpec);
			return cipher.doFinal(content.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decryptDES(byte[] content, Key convertedSecretKey, byte[] salt) {
		try {
			// 解密
			PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.DECRYPT_MODE, convertedSecretKey, pbeParameterSpec);
			return new String(cipher.doFinal(content));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
