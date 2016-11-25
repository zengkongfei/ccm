package com.ccm.api.util;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3des tool
 * 
 * @author Tata.Wang
 */
public class TripleDESUtil {
	public static final String TRIPLEDES = "DESede";
	public static final String DES_ECB_PKCS5 = "DESede/ECB/PKCS5Padding";
	
    static {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }
    
    public static byte[] encrypt(byte[] input, byte[] keyBytes) throws Exception {
    	return encrypt(input, keyBytes, TRIPLEDES, DES_ECB_PKCS5);
    }
    
    public static byte[] encrypt(byte[] input, byte[] keyBytes, String algorithm, String transformation) throws Exception {
    	SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
        Cipher cipher = Cipher.getInstance(transformation);

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        return cipherText;
    }

    public static byte[] decrypt(byte[] input, byte[] keyBytes) throws Exception {
    	return decrypt(input, keyBytes, TRIPLEDES, DES_ECB_PKCS5);
    }
    
    public static byte[] decrypt(byte[] input, byte[] keyBytes, String algorithm, String transformation) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
        Cipher cipher = Cipher.getInstance(transformation);

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        return cipherText;
    }

}