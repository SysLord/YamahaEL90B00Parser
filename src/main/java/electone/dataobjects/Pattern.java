package electone.dataobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.constants.DrumInstrument;

public class Pattern {

	public static final int MAX_SUBCOUNT = 24;

	public static final int MAX_BAR_COUNTS = 4;

	public static final int MAX_BARS = 2;

	public static final int MAX_TRACKS = 16;

	public static final int MAX_PATTERN_LENGTH = MAX_BARS * MAX_BAR_COUNTS * MAX_SUBCOUNT;

	private List<TrackPattern> trackPatterns = new ArrayList<>(MAX_TRACKS);

	public Pattern() {
		//
	}

	public Pattern(List<TrackPattern> trackPatterns) {
		this.trackPatterns = trackPatterns;
	}

	public void addTrack(TrackPattern track) {
		trackPatterns.add(track);
	}

	public TrackPattern getTrackPattern(int index) {
		return trackPatterns.get(index);
	}

	public Map<DrumInstrument, Volume> getCount(int count) {
		Map<DrumInstrument, Volume> map = new HashMap<>();

		for (TrackPattern trackPattern : trackPatterns) {
			Volume volume = trackPattern.getVolume(count);
			DrumInstrument instrument = trackPattern.getInstrument();

			if (volume.isSounding()) {
				map.put(instrument, volume);
			}
		}
		return map;
	}

	// public static Pattern createEmptyPattern() {
	// Pattern pattern = new Pattern();
	//
	// for (int trackCount = 0; trackCount < Pattern.MAX_TRACKS; trackCount++) {
	//
	// CountQuantization countQuantization = trackCount % 2 == 0 ? CountQuantization.WHOLE
	// : CountQuantization.THIRDS;
	//
	// List<Volume> trackPattern = TrackPattern.createEmptyPattern();
	// TrackPattern track = new TrackPattern(DrumInstrument.values()[trackCount], countQuantization, trackPattern);
	// pattern.addTrack(track);
	// }
	//
	// return pattern;
	// }
}
