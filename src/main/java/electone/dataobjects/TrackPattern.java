package electone.dataobjects;

import java.util.Map;
import java.util.stream.IntStream;

import parser.constants.DrumInstrument;

public class TrackPattern {

	private DrumInstrument instrument;

	private CountQuantization quantization;

	private Map<Measure, Volume> countVolumes;

	public TrackPattern(DrumInstrument instrument, CountQuantization quantization, Map<Measure, Volume> countVolumes) {
		this.instrument = instrument;
		this.quantization = quantization;
		this.countVolumes = countVolumes;
		assureCounts();
	}

	private void assureCounts() {
		IntStream.range(0, Pattern.MAX_PATTERN_LENGTH)
		.boxed()
		.map(measureValue -> Measure.of(measureValue))
		.filter(measure -> !countVolumes.containsKey(measure))
		.forEach(measure -> countVolumes.put(measure, Volume.createSilent()));
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

	public Volume getVolume(int count) {
		return countVolumes.get(Measure.of(count));
	}
}
