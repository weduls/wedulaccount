package cUtil;

public class CommonUtil {
	public static int findCharacterIndex(String str, char character){
		return str.indexOf(character);
	}
	
	public static Object subStr(Object obj, int start, int end){
		return ((String)obj).substring(start, end);
	}
}
