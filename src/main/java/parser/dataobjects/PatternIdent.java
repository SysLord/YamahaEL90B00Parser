package parser.dataobjects;

import java.util.List;

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
		List<PatternIdent> l = Lists.newArrayList();
		for (int patternIndex = 0; patternIndex < patternsCount; patternIndex++) {
			l.add(new PatternIdent(patternIndex, PatternVariation.FILL_IN));
		}
		return l;
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

}
