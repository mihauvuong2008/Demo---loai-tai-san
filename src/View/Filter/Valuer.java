package View.Filter;

import java.util.ArrayList;
import java.util.Arrays;

public class Valuer {
	final double value;
	final String pattern1;
	final String pattern2;

	public Valuer(String pattern1, String pattern2) {
		this.pattern1 = pattern1;
		this.pattern2 = pattern2;
		value = do_Value2(removeAccent(pattern1.replaceAll("(^\\s+|\\s+$)", "").toLowerCase()),
				removeAccent(pattern2.replaceAll("(^\\s+|\\s+$)", "").toLowerCase()));
		// System.out.println(pattern1 + ", " + pattern2 + " " + value);
	}

	public final double getValue() {
		return value;
	}

	class condition {
		private int indexi = 0;
		private int indexj = 0;
		private int error;

		public condition(int indexi, int indexj) {
			super();
			this.indexi = indexi;
			this.indexj = indexj;
			error = Math.abs(indexi - indexj);
		}

		public final int getIndexi() {
			return indexi;
		}

		public final void setIndexi(int indexi) {
			this.indexi = indexi;
		}

		public final int getIndexj() {
			return indexj;
		}

		public final void setIndexj(int indexj) {
			this.indexj = indexj;
		}

		public int getError() {
			return error;
		}

	}

	private double do_Value2(String getData, String pattern) {
		String[] data1 = getData.split(" ");
		String[] data2 = pattern.split(" ");
		ArrayList<condition> con = new ArrayList<>();
		double value = 0;
		double data1_length = data1.length;
		double data2_length = data2.length;
		int perfect = 1;
		for (int i = 0; i < data1.length; i++) {
			for (int j = 0; j < data2_length; j++) {
				double resultTMP = 0;
				if (data1[i].length() >= data2[j].length()) {
					resultTMP = subValuer(data2[j], data1[i]);
					if (resultTMP > 0) {
						con.add(new condition(i, j));
						if (resultTMP == data2[j].length() * 1000) {
							perfect++;
							// tăng ảnh hưởng khi tìm chính xác từ
						}
					}
				}
				value += resultTMP;
				// tăng ảnh hưởng đối với cụm từ chiều dài lớn
			}
		}

		double avg = 0;
		for (condition c : con) {
			avg += c.getError();
		}

		avg = avg / (con.size());
		double err = 0;
		for (condition c : con) {
			err += Math.pow(c.getError() - avg, 2);
		}
		err = Math.sqrt(err);
		double ds = data2_length / data1_length;
		perfect = perfect * perfect;
		// trung bình error
		// System.out.println("value : " + value + ", err: " + (err + ds) + ",
		// ds: " + ds);
		return (value * value * perfect / (1 + err * ds));
	}

	private double max(double[] arr) {
		double max = 0;
		for (double d : arr) {
			if (d > max)
				max = d;
		}
		return max;
	}

	private double subValuer(String shortText, String longText) {
		if (shortText.length() == 0)
			return 0;
		double Unit = 1000;
		char[] short_ = shortText.toCharArray();
		char[] long_ = longText.toCharArray();
		double short_length = short_.length;
		double long_length = long_.length;
		double range = 0;
		double delta = long_length - short_length;
		double[] finder = new double[(int) long_length];
		for (int i = 0; i < delta + 1; i++) {
			for (int j = 0; j < short_length; j++) {
				if (short_[j] == long_[i + j]) {
					range++;
				}
			}
			finder[i] = range;
			range = 0;
		}
		int x = 1;
		for (int i = (int) (delta + 1); i < long_length; i++) {
			for (int j = 0; j < short_length - x; j++) {
				if (short_[j] == long_[i + j]) {
					range++;
				}
			}
			finder[i] = range;
			range = 0;
			x++;
		}
		range = max(finder);
		if (range == 0)
			return 0;
		double delta2 = long_length - range;
		// lỗi 01, mức độ không tìm kiếm đúng ý (thường lớn)
		double delta3 = short_length - range;
		// lỗi 02, mức độ không biểu đạt đúng ý (thường nhỏ)
		double error = 0;
		double tilechinhxac = range / short_length;
		double value = Unit * range * tilechinhxac;
		// tăng ảnh hưởng của tỉ lệ chính xác

		error = Math.pow((delta2 / long_length) /** (delta3 / short_length) */
		// (delta3 / short_length): tăng chính xác, giảm hiệu
		// ứng error
				, 1 / (delta3 + delta2)) * value;
		// delta2 / long_length: tỉ lệ sai tìm kiếm
		// delta3 / delta2: mức độ nghiêm trọng

		// System.out.println(
		// "0: " + (delta2 / long_length) + ", delta3: " + delta3 + ", delta2: "
		// + delta2 + ", err: " + error);
		double rs = value - error;
		if (rs < value / 8)
			rs = range * rs;
		// System.out.println("1: " + shortText + ", " + longText + ", range: "
		// + range + ", value: " + value + ", error: "
		// + error + "|||||| total: " + (rs));
		return rs;
	}

	@SuppressWarnings("unused")
	private int do_Value(String getData, String pattern) {
		int value = 0;
		char[] data_arr = getData.toCharArray();
		char[] pattern_arr = pattern.toCharArray();
		int tmp1 = data_arr.length;
		int tmp2 = pattern_arr.length;
		int datalen;
		int patternlen;
		char[] big;
		char[] small;
		if (tmp1 > tmp2) {
			datalen = tmp1;
			patternlen = tmp2;
			big = data_arr;
			small = pattern_arr;
		} else {
			datalen = tmp2;
			patternlen = tmp1;
			big = pattern_arr;
			small = data_arr;
		}
		for (int i = 0; i < datalen; i++) {
			if (i > datalen - patternlen) {
				for (int j = 0; j < patternlen; j++) {
					int match = 0;
					for (int x = 0; x < datalen - i - j; x++) {
						if (big[i + x] == small[j + x]) {
							value += 1 + (match);
							match += 3000;
						} else {
							// match = 0;
							if (match > 0)
								match -= 1500;
							if (value > 0)
								value -= match;
						}
					}
				}
			} else {
				for (int j = 0; j < patternlen; j++) {
					int match = 0;
					for (int x = 0; x < patternlen - j; x++) {
						if (big[i + x] == small[j + x]) {
							value += 1 + (match);
							match += 3000;
						} else {
							// match = 0;
							if (match > 0)
								match -= 1500;
							if (value > 0)
								value -= match;
						}
					}
				}
			}
		}
		return value;
	}

	// Mang cac ky tu goc co dau
	private static char[] SOURCE_CHARACTERS = { 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù',
			'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ',
			'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ',
			'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề',
			'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ',
			'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ',
			'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', };

	// Mang cac ky tu thay the khong dau
	private static char[] DESTINATION_CHARACTERS = { 'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O',
			'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A', 'a',
			'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
			'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e',
			'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
			'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u',
			'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', };

	public static char removeAccent(char ch) {
		int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
		if (index >= 0) {
			ch = DESTINATION_CHARACTERS[index];
		}
		return ch;
	}

	// xoa dau 1 chuoi:
	public static String removeAccent(String s) {
		StringBuilder sb = new StringBuilder(s.toLowerCase());
		for (int i = 0; i < sb.length(); i++) {
			sb.setCharAt(i, removeAccent(sb.charAt(i)));
		}
		return sb.toString();
	}

}
