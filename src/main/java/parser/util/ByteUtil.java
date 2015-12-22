package parser.util;

import java.util.List;

import com.google.common.collect.Lists;

public class ByteUtil {

	public static <T> List<T> getView(List<T> list) {
		return list.subList(0, list.size());
	}

	public static int and7F(int value) {
		return (value & 0x7f);
	}

	public static void mustMatch(int byteObject, int... byteValue) {
		if (!match(byteObject, byteValue)) {
			throw new RuntimeException("TODO");
		}
	}

	public static void mustMatch(int byteObject, int byteValue) {
		if (byteValue != byteObject) {
			throw new RuntimeException("TODO");
		}
	}

	public static <T> void assertSize(List<T> list, int size) {
		if (list.size() != size) {
			throw new RuntimeException("expected: " + size + " but was: " + list.size());
		}
	}

	public static <T> void assertMinSize(List<T> list, int min) {
		if (list.size() < min) {
			throw new RuntimeException("TODO");
		}
	}

	private static boolean match(int value0, int... values) {
		for (int value : values) {
			if (value == value0) {
				return true;
			}
		}
		return false;
	}

	public static <T> List<T> tail(List<T> content, int fromIndex) {
		return content.subList(fromIndex, content.size());
	}

	public static <T> List<T> head(List<T> content, int toIndex) {
		return content.subList(0, toIndex);
	}

	public static List<List<Integer>> sizedChunks(List<Integer> info, int patternLength) {
		if (info.size() % patternLength != 0) {
			throw new RuntimeException("TODO");
		}
		List<List<Integer>> chunks = Lists.newLinkedList();
		int chunkCount = info.size() / patternLength;
		for (int index = 0; index < chunkCount; index++) {
			int offset = index * patternLength;
			chunks.add(info.subList(offset, offset + patternLength));
		}
		return chunks;
	}
}
