package parser.main;

import java.util.List;

import parser.constants.PatternVariation;
import parser.dataobjects.B00Data;
import parser.dataobjects.B00Pattern;
import parser.dataobjects.PatternIdent;
import electone.dataobjects.Pattern;

/**
 * Convert B00File pattern to more usable Pattern object.
 */
public class PatternConverter {

	// Work in progress
	public Pattern toBusiness(B00Data b00Data) {
		Pattern pattern = new Pattern();

		List<B00Pattern> patterns = b00Data.getPatterns();

		for (B00Pattern b00Pattern : patterns) {

			PatternIdent patternIdent = b00Pattern.getPatternIdent();
			int patternIndex = patternIdent.getPatternIndex();
			PatternVariation variation = patternIdent.getVariation();

			// TrackPattern trackPattern = new TrackPattern();
			// pattern.addTrack(trackPattern);

		}

		return null;
	}
}
