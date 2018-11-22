package kim.castle.sample.security;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class SymmetricEncryption3DES {
	public static void main(String[] args) {
		// 采用BC方式，如注释此行，则采用JDK方式
		Security.addProvider(new BouncyCastleProvider());

		Key convertedSecretKey = jdk3DES();
		byte[] encrypted = encryptDES("who's your daddy?", convertedSecretKey);
		System.out.println(new String(Hex.encode(encrypted)));
		System.out.println(decryptDES(encrypted, convertedSecretKey));
	}

	private static Key jdk3DES() {
		try {
			// 生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			// keyGenerator.init(168);
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();

			// KEY转换
			DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			return factory.generateSecret(desKeySpec);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] encryptDES(String content, Key convertedSecretKey) {
		try {
			// 加密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
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
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, convertedSecretKey);
			return new String(cipher.doFinal(content));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
