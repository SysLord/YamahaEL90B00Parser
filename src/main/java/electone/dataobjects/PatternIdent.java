package electone.dataobjects;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import parser.constants.PatternVariation;

import com.google.common.collect.Lists;

public class PatternIdent {

	private int patternIndex;
	private PatternVariation variation;

	public PatternIdent(int patternIndex, PatternVariation variation) {
		this.patternIndex = patternIndex;
		this.variation = variation;
	}

	public static List<PatternIdent> createVariations(int patternsCount) {
		List<PatternIdent> l = Lists.newArrayList();
		for (int patternIndex = 0; patternIndex < patternsCount; patternIndex++) {
			for (PatternVariation variation : PatternVariation.VARIATION_VALUES) {
				l.add(new PatternIdent(patternIndex, variation));
			}
		}
		return l;
	}

	public static List<PatternIdent> createFillIns(int patternsCount) {
		return IntStream.range(0, patternsCount)
				.boxed()
				.map(patternIndex -> new PatternIdent(patternIndex, PatternVariation.FILL_IN))
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return String.format("%d %s", patternIndex, variation);
	}

	public int getPatternIndex() {
		return patternIndex;
	}

	public PatternVariation getVariation() {
		return variation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + patternIndex;
		result = prime * result + ((variation == null) ? 0 : variation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PatternIdent other = (PatternIdent) obj;
		if (patternIndex != other.patternIndex) {
			return false;
		}
		if (variation != other.variation) {
			return false;
		}
		return true;
	}

}
