package com.simpo.tracker.common;

import java.util.List;

/**
 * 字符工具类，包括一些常用的字符串操作方法
 * 如：判断字符串是否为空等
 * 
 * JavaScript escape/unescape 编码的 Java 实现
 */
public class StringTools
{
	private final static String[] hex = { "00", "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B",
			"1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26",
			"27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31",
			"32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C",
			"3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47",
			"48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52",
			"53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D",
			"5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68",
			"69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73",
			"74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E",
			"7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
			"8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94",
			"95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F",
			"A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA",
			"AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5",
			"B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0",
			"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB",
			"CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6",
			"D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1",
			"E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC",
			"ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7",
			"F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };

	private final static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01,
			0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };

	/**
	 * Javascript escape函数的java实现
	 * @param s
	 * @return
	 */
	public static String escape(String s)
	{
		if (s == null)
		{
			return null;
		}
		
		StringBuffer sbuf = new StringBuffer();
		int len = s.length();
		for (int i = 0; i < len; i++)
		{
			int ch = s.charAt(i);
			if ('A' <= ch && ch <= 'Z')
			{ // 'A'..'Z' : as it was
				sbuf.append((char) ch);
			}
			else if ('a' <= ch && ch <= 'z')
			{ // 'a'..'z' : as it was
				sbuf.append((char) ch);
			}
			else if ('0' <= ch && ch <= '9')
			{ // '0'..'9' : as it was
				sbuf.append((char) ch);
			}
			else if (ch == '-' || ch == '_' // unreserved : as it was
					|| ch == '.' || ch == '!' || ch == '~' || ch == '*'
					|| ch == '\'' || ch == '(' || ch == ')' || ch == '/')
			{
				sbuf.append((char) ch);
			}
			else if (ch <= 0x007F)
			{ // other ASCII : map to %XX
				sbuf.append('%');
				sbuf.append(hex[ch]);
			} 
			else
			{ // unicode : map to %uXXXX
				sbuf.append('%');
				sbuf.append('u');
				sbuf.append(hex[(ch >>> 8)]);
				sbuf.append(hex[(0x00FF & ch)]);
			}
		}
		return sbuf.toString();
	}

	/**
	 * javascript函数unescape的java实现
	 * @param s
	 * @return
	 */
	public static String unescape(String s)
	{
		if (s == null)
		{
			return null;
		}
		
		StringBuffer sbuf = new StringBuffer();
		int i = 0;
		int len = s.length();
		while (i < len)
		{
			int ch = s.charAt(i);
			if ('A' <= ch && ch <= 'Z')
			{ // 'A'..'Z' : as it was
				sbuf.append((char) ch);
			}
			else if ('a' <= ch && ch <= 'z')
			{ // 'a'..'z' : as it was
				sbuf.append((char) ch);
			}
			else if ('0' <= ch && ch <= '9')
			{ // '0'..'9' : as it was
				sbuf.append((char) ch);
			}
			else if (ch == '-' || ch == '_' // unreserved : as it was
					|| ch == '.' || ch == '!' || ch == '~' || ch == '*'
					|| ch == '\'' || ch == '(' || ch == ')' || ch == '<'
					|| ch == '>' || ch == '/' || ch == '=')
			{
				sbuf.append((char) ch);
			}
			else if (ch == '%')
			{
				int cint = 0;
				if ('u' != s.charAt(i + 1))
				{ // %XX : map to ascii(XX)
					cint = (cint << 4) | val[s.charAt(i + 1)];
					cint = (cint << 4) | val[s.charAt(i + 2)];
					i += 2;
				}
				else
				{ // %uXXXX : map to unicode(XXXX)
					cint = (cint << 4) | val[s.charAt(i + 2)];
					cint = (cint << 4) | val[s.charAt(i + 3)];
					cint = (cint << 4) | val[s.charAt(i + 4)];
					cint = (cint << 4) | val[s.charAt(i + 5)];
					i += 5;
				}
				sbuf.append((char) cint);
			}
			else
			{
				sbuf.append((char) ch);
			}
			i++;
		}
		return sbuf.toString();
	}

	/**
	 * 将null转为"" - 空串，如果字符串不为空，则返回原串。
	 * @param resource 待转换字符串
	 * @return 转换后字符串，如果源字符串为null，返回空串，否则源字符串。
	 */
	public static String null2Space(String resource)
	{
		return resource == null || resource.equalsIgnoreCase("null") ? "" : resource;
	}

	/**
	 * 将null转为指定字符串，如果字符串不为空，则返回原串。
	 * @param resource 源字符串
	 * @param replace 替换字符串
	 * @return 转换后字符串，如果源字符串为null，返回replace，否则源字符串。
	 */
	public static String null2(String resource, String replace)
	{
		return resource == null || resource.equalsIgnoreCase("null") ? replace : resource;
	}
	
	/**
	 * 将空值转(包括：null,"null"," ","")为指定字符串，如果字符串不为空，则返回原串。
	 * @param resource 源字符串
	 * @param replace 替换字符串
	 * @return 转换后字符串，如果源字符串为null，返回replace，否则源字符串。
	 */
	public static String blank2(String resource, String replace)
	{
		return resource == null || resource.equalsIgnoreCase("null") || resource.trim().length() == 0 ? replace : resource;
	}

