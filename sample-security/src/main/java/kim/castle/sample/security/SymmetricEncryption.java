package kim.castle.sample.security;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SymmetricEncryption {
	public static void main(String[] args) {

		// 采用BC方式，如注释此行，则采用JDK方式
		Security.addProvider(new BouncyCastleProvider());

		Key convertedSecretKey = initConvertedSecretKey();
		System.out.println(decryptDES(encryptDES("sssbbb", convertedSecretKey), convertedSecretKey));
	}

	private static Key initConvertedSecretKey() {
		try {
			// 生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();

			// KEY转换
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			return factory.generateSecret(desKeySpec);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] encryptDES(String content, Key convertedSecretKey) {
		try {
			// 加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertedSecretKey);
			return cipher.doFinal(content.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decryptDES(byte[] content, Key convertedSecretKey) {
		try {
			// 解密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, convertedSecretKey);
			return new String(cipher.doFinal(content));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
