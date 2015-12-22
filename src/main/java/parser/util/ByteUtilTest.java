package parser.util;

import static org.junit.Assert.assertEquals;
import static parser.util.ByteUtil.and7F;

import org.junit.Test;

public class ByteUtilTest {

	@Test
	public void testAnd7F() throws Exception {
		assertEquals(0b01111111, and7F(0b11111111));
		assertEquals(0b01111111, and7F(0b11111111111));
		assertEquals(0b00001111, and7F(0b00001111));
	}
}
