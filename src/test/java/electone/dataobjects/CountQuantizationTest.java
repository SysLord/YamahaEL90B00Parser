package electone.dataobjects;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class CountQuantizationTest {

	@Test
	public void testName() throws Exception {
		Assert.assertEquals(CountQuantization.WHOLE, CountQuantization.getLargestQuantization(Arrays.asList(0)));

		Assert.assertEquals(CountQuantization.THIRDS, CountQuantization.getLargestQuantization(Arrays.asList(96 / 3)));
		Assert.assertEquals(CountQuantization.QUARTER, CountQuantization.getLargestQuantization(Arrays.asList(96 / 4)));
		Assert.assertEquals(CountQuantization.EIGHTH, CountQuantization.getLargestQuantization(Arrays.asList(96 / 8)));
		Assert.assertEquals(CountQuantization.SIXTEENTH,
				CountQuantization.getLargestQuantization(Arrays.asList(96 / 16)));

		Assert.assertEquals(CountQuantization.TWENTYFORTH, CountQuantization.getLargestQuantization(Arrays.asList(1)));

		Assert.assertEquals(CountQuantization.QUARTER,
				CountQuantization.getLargestQuantization(Arrays.asList(0, 24, 48, 72)));
		Assert.assertEquals(CountQuantization.TWENTYFORTH,
				CountQuantization.getLargestQuantization(Arrays.asList(0, 1, 24, 48, 72)));
		Assert.assertEquals(CountQuantization.TWENTYFORTH,
				CountQuantization.getLargestQuantization(Arrays.asList(0, 24, 48, 72, 95)));

	}
}
