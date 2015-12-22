package parser;

import static parser.constants.Constants.ALL_PATTERNS_COUNT;
import static parser.constants.Constants.BULK_DUMP_BLOCK_HEADER_LENGTH;
import static parser.constants.Constants.PATTERNS_COUNT;
import static parser.constants.Constants.PATTERN_LENGTH;
import static parser.constants.Constants.PATTERN_MEASURE_END;
import static parser.constants.Constants.PATTERN_WEIRD_GAP;
import static parser.constants.Constants.SINGLE_PATTERN_LENGTH;
import static parser.util.ByteUtil.and7F;
import static parser.util.ByteUtil.mustMatch;

import java.util.ArrayList;
import java.util.List;

import parser.constants.BlockKind;
import parser.constants.Constants;
import parser.constants.DataKind;
import parser.constants.ElectoneModel;
import parser.constants.PatternVariation;
import parser.dataobjects.B00Data;
import parser.dataobjects.B00Measure;
import parser.dataobjects.B00Pattern;
import parser.dataobjects.BinaryData;
import parser.dataobjects.BinaryData.Condition;
import parser.dataobjects.PatternIdent;
import parser.util.LogUtil;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class B00Parser {

	public B00Data parse(BinaryData binaryData) {

		BinaryData header = binaryData.getRange(0, 6);
		BinaryData data = binaryData.getTail(6);

		mustMatch(header.get(0), 0xf0);
		mustMatch(header.get(1), 0x43);
		mustMatch(header.get(2), 0x70);
		mustMatch(header.get(3), 0x70, 0x78);

		int modelByte = and7F(header.get(5));
		ElectoneModel model = ElectoneModel.getModel(modelByte);
		LogUtil.log(model.toString());

		int dataKindByte = and7F(header.get(4));
		DataKind dataKind = DataKind.get(dataKindByte);

		B00Data parseBulkdump = null;
		if (DataKind.BULKDUMP.equals(dataKind)) {
			LogUtil.log("BULKDUMP");
			parseBulkdump = parseBulkdump(data);
		}

		if (DataKind.REGISTRATION.equals(dataKind)) {
			LogUtil.log("REG (NIY)");
		}
		return parseBulkdump;
	}

	private B00Data parseBulkdump(BinaryData data) {
		B00Data b00data = new B00Data();

		Optional<BinaryData> nextBulkdumpBlock = Optional.of(data);
		while (nextBulkdumpBlock.isPresent()) {
			nextBulkdumpBlock = handleBulkdump(nextBulkdumpBlock.get(), b00data);
		}
		LogUtil.log("bulkdump end");
		return b00data;
	}

	private Optional<BinaryData> handleBulkdump(BinaryData data, B00Data b00data) {
		BlockKind blindTestKind = BlockKind.get(data.get(0));
		if (blindTestKind == BlockKind.END_BULK_BLOCK) {
			return Optional.absent();
		}
		LogUtil.log("bulkdump block");

		BinaryData head = data.getHead(BULK_DUMP_BLOCK_HEADER_LENGTH);
		assertBulkdumpBlockHeader(head);
		int blockLength = getBlockLengthFromblockHeader(head);
		BlockKind blockKind = getBlockKindFromblockHeader(head);

		BinaryData remainingData = data.getTail(BULK_DUMP_BLOCK_HEADER_LENGTH);

		BinaryData blockData = remainingData.getHead(blockLength);
		BinaryData nextblock = remainingData.getTail(blockLength + 1);
		int checksum = remainingData.get(blockLength);

		blockData.matchChecksum(checksum);

		parseBlocks(blockData, blockKind, b00data);
		return Optional.of(nextblock);
	}

	private void assertBulkdumpBlockHeader(BinaryData blockHeader) {
		blockHeader.nameValue(0, "blockkind");
		blockHeader.nameValue(1, "unknown");
		blockHeader.nameValue(2, "blocklength least sign bit");
		blockHeader.nameValue(3, "blocklength most sign bit");
		blockHeader.assertMatch(4, 0x00, "should be zero");
		blockHeader.assertMatch(5, 0x00, "should be zero");
	}

	private BlockKind getBlockKindFromblockHeader(BinaryData head) {
		return BlockKind.get(head.get(0));
	}

	private int getBlockLengthFromblockHeader(BinaryData blockHeader) {
		return blockHeader.get(2) + 128 * blockHeader.get(3);
	}

	private void parseBlocks(BinaryData bulkdumpBlock, BlockKind kind, B00Data b00data) {
		if (BlockKind.REG_BLOCK.equals(kind)) {
			LogUtil.log("REG BLOCK");
		} else if (BlockKind.VOICE_BLOCK.equals(kind)) {
			LogUtil.log("VOICE BLOCK");
		} else if (BlockKind.FLUTE_BLOCK.equals(kind)) {
			LogUtil.log("FLUTE BLOCK");
		} else if (BlockKind.PATTERN_BLOCK.equals(kind)) {
			LogUtil.log("PATTERN BLOCK");
			List<B00Pattern> parsePatternBlock = parsePatternBlock(bulkdumpBlock);
			b00data.setPatterns(parsePatternBlock);

		} else if (BlockKind.SEQUENCE_BLOCK.equals(kind)) {
			LogUtil.log("SEQUENCE BLOCK");
		} else {
			LogUtil.log("!! UNKNOWN BLOCK");
		}
	}

	private List<B00Pattern> parsePatternBlock(BinaryData patternBlock) {
		// TODO I dont know why there is this additional 2
		patternBlock.assertMinSize(PATTERN_LENGTH + 2);

		BinaryData patterns8with5Variations = patternBlock.getHead(ALL_PATTERNS_COUNT * SINGLE_PATTERN_LENGTH);
		List<B00Pattern> patterns = parsePatternsHeaders(patterns8with5Variations);

		BinaryData dataLengthAndMeasures = patternBlock.getTail(ALL_PATTERNS_COUNT * SINGLE_PATTERN_LENGTH);
		int dataLength = dataLengthAndMeasures.get(0) + 256 * dataLengthAndMeasures.get(1);

		BinaryData measures = dataLengthAndMeasures.getTail(2 + PATTERN_WEIRD_GAP);

		compareSizes(dataLength, measures.size());

		parseMeasures(patterns, measures);
		return patterns;
	}

	private void compareSizes(int dataLength, int dataSize) {
		LogUtil.log(String.format("calc length/taillength %d %d", dataLength, dataSize));

		// TODO I dont know why there is this additional 2
		if (dataLength + 2 != dataSize) {
			throw new RuntimeException("TODO");
		}
	}

	private void parseMeasures(List<B00Pattern> patterns, BinaryData rawMeasureData) {
		for (B00Pattern pattern : patterns) {
			int offsetMeasure1 = pattern.getOffsetMeasure1();
			BinaryData rawMeasures1 = rawMeasureData.getFromIndexUntilIncluding(offsetMeasure1, PATTERN_MEASURE_END);
			List<B00Measure> measures1 = parseMeasures(rawMeasures1);
			pattern.setMeasures1(measures1);

			// LogUtil.log(measures1);
			// HexPrintUtil.printMeasures(measures1);

			int offsetMeasure2 = pattern.getOffsetMeasure2();
			BinaryData rawMeasures2 = rawMeasureData.getFromIndexUntilIncluding(offsetMeasure2, PATTERN_MEASURE_END);
			List<B00Measure> measures2 = parseMeasures(rawMeasures2);
			pattern.setMeasures2(measures2);

			// LogUtil.log(measures2);
			// HexPrintUtil.printMeasures(measures2);
			// LogUtil.log(pattern.toString());
		}

	}

	/**
	 * rawMeasures layout:
	 * [1 byte time code] [1 byte instrument and volume ]* [0xFF]
	 * time code: 80=1 88=1.08 88=2
	 * instrument and volume: 0VVVIIII V=Volume I=Instrument
	 * end marker: 0xFF;
	 */
	private List<B00Measure> parseMeasures(BinaryData rawMeasures) {
		LogUtil.logDebug(rawMeasures, "rawMeasures");

		Condition isMeasureData = x -> (x & Constants.PATTERN_MEASURES_IS_MEASURE_MASK) > 0;
		List<BinaryData> measuresAndInstrumentsList = rawMeasures.chunksStartingWith(isMeasureData);

		LogUtil.logDebug(measuresAndInstrumentsList, "rawMeasures splitted");

		List<B00Measure> measures = Lists.newArrayList();
		for (BinaryData measureByteAndInstrumentsBytes : measuresAndInstrumentsList) {
			if (measureByteAndInstrumentsBytes.isEmpty()) {
				LogUtil.logWarn("Empty measure should not happen." + measureByteAndInstrumentsBytes.getDebugInfo());
			} else if (measureByteAndInstrumentsBytes.get(0) == 0xFF) {
				// TODO we should be done. check and throw exceptions on unexpected input.
				continue;
			}
			B00Measure measure = parseMeasure(measureByteAndInstrumentsBytes);
			measures.add(measure);
		}

		return measures;
	}

	private B00Measure parseMeasure(BinaryData measureByteAndInstrumentsBytes) {
		if (measureByteAndInstrumentsBytes.size() < 1) {
			throw new RuntimeException("Got empty measure data!");
		} else if (measureByteAndInstrumentsBytes.size() < 2) {
			LogUtil.logWarn(
					"Strange: This measure contains no instrument data. We expect at least one instrument to play for this measure. measure byte: %d",
					measureByteAndInstrumentsBytes.get(0));
		}

		int rawMeasure = measureByteAndInstrumentsBytes.get(0);
		// int measure24s = (rawMeasure & 0x7F) - 0x80;
		int measure24s = rawMeasure - 0x80;
		B00Measure measure = new B00Measure(measure24s);
		for (int i = 1; i < measureByteAndInstrumentsBytes.size(); i++) {
			int instrumentAndAccent = measureByteAndInstrumentsBytes.get(i);
			int channel = instrumentAndAccent & Constants.PATTERN_NOTE_CHANNEL_MASK;
			int accent = instrumentAndAccent & Constants.PATTERN_NOTE_ACCENT_MASK;
			measure.addNote(channel, accent);
		}

		return measure;
	}

	private List<B00Pattern> parsePatternsHeaders(BinaryData patterns8with5Variations) {
		List<BinaryData> patternHeaders = patterns8with5Variations.getEquallySizedChunks(SINGLE_PATTERN_LENGTH);

		List<PatternIdent> orderedPatternIdents = createOrderedPatternIdents();

		if (patternHeaders.size() != ALL_PATTERNS_COUNT || orderedPatternIdents.size() != ALL_PATTERNS_COUNT) {
			throw new ParserException(
					"We got %d pattern headers and expected %d and got %d patternIdents and expected %d.",
					patternHeaders.size(), ALL_PATTERNS_COUNT, orderedPatternIdents.size(), ALL_PATTERNS_COUNT);
		}

		List<B00Pattern> patterns = Lists.newArrayList();
		for (int patternIndex = 0; patternIndex < ALL_PATTERNS_COUNT; patternIndex++) {
			PatternIdent patternIdent = orderedPatternIdents.get(patternIndex);
			BinaryData patternHeader = patternHeaders.get(patternIndex);

			int measures = patternHeader.get(0) + 1;
			BinaryData channelInstruments = patternHeader.getRange(1, 16);
			int offsetMeasure1 = patternHeader.get(17) + 256 * patternHeader.get(18) + 1;
			int offsetMeasure2 = offsetMeasure1 + patternHeader.get(19);

			B00Pattern pattern = new B00Pattern(patternIdent, measures, channelInstruments, offsetMeasure1,
					offsetMeasure2);
			patterns.add(pattern);
		}
		return patterns;
	}

	private List<PatternIdent> createOrderedPatternIdents() {
		List<PatternIdent> variationsIdent = PatternIdent.createVariations(PATTERNS_COUNT);
		List<PatternIdent> fillInsIdent = PatternIdent.createFillIns(PATTERNS_COUNT);

		ArrayList<PatternIdent> orderedPatternIdents = Lists.newArrayList();
		orderedPatternIdents.addAll(variationsIdent);
		orderedPatternIdents.addAll(fillInsIdent);
		return orderedPatternIdents;
	}

	// TODO try, now that BinaryData bugs are fixed
	/*
	 * private void parsePattern(List<Integer> patternInfo, List<Integer> measureData) { int patternVariationsLength =
	 * PATTERNS_COUNT * VARIATIONS_PER_PATTERN * SINGLE_PATTERN_LENGTH; int patternFillInsLength = PATTERNS_COUNT *
	 * FILL_INS_PER_PATTERN * SINGLE_PATTERN_LENGTH; assertSize(patternInfo, patternVariationsLength +
	 * patternFillInsLength);
	 *
	 * // List<Pattern> patterns = Lists.newArrayList(); // 8 Patterns (1-8), each 4 Variations (A,B,C,D) + 1 FillIn (F)
	 * // # sets: 1A 1B 1C 1D 2A 2B 2C 2D ... 8D 1F 2F ... 8F // # We have 8 user patterns, each 4 variations in each.
	 * // # Each pattern uses 1+15+5 bytes. // # The 8 fill ins are last.
	 *
	 * List<Integer> variationsInfo = head(patternInfo, patternVariationsLength);
	 *
	 * List<List<Integer>> variationsChunks = ByteUtil.sizedChunks(variationsInfo, PATTERN_LENGTH);
	 * assertSize(variationsChunks, PATTERNS_COUNT * VARIATIONS_PER_PATTERN);
	 *
	 * for (int patternNumber = 0; patternNumber < PATTERNS_COUNT; patternNumber++) { Pattern pattern = new
	 * Pattern(patternNumber); for (int variationNumber = 0; variationNumber < VARIATIONS_PER_PATTERN;
	 * variationNumber++) { PatternVariation patternVariation = PatternVariation.get(variationNumber);
	 * pattern.setVariation(patternVariation);
	 *
	 * parse_single_pattern(variationsChunks.get(patternNumber), patternNumber, patternVariation);
	 * parse_single_measure(measureData, patternNumber, patternVariation); parse_single_measure(measureData,
	 * patternNumber, patternVariation); } }
	 *
	 * List<Integer> fillinInfo = tail(patternInfo, patternVariationsLength); fillinInfo = head(fillinInfo,
	 * patternFillInsLength); List<List<Integer>> fillinChunks = ByteUtil.sizedChunks(fillinInfo, PATTERN_LENGTH);
	 * assertSize(fillinChunks, PATTERNS_COUNT * FILL_INS_PER_PATTERN);
	 *
	 * for (int patternNumber = 0; patternNumber < PATTERNS_COUNT; patternNumber++) { Pattern pattern = new
	 * Pattern(patternNumber); pattern.setVariation(PatternVariation.FILL_IN);
	 *
	 * parse_single_pattern(fillinChunks.get(patternNumber), patternNumber, PatternVariation.FILL_IN);
	 * parse_single_measure(measureData, patternNumber, PatternVariation.FILL_IN); parse_single_measure(measureData,
	 * patternNumber, PatternVariation.FILL_IN); } }
	 */

	// #0 Time
	// #1-16 instruments of 15 'levels' (16 * ?IIIIIII)
	// #16+1 measure [16+1] * 265 [16+2] = offset from first position to beginning ??
	// #16+2
	// #16+3 Offset 2: first start position ??
	// #16+4 Unknown value ??
	private void parse_single_pattern(List<Integer> chunk, int patternNumber, PatternVariation patternVariation) {
		LogUtil.log("pattern");
	}

	private void parse_single_measure(List<Integer> measureData, int patternNumber, PatternVariation patternVariation) {
		LogUtil.log("measure");
	}

	private String join(List<Integer> unknownGap) {
		List<String> hexStrings = Lists.transform(unknownGap, new Function<Integer, String>() {

			@Override
			public String apply(Integer input) {
				return Integer.toHexString(input);
			}

		});
		Joiner joiner = Joiner.on(", ");
		String joined = joiner.join(hexStrings);
		return joined;
	}

}
