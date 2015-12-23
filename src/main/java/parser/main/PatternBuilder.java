package parser.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import parser.constants.DrumInstrument;
import parser.dataobjects.B00Note;
import electone.dataobjects.Channel;
import electone.dataobjects.CountQuantization;
import electone.dataobjects.Measure;
import electone.dataobjects.Notes;
import electone.dataobjects.Pattern;
import electone.dataobjects.TrackPattern;
import electone.dataobjects.Volume;

public class PatternBuilder {

	private Map<Channel, Notes> channelMap = new HashMap<>();
	private List<DrumInstrument> channelInstruments;
	private List<Channel> channels;

	public PatternBuilder(List<DrumInstrument> channelInstruments) {
		this.channelInstruments = channelInstruments;

		initChannels();
		initChannelMap();
	}

	private void initChannelMap() {
		channels.forEach(channel -> channelMap.put(channel, new Notes()));
	}

	private void initChannels() {
		channels = IntStream.range(0, this.channelInstruments.size())
				.boxed()
				.map(channelIndex -> new Channel(channelIndex, this.channelInstruments.get(channelIndex)))
				.collect(Collectors.toList());

		// TODO we get a 15th channel, that either should not exist or has a purpose. For now let's play along.
		channels = new ArrayList<>(channels);
		channels.add(Channel.of(15));
	}

	public void addNotes(int measureValue, List<B00Note> notesList) {
		for (B00Note b00note : notesList) {
			Channel fromIndex = Channel.of(b00note.getChannel());
			if (!channelMap.containsKey(fromIndex)) {
				throw new RuntimeException("Missing channel " + fromIndex.toString());
			}
			Notes notes = channelMap.get(fromIndex);
			notes.add(measureValue, b00note.getAccent());
		}
	}

	public Pattern build() {
		List<TrackPattern> trackpatterns = channelMap.entrySet().stream()
				.map(entry -> {
					Channel channel = entry.getKey();
					Notes notes = entry.getValue();
					return createTrackPattern(channel, notes);
				})
				.collect(Collectors.toList());

		return new Pattern(trackpatterns);
	}

	private TrackPattern createTrackPattern(Channel channel, Notes notes) {
		CountQuantization quantization = determineQuantization(channel);

		Map<Measure, Volume> volumes = notes.getNotes().stream()
				.collect(Collectors.toMap(
						note -> Measure.of(note.getMeasure()),
						note -> Volume.of(note.getAccent())));

		return new TrackPattern(channel.getInstrument(), quantization, volumes);
	}

	private CountQuantization determineQuantization(Channel channel) {
		Notes notes = channelMap.get(channel);
		return CountQuantization.getLargestQuantization(notes.getMeasures());
	}

}
