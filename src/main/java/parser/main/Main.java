package parser.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import parser.B00Parser;
import parser.dataobjects.B00Data;
import parser.dataobjects.BinaryData;
import parser.util.LogUtil;

import com.google.common.collect.Lists;

public class Main {

	public static void main(String[] args) {

		if (args.length < 1) {
			LogUtil.log("Expect B00 file as first parameter.");
			return;
		}

		try {
			Path path = Paths.get(args[0]);
			BinaryData binaryData = toIntbasedBinaryData(Files.readAllBytes(path));

			B00Parser parser = new B00Parser();
			B00Data b00Data = parser.parse(binaryData);

			LogUtil.log(b00Data, "TEST");

			// PatternConverter converter = new PatternConverter();

			// Pattern p = converter.toBusiness(b00Data);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	// Convert bytes to int. Java bytes seem kind of useless to work with for this job.
	private static BinaryData toIntbasedBinaryData(byte[] array) {
		List<Integer> data = Lists.newArrayList();
		for (byte b : array) {
			data.add(Byte.toUnsignedInt(b));
		}
		BinaryData binaryData = new BinaryData(data);
		return binaryData;
	}
}
