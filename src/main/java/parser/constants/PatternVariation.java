package parser.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Each rhythm pattern has 4 variations and a fill in, which counts as fifth variation.
 */
public enum PatternVariation {

	A(0), B(1), C(2), D(3), FILL_IN(100);

	public static final List<PatternVariation> VARIATION_VALUES = Arrays.asList(A, B, C, D);

	private int value;

	private PatternVariation(int value) {
		this.value = value;
	}

	public int getIndex() {

		return value;
	}

	public static PatternVariation get(int needle) {
		for (PatternVariation value : PatternVariation.values()) {
			if (value.getIndex() == needle) {
				return value;
			}
		}
		return null;
	}
}
