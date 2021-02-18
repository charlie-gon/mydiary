package co.mydiary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

public class StdInputUtil {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static SimpleDateFormat df = new SimpleDateFormat("yyMMdd");

	// 숫자입력
	public static int readInt() {
		int result = 0;
		do {
			try {
				String temp = br.readLine();
				result = Integer.parseInt(temp);
				break;
			} catch (Exception e) {
				System.out.println("숫자 형식이 아닙니다.");
			}
		} while (true);
		return result;
	}

	// 한줄입력
	public static String readLine() {
		String result = "";
		try {
			result = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 날짜입력
	public static String readDate() {
		String result = "";
		do {
			try {
				result = br.readLine();
				df.parse(result.trim());
				break;
			} catch (Exception e) {
				System.out.println("날짜 형식이 아닙니다.");
			}
		} while (true);
		return result;
	}

	// 여러줄입력
	public static String readMultiLine() {
		StringBuffer result = new StringBuffer();
		try {
			String a = "";
			while (true) {
				a = br.readLine();
				if (a == null || a.trim().equals(".end")) {
					break;
				}
				result.append(a).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
