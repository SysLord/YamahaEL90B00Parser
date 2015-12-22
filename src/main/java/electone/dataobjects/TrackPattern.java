package electone.dataobjects;

import java.util.List;

import parser.constants.DrumInstrument;

import com.google.common.collect.Lists;

public class TrackPattern {

	private DrumInstrument instrument;

	private CountQuantization quantization;

	private List<Volume> pattern;

	public TrackPattern(DrumInstrument instrument, CountQuantization quantization, List<Volume> pattern) {
		this.instrument = instrument;
		this.quantization = quantization;
		this.pattern = pattern;
	}

	public DrumInstrument getInstrument() {
		return instrument;
	}

	public String getInstrumentRepresentation() {
		return instrument.getName();
	}

	public void setInstrument(DrumInstrument instrument) {
		this.instrument = instrument;
	}

	public CountQuantization getQuantization() {
		return quantization;
	}

	public Volume getCount(int count) {
		return pattern.get(count);
	}

	public static List<Volume> createEmptyPattern() {
		// TODO dep cycle
		int maxPatternLength = Pattern.MAX_PATTERN_LENGTH;

		List<Volume> emptyPattern = Lists.newArrayListWithCapacity(maxPatternLength);
		for (int index = 0; index < maxPatternLength; index++) {
			emptyPattern.add(new Volume(0));
		}
		return emptyPattern;
	}
}
