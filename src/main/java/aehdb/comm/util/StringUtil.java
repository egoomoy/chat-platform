package aehdb.comm.util;

import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	public static final DecimalFormat moneyFormat = new DecimalFormat("###,###,###,###,##0");

	/**
	 * 
	 *   * NULL 값을 제거한다. 입력값(text)이 NULL이이면 공백("")을 리턴한다.
	 * 
	 *   * @param text NULL을 제거할 입력값
	 * 
	 *   * @return NULL을 제거한 값
	 * 
	 *  
	 */
	public static String NVL(Object text) {
		if (text == null) {
			return "";
		} else {
			return NVL(text.toString(), "");
		}
	}

	/**
	 * 
	 *   * NULL 값을 제거한다. 입력값(text)이 NULL이이면 공백("")을 리턴한다.
	 * 
	 *   * @param text NULL을 제거할 입력값
	 * 
	 *   * @return NULL을 제거한 값
	 * 
	 *  
	 */
	public static String NVL(String text) {
		return NVL(text, "");
	}

	/**
	 * 
	 *   * NULL 값을 제거한다. 입력값(text)이 NULL이거나 공백("")이면 기본값(value)을 리턴한다.
	 * 
	 *   * @param text NULL을 제거할 입력값
	 * 
	 *   * @param value 기본값
	 * 
	 *   * @return NULL을 제거한 값
	 * 
	 *  
	 */
	public static String NVL(Object text, String value) {
		if (text == null) {
			return value;
		} else {
			return NVL(text.toString(), value);
		}
	}

	/**
	 * 
	 *   * NULL 값을 제거한다. 입력값(text)이 NULL이거나 공백("")이면 기본값(value)을 리턴한다.
	 * 
	 *   * @param text NULL을 제거할 입력값
	 * 
	 *   * @param value 기본값
	 * 
	 *   * @return NULL을 제거한 값
	 * 
	 *  
	 */
	public static String NVL(String text, String value) {
		if (text == null) {
			return value;
		} else if (text.equals("")) {
			return value;
		} else {
			return text;
		}
	}

	/**
	 * 
	 *   * NULL 값을 제거한다. 입력값(text)이 NULL이거나 공백(""), 혹은 int형으로 전환할수 없는 값이면
	 * 기본값(value)을 리턴한다.
	 * 
	 *   * @param text NULL을 제거할 입력값
	 * 
	 *   * @param value 기본값
	 * 
	 *   * @return NULL을 제거하고 int형으로 변환된 값
	 * 
	 *  
	 */
	public static int NVL(Object text, int value) {
		if (text == null) {
			return value;
		} else {
			return NVL(text.toString(), value);
		}
	}

	/**
	 * 
	 *   * NULL 값을 제거한다. 입력값(text)이 NULL이거나 공백(""), 혹은 int형으로 전환할수 없는 값이면
	 * 기본값(value)을 리턴한다.
	 * 
	 *   * @param text NULL을 제거할 입력값
	 * 
	 *   * @param value 기본값
	 * 
	 *   * @return NULL을 제거하고 int형으로 변환된 값
	 * 
	 *  
	 */
	public static int NVL(String text, int value) {
		if (text == null) {
			return value;
		} else if (text.equals("")) {
			return value;
		} else {
			return toInt(text, value);
		}
	}

	public static boolean equals(String text, int value) {
		return equals(text, "" + value);
	}

	public static boolean equals(String text, String value) {
		if (text == null)
			text = "";

		if (value == null) { // return equals(text, null);
			return text.equals(value);
		} else { // return equals(text, value.toString());
			return text.equals(value);
		}
	}

	/**
	 * 
	 *   * 문자열이 같은지 검사한다.
	 * 
	 *   * @param text 첫번째 문자열
	 * 
	 *   * @param value 두번째 문자열
	 * 
	 *   * @return 두 문자열이 값으면 true, 그렇지 않으면 false를 리턴한다.
	 * 
	 *  
	 */
	public static boolean equals(String text, Object value) {
		if (value == null) {
			return equals(text, null);
		} else {
			return equals(text, value.toString());
		}
	}

	public static String equalsGet(String key, String value, String str1, String str2) {
		return (equals(key, value) ? str1 : str2);
	}

	/**
	 * 
	 *   * DB의 스트링을 input box 로 보여 질때.
	 * 
	 *   * @param str
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static String toInputbox(String str) {
		if (str == null) {
			return "";
		}
		StringBuffer buf = new StringBuffer(str.length());
		char ch;
		for (int i = 0, j = str.length(); i < j; i++) {
			switch (ch = str.charAt(i)) {
			case '\"':
				buf.append("&quot;");
				break;
			case '<':
				buf.append("&lt;");
				break;
			case '>':
				buf.append("&gt;");
				break;
			default:
				buf.append(ch);
				break;
			}
		}
		return buf.toString();
	}

	/**
	 * 
	 *   * DB의 스트링을 웹으로 보여 질때.
	 * 
	 *   * @param str
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static String toView(String str) {
		if (str == null) {
			return "";
		}
		StringBuffer buf = new StringBuffer(str.length());
		char ch;
		for (int i = 0, j = str.length(); i < j; i++) {
			switch (ch = str.charAt(i)) {
			case ' ':
				buf.append("&nbsp;");
				break;
			case '\"':
				buf.append("&quot;");
				break;
			case '<':
				buf.append("&lt;");
				break;
			case '>':
				buf.append("&gt;");
				break;
			default:
				buf.append(ch);
				break;
			}
		}
		return buf.toString();
	}

	/**
	 * 
	 *   * String을 int 형으로 리턴한다.
	 * 
	 *  
	 */
	public static int toInt(String str) {
		int rtn = 0;
		try {
			if (str != null)
				rtn = Integer.parseInt(str);

		} catch (Exception e) {
			rtn = 0;
		}
		return rtn;
	}

	/**
	 * 
	 *   * String을 int 형으로 리턴한다. int 형으로 변환과정에서 에러가 발생한 경우 기본값(value)를 리턴한다.
	 * 
	 *   * @param str 숫자로 변환할 문자열
	 * 
	 *   * @param value 기본값
	 * 
	 *   * @return 숫자로 변환된 값
	 * 
	 *  
	 */
	public static int toInt(String str, int value) {
		int rtn = 0;
		try {
			if (str != null)
				rtn = Integer.parseInt(str);

		} catch (Exception e) {
			rtn = value;
		}
		return rtn;
	}

	/**
	 * 
	 *   * String을 long 형으로 리턴한다.
	 * 
	 *  
	 */
	public static long toLong(String str) {
		if (str == null)
			return 0;

		if (str.trim().equals(""))
			return 0;

		return (long) Double.parseDouble(str.trim());
	}

	/**
	 * 
	 *   * String을 float 형으로 리턴한다.
	 * 
	 *   * @param str
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static float toFloat(String str) {
		if (str == null)
			return 0;

		if (str.trim().equals(""))
			return 0;

		return (float) Double.parseDouble(str.trim());
	}

	/**
	 * 
	 *   * String을 double 형으로 리턴한다.
	 * 
	 *   * @param str
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static double toDouble(String str) {
		if (str == null)
			return 0;

		if (str.trim().equals(""))
			return 0;

		return Double.parseDouble(str.trim());
	}

	public static String toBr(String str) {
		String rtnValue = "";
		if (str != null) {
			rtnValue = replaceStr(str, "<", "&lt;");
			rtnValue = replaceStr(rtnValue, ">", "&gt;");
		} else {
			rtnValue = "";
		}
		rtnValue = replaceStr(rtnValue, "\n", "<br>") + "\n";
		return rtnValue;
	}

	/**
	 * 
	 *   * 반각문자로 변경한다.
	 * 
	 *   * @param src
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static String toHalfChar(String src) {
		StringBuffer strBuf = new StringBuffer();
		char c = 0;
		int nSrcLength = src.length();
		for (int i = 0; i < nSrcLength; i++) {
			c = src.charAt(i);
			// 영문이거나 특수 문자 일경우.
			if (c >= '！' && c <= '～') {
				c -= 0xfee0;
			} else if (c == '　') {
				c = 0x20;
			}
			// 문자열 버퍼에 변환된 문자를 쌓는다
			strBuf.append(c);
		}
		return strBuf.toString();
	}

	/**
	 * 
	 *   * 전각문자로 변경한다.
	 * 
	 *   * @param src
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static String toFullChar(String src) { // 입력된 스트링이 null 이면 "" 을 리턴
		if (src == null)
			return "";

		// 변환된 문자들을 쌓아놓을 StringBuffer 를 마련한다
		StringBuffer strBuf = new StringBuffer();
		char c = 0;
		int nSrcLength = src.length();
		for (int i = 0; i < nSrcLength; i++) {
			c = src.charAt(i);
			// 영문이거나 특수 문자 일경우.
			if (c >= 0x21 && c <= 0x7e) {
				c += 0xfee0;
				// 공백일경우
			} else if (c == 0x20) {
				c = 0x3000;
			}
			// 문자열 버퍼에 변환된 문자를 쌓는다
			strBuf.append(c);
		}
		return strBuf.toString();
	}

	/**
	 * 
	 *   * 소문자를 반환한다.
	 * 
	 *   * @param text 입력문자열
	 * 
	 *   * @return 소문자로 변환된 문자열
	 * 
	 *  
	 */
	public String toLowerCase(String text) {
		if (text != null) {
			return text.toLowerCase();
		} else {
			return "";
		}
	}

	/**
	 * 
	 *   * 소문자를 반환한다.
	 * 
	 *   * @param text 입력문자열
	 * 
	 *   * @return 소문자로 변환된 문자열
	 * 
	 *  
	 */
	public String toLowerCase(Object text) {
		if (text == null) {
			return this.toLowerCase(null);
		} else {
			return this.toLowerCase(text.toString());
		}
	}

	/**
	 * 
	 *   * 대문자를 반환한다.
	 * 
	 *   * @param text 입력문자열
	 * 
	 *   * @return 대문자로 변환된 문자열
	 * 
	 *  
	 */
	public String toUpperCase(String text) {
		if (text != null) {
			return text.toUpperCase();
		} else {
			return "";
		}
	}

	/**
	 * 
	 *   * 대문자를 반환한다.
	 * 
	 *   * @param text 입력문자열
	 * 
	 *   * @return 대문자로 변환된 문자열
	 * 
	 *  
	 */
	public String toUpperCase(Object text) {
		if (text == null) {
			return this.toUpperCase(null);
		} else {
			return this.toUpperCase(text.toString());
		}
	}

	/**
	 * 
	 *   * 특수문자를 &lt; &gt; 등으로 변경
	 * 
	 *   * @param text
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static String str2HTML(String text) {
		if (text == null) {
			return text;
		}
		StringBuffer buf = new StringBuffer(text.length());
		char ch;
		for (int i = 0, j = text.length(); i < j; i++) {
			if ((ch = text.charAt(i)) == '&')
				buf.append("&amp;");
			else if (ch == '<')
				buf.append("&lt;");
			else if (ch == '>')
				buf.append("&gt;");
			else if (ch == '\'')
				buf.append("\\\'");
			else if (ch == '\"')
				buf.append("&quot;");
			else if (ch == '\\')
				buf.append("\\\\");
			else
				buf.append(ch);

		}
		return buf.toString();
	}

	public static String replaceStr(String text, String repl, String with) {
		return replaceStr(text, repl, with, -1);
	}

	public static String replaceStr(String text, String repl, String with, int max) {
		if (text == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(text.length());
		int start = 0, end = 0;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();
			if (--max == 0) {
				break;
			}
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * 
	 *   * byte크기만큼 글자 자르기
	 * 
	 *   * @param text
	 * 
	 *   * @param cutLength
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static String cutStr(String text, int cutLength) {
		if (text == null || text.length() < cutLength)
			return text;

		int len = text.length();
		int cnt = 0, index = 0;
		while (index < len && cnt < cutLength) {
			if (text.charAt(index++) < 256) // 1바이트 문자라면...
				cnt++;
			// 길이 1 증가 else // 2바이트 문자라면...
			cnt += 2;
			// 길이 2 증가
		}
		String cutString = text;
		if (index < len)
			cutString = text.substring(0, index);

		return cutString;
	}

	/**
	 * 
	 *   * 문자열의 일부를 반환한다. (N1 ~ N2)
	 * 
	 *   * @param text 입력 문자열
	 * 
	 *   * @param N1 시작위치
	 * 
	 *   * @param N2 종료위치
	 * 
	 *   * @return (N1 ~ N2) 까지의 문자열
	 * 
	 *  
	 */
	public String substring(String text, int N1, int N2) {
		if (text != null) {
			if (N2 >= 0 && N1 >= 0 && N2 > N1 && text.length() >= N2) {
				return text.substring(N1, N2);
			} else {
				return null;
			}
		} else {
			return "";
		}
	}

	public static int searchStr(String text, String keyText) {
		int result = 0;
		String strCheck = new String(text);
		int length = text.length();
		for (int i = 0; i < length;) {
			int loc = strCheck.indexOf(keyText);
			if (loc == -1) {
				break;
			} else {
				result++;
				i = loc + keyText.length();
				strCheck = strCheck.substring(i);
			}
		}
		return result;
	}

	/**
	 * 
	 *   * 문자열을 구분자에 의해 배열로 변환한다.
	 * 
	 *   * @param text
	 * 
	 *   * @param delim
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static String[] splitStr(String text, String delim) {
		return splitStr(text, delim, true);
	}

	/**
	 * 
	 *   * 문자열을 구분자에 의해 배열로 변환한다.
	 * 
	 *   * @param text
	 * 
	 *   * @param delim
	 * 
	 *   * @param bContainNull
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static String[] splitStr(String text, String delim, boolean bContainNull) {
		// StringTokenizer는 구분자가 연속으로 중첩되어 있을 경우 공백 문자열을 반환하지 않음.
		// 따라서 아래와 같이 작성함.
		int index = 0;
		String[] resultStrArray = new String[searchStr(text, delim) + 1];
		String strCheck = new String(text);
		while (strCheck.length() != 0) {
			int begin = strCheck.indexOf(delim);
			if (begin == -1) {
				resultStrArray[index] = strCheck;
				break;
			} else {
				int end = begin + delim.length();
				if (bContainNull) {
					resultStrArray[index++] = strCheck.substring(0, begin);
				}
				strCheck = strCheck.substring(end);
				if (strCheck.length() == 0 && bContainNull) {
					resultStrArray[index] = strCheck;
					break;
				}
			}
		}
		return resultStrArray;
	}

	/**
	 * 
	 *   * 배열을 구분자에 의해 문자열로 변환한다
	 * 
	 *   * @param text
	 * 
	 *   * @param token
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public String implode(Object[] text, String token) {
		if (text != null && text.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < text.length; i++) {
				if (i > 0) {
					sb.append(token);
				}
				sb.append(text[i].toString());
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	/**
	 * 
	 *  * 배열에서 N번째 요소를 가져온다 (N번째 요소가 NULL이거나 배열의 길이가 N보다 작은 경우에 공백문자("")를 가져온다.)
	 * 
	 *   * @param text 배열
	 * 
	 *   * @param N 가져올 순번
	 * 
	 *   * @return 배열의 N번째 요소
	 * 
	 *  
	 */
	public String gets(Object[] text, int N) {
		return this.gets(text, N, "");
	}

	/**
	 * 
	 *  * 배열에서 N번째 요소를 가져온다. (N번째 요소가 NULL이거나 배열의 길이가 N보다 작은 경우에 value를 가져온다.)
	 * 
	 *   * @param text 배열
	 * 
	 *   * @param N 가져올 순번
	 * 
	 *   * @param value 기본값
	 * 
	 *   * @return 배열의 N번째 요소
	 * 
	 *  
	 */
	public String gets(Object[] text, int N, String value) {
		if (text != null && text.length > N) {
			return NVL(text[N], value);
		} else {
			return value;
		}
	}

	/**
	 * 
	 *  * 배열에서 N번째 요소를 가져온다. (N번째 요소가 NULL이거나 배열의 길이가 N보다 작은 경우에 value를 가져온다.)
	 * 
	 *   * @param text 배열
	 * 
	 *   * @param N 가져올 순번
	 * 
	 *   * @param value 기본값
	 * 
	 *   * @return 배열의 N번째 요소
	 * 
	 *  
	 */
	public int gets(Object[] text, int N, int value) {
		if (text != null && text.length > N) {
			return toInt(text[N].toString(), value);
		} else {
			return value;
		}
	}

	/**
	 * 
	 *  * 배열에서 N번째 요소를 가져온다.
	 * 
	 *   * @param text 배열
	 * 
	 *   * @param N 가져올 순번
	 * 
	 *   * @return 배열의 N번째 요소
	 * 
	 *  
	 */
	public String gets(String[] text, int N) {
		return this.gets(text, N, "");
	}

	/**
	 * 
	 *  * 배열에서 N번째 요소를 가져온다. (N번째 요소가 NULL이거나 배열의 길이가 N보다 작은 경우에 value를 가져온다.)
	 * 
	 *   * @param text 배열
	 * 
	 *   * @param N 가져올 순번
	 * 
	 *   * @param value 기본값
	 * 
	 *   * @return 배열의 N번째 요소
	 * 
	 *  
	 */
	public String gets(String[] text, int N, String value) {
		if (text != null && text.length > N) {
			return NVL(text[N], value);
		} else {
			return value;
		}
	}

	/**
	 * 
	 *  * 배열에서 N번째 요소를 가져온다. (N번째 요소가 NULL이거나 배열의 길이가 N보다 작은 경우에 value를 가져온다.)
	 * 
	 *   * @param text 배열
	 * 
	 *   * @param N 가져올 순번
	 * 
	 *   * @param value 기본값
	 * 
	 *   * @return 배열의 N번째 요소
	 * 
	 *  
	 */
	public int gets(String[] text, int N, int value) {
		if (text != null && text.length > N) {
			return toInt(text[N], value);
		} else {
			return value;
		}
	}

	/**
	 * 
	 *   * 글에 포함된 <script 를 &ltscript 로 변경
	 * 
	 *   * @param str
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static String removeXSS(String str) {
		if (str != null) {
			String text = str.toLowerCase();
			int a = text.lastIndexOf("<script");
			if (a >= 0) {
				text = str.substring(0, a) + "&lt;" + str.substring(a + 1);
				str = text;
			}
			text = str.toLowerCase();
			int b = text.lastIndexOf("</script");
			if (b >= 0) {
				text = str.substring(0, b) + "&lt;" + str.substring(b + 1);
				str = text;
			}
			a = str.lastIndexOf("<script");
			b = str.lastIndexOf("</script");
			if (a >= 0 || b >= 0)
				str = removeXSS(str);

			return str;
		} else {
			return "";
		}
	}

	public static final String getDateInProperFormat(java.util.Date now, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(now);
	}

	public static final String getToday() {
		return getDateInProperFormat(new java.util.Date(), "yyyyMMdd");
	}

	public static final String getYear() {
		return getDateInProperFormat(new java.util.Date(), "yyyy");
	}

	public static final String getMonth() {
		return getDateInProperFormat(new java.util.Date(), "MM");
	}

	public static final String getDay() {
		return getDateInProperFormat(new java.util.Date(), "dd");
	}

	public static String formatKoreanCurrency(long currency) {
		String value = String.valueOf(currency);
		NumberFormat f = NumberFormat.getInstance(Locale.KOREAN);
		if (f instanceof DecimalFormat) {
			value = f.format(currency);
		}
		return value;
	}

	public static String formatKoreanCurrencyHan(String value) {
		value = replaceStr(value, ",", "");
		String[] str1 = new String[] { "", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구" };
		String[] str2 = new String[] { "", "십", "백", "천" };
		String[] str3 = new String[] { "", "만 ", "억 ", "조 ", "경 ", "해 " };
		String str = "";
		String str4 = "";
		String digit = "";
		int j = value.length() - 1;
		if (value.length() > 22) {
			return "금액이 너무 큽니다.";
		}
		for (int i = 0; i < value.length(); i++) {
			digit += value.charAt(j - i);
		}
		for (int i = 0; i < value.length(); i++) {
			str += str1[Integer.parseInt("" + digit.charAt(j))];
			str4 += str1[Integer.parseInt("" + digit.charAt(j))];
			if (digit.charAt(j) != '0') {
				str += str2[j % 4];
			}
			if ((j % 4) == 0) {
				if (!str4.equals("")) {
					str += str3[Math.round(j / 4)];
				}
				str4 = "";
			}
			j--;
		}
		return str + "원정";
	}

	public static String getMoney(double money) {
		return moneyFormat.format(money);
	}

	public static String getMoney(String money) {
		if (money == null || money.equals(""))
			return "";

		return getMoney(Double.parseDouble(money));
	}

	public static String getFormattedNumber(String format, double num) { // 숫자의 형식을 정의한다.
		DecimalFormat areaFormat = new DecimalFormat(format);
		return areaFormat.format(num);
	}

	public static String addZero(int num, int length) {
		return addZero((long) num, length);
	}

	public static String addZero(long num, int length) {
		String numStr = String.valueOf(num);
		int numStrLength = numStr.length();
		int differ = length - numStrLength;
		String sum = numStr;
		if (differ > 0) {
			for (int i = 0; i < differ; i++) {
				sum = "0" + sum;
			}
		}
		return sum;
	}

	public static String addBigSpace(String str, int length) { // byte[] rtnValue = str.getBytes() ;
		String rtnValue = toFullChar(str);
		char bigSpace = 0x3000;
		int strLength = str.getBytes().length;
		int differ = (length - strLength) / 2;
		for (int i = 0; i < differ; i++) {
			rtnValue += bigSpace;
		}
		if (rtnValue.getBytes().length > 0) {
			rtnValue = rtnValue.substring(0, length / 2);
		}
		return rtnValue;
	}

	/**
	 * 
	 *   * filename의 확장자가 이미지 확장자 인지 검사한다.
	 * 
	 *   * @param filename
	 * 
	 *   * @return 이미지면 true 아니면 false
	 * 
	 *  
	 */
	public static boolean isImage(String filename) {
		if (filename == null)
			return false;
		else {
			String[] fileType = splitStr(filename, ".");
			String ext = "";
			if (fileType != null && fileType.length > 1)
				ext = fileType[(fileType.length - 1)].toLowerCase();

			if ("jpg".equals(ext) || "jpeg".equals(ext) || "gif".equals(ext))
				return true;
			else
				return false;

		}
	}

	/**
	 * 
	 *   * 스트링이 비어 있는지 검사한다.
	 * 
	 *   * @param str
	 * 
	 *   * @return
	 * 
	 *  
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.length() < 1)
			return true;

		return false;
	}

	public static String Go(String url) {
		return "<script> document.location.replace (\"" + url + "\");</script>";
	}

	public static String onlyAlert(String mesg) {
		return "<script>alert (\"" + mesg + "\");</script>";
	}

	public static String Alert(String mesg) {
		return "<script>alert (\"" + mesg + "\"); history.back ();</script>";
	}

	public static String Alert(String mesg, String url) {
		return "<script>alert (\"" + mesg + "\"); document.location.replace (\"" + url + "\");</script>";
	}

	public static String AlertPopup(String mesg) {
		return "<script>alert (\"" + mesg + "\"); window.close ();</script>";
	}

	public static String AlertPopup(String mesg, String url) {
		return "<script>alert (\"" + mesg + "\"); opener.document.location.href='" + url + "';</script>";
	}

	public static String AlertFrame(String mesg, String url) {
		return "<script>alert (\"" + mesg + "\"); parent.document.location.href='" + url + "';</script>";
	}

	/**
	 * 
	 *      * 시작될 게시물 글번호를 구한다.
	 * 
	 *      * @param max 총 게시물의 수
	 * 
	 *      * @param pageNum 현재 페이지 번호
	 * 
	 *      * @param rowPerPage 한페이지에 뿌려주는 페이지 블럭 수
	 * 
	 *      * @return 시작될 글 번호
	 * 
	 *     
	 */
	public static int getArticleNum(int max, int pageNum, int rowPerPage) { // return max -((pageNum -1)*10 + i);
		return max - (pageNum - 1) * rowPerPage;
	}

	public static Hashtable parseQueryString(String s) {
		String valArray[] = null;
		if (s == null)
			throw new IllegalArgumentException();

		Hashtable ht = new Hashtable();
		StringBuffer sb = new StringBuffer();
		String key;
		for (StringTokenizer st = new StringTokenizer(s, "&"); st.hasMoreTokens(); ht.put(key, valArray)) {
			String pair = st.nextToken();
			int pos = pair.indexOf('=');
			if (pos == -1)
				throw new IllegalArgumentException();

			key = parseName(pair.substring(0, pos), sb);
			String val = parseName(pair.substring(pos + 1, pair.length()), sb);
			if (ht.containsKey(key)) {
				String oldVals[] = (String[]) ht.get(key);
				valArray = new String[oldVals.length + 1];
				for (int i = 0; i < oldVals.length; i++)
					valArray[i] = oldVals[i];

				valArray[oldVals.length] = val;
			} else {
				valArray = new String[1];
				valArray[0] = val;
			}
		}
		return ht;
	}

	private static String parseName(String s, StringBuffer sb) {
		sb.setLength(0);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case 43: // '+'
				sb.append(' ');
				break;
			case 37: // '%'
				try {
					sb.append((char) Integer.parseInt(s.substring(i + 1, i + 3), 16));
					i += 2;
					break;
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException();
				} catch (StringIndexOutOfBoundsException e) {
					String rest = s.substring(i);
					sb.append(rest);
					if (rest.length() == 2)
						i++;

				}
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	public static String readClobData(Reader reader) throws IOException {
		StringBuffer data = new StringBuffer();
		char[] buf = new char[1024];
		int cnt = 0;
		if (null != reader) {
			while ((cnt = reader.read(buf)) != -1) {
				data.append(buf, 0, cnt);
			}
		}
		return data.toString();
	}

	public static boolean isMultiPart(HttpServletRequest req) {
		if (req.getHeader("Content-Type") != null
				&& req.getHeader("Content-Type").toLowerCase().startsWith("multipart/form-data")) {
			return true;
		}
		if (req.getContentType() != null && req.getContentType().toLowerCase().startsWith("multipart/form-data")) {
			return true;
		}
		return false;
	}

	/*
	 * 
	 *         날짜 출력양식 (YYYY.MM.DD)
	 * 
	 *    
	 */
	public static String makeDate(String strDate) throws IOException {
		String strTrunDate = "";
		strTrunDate = strDate.substring(0, 4) + "." + strDate.substring(4, 6) + "." + strDate.substring(6, 8);
		return strTrunDate;
	}

	/*
	 * 
	 *         날짜 출력양식 (YYYY.MM.DD HH:MM:SS)
	 * 
	 *    
	 */
	public static String makeDateTime(String strDate) throws IOException {
		String strTrunDateTime = "";
		strTrunDateTime = strDate.substring(0, 4) + "." + strDate.substring(4, 6) + "." + strDate.substring(6, 8) + " "
				+ strDate.substring(8, 10) + ":" + strDate.substring(10, 12) + ":" + strDate.substring(12, 14);
		return strTrunDateTime;
	}
}
