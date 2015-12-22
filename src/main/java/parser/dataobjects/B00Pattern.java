package parser.dataobjects;

import java.util.List;
import java.util.stream.Collectors;

import parser.constants.DrumInstrument;
import parser.util.HexPrintUtil;

public class B00Pattern {

	private PatternIdent patternIdent;

	private int measures;

	private BinaryData channelInstruments;

	private int offsetMeasure1;

	private int offsetMeasure2;

	private List<B00Measure> parsedMeasures1;

	private List<B00Measure> parsedMeasures2;

	public B00Pattern(PatternIdent patternIdent, int measures, BinaryData channelInstruments, int offsetMeasure1,
			int offsetMeasure2) {
		this.patternIdent = patternIdent;
		this.measures = measures;
		this.channelInstruments = channelInstruments;
		this.offsetMeasure1 = offsetMeasure1;
		this.offsetMeasure2 = offsetMeasure2;
	}

	@Override
	public String toString() {
		String instruments = HexPrintUtil.getHumandReadable(channelInstruments.getData());
		String format = String.format("pattern %s\n" + "measures %d\noffsets %d %d\n" + "channelInstruments\n" + "%s",
				patternIdent, measures, offsetMeasure1, offsetMeasure2, instruments);

		String collect = channelInstruments.getData().stream().map(x -> DrumInstrument.getName(x))
				.collect(Collectors.joining(", "));

		String measu = String.format("measures1:\n%s\nmeasures2\n%s\n", parsedMeasures1.toString(),
				parsedMeasures2.toString());
		return format + "\n" + collect + "\n" + measu;
	}

	public PatternIdent getPatternIdent() {
		return patternIdent;
	}

	public int getMeasures() {
		return measures;
	}

	public int getOffsetMeasure1() {
		return offsetMeasure1;
	}

	public int getOffsetMeasure2() {
		return offsetMeasure2;
	}

	public void setMeasures1(List<B00Measure> parsedMeasures1) {
		this.parsedMeasures1 = parsedMeasures1;
	}

	public void setMeasures2(List<B00Measure> parsedMeasures2) {
		this.parsedMeasures2 = parsedMeasures2;
	}

}
