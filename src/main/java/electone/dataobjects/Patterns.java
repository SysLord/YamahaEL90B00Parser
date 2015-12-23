package electone.dataobjects;

import java.util.HashMap;
import java.util.Map;

public class Patterns {

	private Map<PatternIdent, Pattern> patterns = new HashMap<>();

	public void addPattern(PatternIdent patternIdent, Pattern pattern) {
		patterns.put(patternIdent, pattern);
	}

}
