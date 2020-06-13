package com.simpo.tracker.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 获取字符串的MD5码
 *
 */
public class MD5Util {
	
	public static String MD5(String str) {
		String result="";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bt = str.getBytes();
			md.update(bt);
			result = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	private static String bytes2Hex(byte[] bts) {
		StringBuffer des = new StringBuffer();
		String tmp = "";
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des.append("0");
			}
			des.append(tmp);
		}
		return des.toString();
	}
}
