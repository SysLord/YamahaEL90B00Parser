package electone.dataobjects;

import java.util.Arrays;
import java.util.List;

public enum CountQuantization {

	WHOLE(1), HALF(2), QUARTER(4), EIGHTH(8), SIXTEENTH(16), THIRDS(3), TWENTYFORTH(24);

	private int countsPerBar;

	private CountQuantization(int countsPerBar) {
		this.countsPerBar = countsPerBar;
	}

	public int getCountsPerBar() {
		return countsPerBar;
	}

	public static CountQuantization getLargestQuantization(List<Integer> measures) {
		List<CountQuantization> orderedQuantization = Arrays.asList(values());
		CountQuantization quantization = orderedQuantization.stream()
				.filter(q -> isValidQuantization(q, measures))
				.findFirst().orElse(TWENTYFORTH);

		return quantization;
	}

	private static boolean isValidQuantization(CountQuantization quantization, List<Integer> measures) {
		int validCountsFactor = 4 * TWENTYFORTH.getCountsPerBar() / quantization.getCountsPerBar();
		return measures.stream()
				.allMatch(measure -> measure % validCountsFactor == 0);
	}
}
