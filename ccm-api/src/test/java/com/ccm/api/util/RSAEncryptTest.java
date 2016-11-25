package com.ccm.api.util;

public class RSAEncryptTest {
	public static void main(String[] args) {
		String str = "A96FD02E6B22B15A9E6ECA0447FE770FF35049C92EB73C174115EF907A046834";
//		String str2 = "9A014E996A048941E8D75D92E356175B798F5A0C7F3BDEE00C6AB7686340ABEA";
//		System.out.println(str.equals(str2));
		System.out.println(AesEncrypt.decrypt(str));
		
		String str1 = AesEncrypt.encrypt("2:ccm_admin:123456");
		System.out.println(str1);
	}
}
