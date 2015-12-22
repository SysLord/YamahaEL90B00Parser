package parser.dataobjects;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import parser.dataobjects.BinaryData.Condition;

public class BinaryDataTest {

	private static final int _0X80 = 0b10000000;
	private static final Condition CONDITION0X80 = x -> (x & _0X80) > 0;

	@Test
	public void getFromIndexUntil() throws Exception {
		List<Integer> asList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 0xFF, 9, 10, 11, 12, 0xFF);
		BinaryData binaryData = new BinaryData(asList);

		Assert.assertEquals(BinaryData.create(0, 1, 2, 3, 4, 5, 6, 7), binaryData.getFromIndexUntilExcluding(0, 0xFF));
		Assert.assertEquals(BinaryData.create(6, 7), binaryData.getFromIndexUntilExcluding(6, 0xFF));
	}

	@Test
	public void getFromIndexUntilIncluding() throws Exception {
		List<Integer> asList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 0xFF, 9, 10, 11, 12, 0xFF);
		BinaryData binaryData = new BinaryData(asList);

		Assert.assertEquals(BinaryData.create(0, 1, 2, 3, 4, 5, 6, 7, 0xFF),
				binaryData.getFromIndexUntilIncluding(0, 0xFF));
		Assert.assertEquals(BinaryData.create(6, 7, 0xFF), binaryData.getFromIndexUntilIncluding(6, 0xFF));
	}

	@Test
	public void getTail() throws Exception {
		List<Integer> asList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		BinaryData data = new BinaryData(asList);

		Assert.assertEquals(data, data.getTail(0));
		Assert.assertEquals(BinaryData.create(6, 7, 8, 9), data.getTail(6));
	}

	@Test
	public void testSplit() {
		List<BinaryData> last = BinaryData.create(0, 1, _0X80, 5, 6, 7, _0X80, 9, 10).chunksStartingWith(CONDITION0X80);
		Assert.assertEquals(2, last.size());
		Assert.assertEquals(BinaryData.create(_0X80, 5, 6, 7), last.get(0));
		Assert.assertEquals(BinaryData.create(_0X80, 9, 10), last.get(1));
	}

	@Test
	public void testSplitLast() {
		List<BinaryData> last = BinaryData.create(0, 1, 2, 3, 4, _0X80).chunksStartingWith(CONDITION0X80);
		Assert.assertEquals(1, last.size());
		Assert.assertEquals(BinaryData.create(_0X80), last.get(0));
	}

	@Test
	public void testSplitNoMatch() {
		Assert.assertTrue(BinaryData.create(0, 1, 2, 3, 4).chunksStartingWith(CONDITION0X80).isEmpty());
	}

}
