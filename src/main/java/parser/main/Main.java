package parser.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import parser.B00Parser;
import parser.dataobjects.B00Data;
import parser.dataobjects.BinaryData;

import com.google.common.collect.Lists;

import electone.dataobjects.Pattern;

public class Main {

	public static void main(String[] args) {

		if (args.length < 1) {
			return;
		}

		try {
			Path path = Paths.get(args[0]);
			byte[] array = Files.readAllBytes(path);
			List<Integer> data = Lists.newArrayList();
			for (byte b : array) {
				data.add(Byte.toUnsignedInt(b));
			}

			B00Parser parser = new B00Parser();

			BinaryData binaryData = new BinaryData(data);
			B00Data b00Data = parser.parse(binaryData);

			PatternConverter converter = new PatternConverter();

			Pattern p = converter.toBusiness(b00Data);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
