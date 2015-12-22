package parser.util;

import java.util.ArrayList;
import java.util.List;

public class HexPrintUtil {
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(List<Integer> bytes) {
		// we use ints, so values greater 0xFF will be shown truncated

		char[] hexChars = new char[bytes.size() * 3];
		for (int j = 0; j < bytes.size(); j++) {
			int v = bytes.get(j) & 0xFF;
			hexChars[j * 3] = hexArray[v >>> 4];
			hexChars[j * 3 + 1] = hexArray[v & 0x0F];
			hexChars[j * 3 + 2] = ' ';
		}
		return new String(hexChars);
	}

	public static String getHumandReadable(List<Integer> bytes) {
		List<List<Integer>> chunks = chunks(bytes, 8);
		StringBuilder b = new StringBuilder();

		int cnt = 0;
		for (List<Integer> v : chunks) {
			b.append(String.format("%6d", cnt * 8));
			b.append("  ");
			b.append(bytesToHex(v));
			b.append("\n");

			cnt++;
		}
		return b.toString();
	}

	public static <T> List<List<T>> chunks(List<T> bigList, int n) {
		List<List<T>> chunks = new ArrayList<List<T>>();

		for (int i = 0; i < bigList.size(); i += n) {
			List<T> subList = bigList.subList(i, Math.min(bigList.size(), i + n));
			chunks.add(subList);
		}
		return chunks;
	}

}