	/**
	 * 将空值转(包括：null,"null"," ","")为""，如果字符串不为空，则返回原串。
	 * @param resource 源字符串
	 * @return 转换后字符串，如果源字符串为null，返回replace，否则源字符串。
	 */
	public static String blank2Space(String resource)
	{
		return resource == null || resource.equalsIgnoreCase("null") || resource.trim().length() == 0 ? "" : resource;
	}

	/**
	 * 将空值转(包括：null,"null"," ","")为"-"，如果字符串不为空，则返回原串。
	 * @param resource 源字符串
	 * @return 转换后字符串，如果源字符串为null，返回replace，否则源字符串。
	 */
	public static String blank2Default(String resource)
	{
		return resource == null || resource.equalsIgnoreCase("null") || resource.trim().length() == 0 ? "-" : resource;
	}

	/**
	 * 将字符串列表转换为String，格式为[str1,str2,...,strn]
	 * @param stringList 字符串列表
	 * @return 字符串
	 */
	public static String list2String(List stringList)
	{
		if (stringList != null && !stringList.isEmpty())
		{
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			
			for (int i = 0; i < stringList.size() - 1; i++)
			{
				sb.append((String) stringList.get(i));
				sb.append(",");
			}
			sb.append((String) stringList.get(stringList.size() - 1));
			sb.append("]");
			return sb.toString();
		}
		return "";
	}

	/**
	 * 判断字符串是否为null或""(空串)
	 * @param resource
	 * @return 字符串为NULL或SPACE，返回true，否则返回false。
	 */
	public static boolean isBlank(String resource)
	{
		return resource == null || resource.trim().length() == 0;
	}
	
	/**
	 * 判断字符串是否为NULL或""(空串)或"null"，注意和isBlank方法的区别
	 * @param resource
	 * @return 字符串为空，返回true，否则返回false。
	 */
	public static boolean isNull(String resource)
	{
		return resource == null || resource.trim().length() == 0 || resource.equalsIgnoreCase("null");
	}
	
	/**
	 * 截取字符串
	 * 如果字符串不为空，且长度大于截取的截止位置，进行截取操作，否则原串返回。
	 * @param resource 待处理字符串
	 * @param beginIndex 截取开始位置
	 * @param endIndex 截取截止位置
	 * @return 
	 */
	public static String subString(String resource, int beginIndex, int endIndex)
	{
		if(!isBlank(resource) && resource.length() > endIndex-1)
		{
			return resource.substring(beginIndex, endIndex);
		}
		else
		{
			return null2Space(resource);
		}
	}
	
	/**
	 * 截取字符串
	 * 如果字符串不为空，且长度大于截取的截止位置，进行截取操作，否则返回指定值。
	 * @param resource 待处理字符串
	 * @param beginIndex 截取开始位置
	 * @param endIndex 截取截止位置
	 * @param reply 预设返回值
	 * @return 
	 */
	public static String subString(String resource, int beginIndex, int endIndex, String reply)
	{
		if(!isBlank(resource) && resource.length() > endIndex-1)
		{
			return resource.substring(beginIndex, endIndex);
		}
		else
		{
			return reply;
		}
	}
	
	/**
	 * 对输入字符串的小数点进行处理
	 * 用途：
	 * 当小数以字符串形式存在时，如：1.0、0.0、1.
	 * 对于小数点及小数点后的内容进行判断，将1.0转换为1，将1.00转换为1，将1.转换为1，等等；
	 * @param resource
	 * @return
	 */
	public static String parseString(String resource)
	{
		//字符中含有"." && 最后一位不是"." && "."后的部分不是0000
		if (resource.lastIndexOf(".") != -1 && resource.lastIndexOf(".") + 1 < resource.length()
				&& Float.parseFloat(resource.substring(resource.lastIndexOf(".") + 1, resource.length())) == 0.0F)
		{
			resource = resource.substring(0, resource.lastIndexOf("."));
		}
		else if(resource.lastIndexOf(".") + 1 == resource.length())//最后一位是 "."
		{
			resource = resource.substring(0, resource.lastIndexOf("."));
		}
		return resource;
	}
	
	/**
	 * 比较两个字符串是否一致的方法
	 * 重写String.equals方法，原方法比较时，会抛出NullPointerException
	 * @param resource
	 * @param target
	 * @return
	 * 其他:
	 */
//	public static boolean compareString(String resource, String target)
//	{
//		if (this == anObject) {
//		    return true;
//		}
//		if (anObject instanceof String) {
//		    String anotherString = (String)anObject;
//		    int n = count;
//		    if (n == anotherString.count) {
//			char v1[] = value;
//			char v2[] = anotherString.value;
//			int i = offset;
//			int j = anotherString.offset;
//			while (n-- != 0) {
//			    if (v1[i++] != v2[j++])
//				return false;
//			}
//			return true;
//		    }
//		}
//		return false;
//	}
	
	/**
	 * 增加一个函数，模仿sql中的 in()，判断字符是否在给定的字符集合内
	 */

	public static void main(String[] args)
	{
		//
	}
}