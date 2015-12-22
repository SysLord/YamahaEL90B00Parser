package parser.dataobjects;

import java.util.List;

/**
 * B00 File data object. Currently only supporting patterns.
 *
 */
public class B00Data {

	private List<B00Pattern> patterns;

	public void setPatterns(List<B00Pattern> patterns) {
		this.patterns = patterns;
	}

	public List<B00Pattern> getPatterns() {
		return patterns;
	}

	@Override
	public String toString() {
		return patterns.toString();
	}
}