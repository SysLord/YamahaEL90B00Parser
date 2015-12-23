package parser.constants;

public enum DrumInstrument {

	X1(1, "Holzblock Höher", "1.wav"),
	X2(2, "Synth Tom Tief", "2.wav"),
	X3(3, "Pauke", "3.wav"),
	X4(4, "Synth Tom Mitte", "4.wav"),
	X5(5, "Bass gedämpft", "5.wav"),
	X6(6, "Synth Tom Hoch", "6.wav"),
	X7(7, "Bass härter", "7.wav"),
	X8(8, "Snare2", "8.wav"),
	X9(9, "Besen ?", "9.wav"),
	X10(10, "snare1", "10.wav"),
	X11(11, "Besen Hit", "11.wav"),
	X12(12, "Snare mit Teppich", "12.wav"),
	X13(13, "Snare hoch weich", "13.wav"),
	X14(14, "Tom tief", "14.wav"),
	X15(15, "Snare Rim", "15.wav"),
	X16(16, "Tom Mitte", "16.wav"),
	X17(17, "Hihat geschlossen", "17.wav"),
	X18(18, "Tom Hoch", "18.wav"),
	X19(19, "Hihat offenx19", "19.wav"),
	X20(20, "Becken Rand", "20.wav"),
	X21(21, "Snare mitte", "21.wav"),
	X22(22, "Crash", "22.wav"),
	X23(23, "Trommelwirbel ?", "23.wav"),
	X24(24, "Hand Becken", "24.wav"),
	X25(25, "Becken Wirbel ?", "25.wav"),
	X26(26, "x26", "26.wav"),
	X27(27, "Tambourine", "27.wav"),
	X28(28, "Triangel?", "28.wav"),
	X29(29, "Holzblock Tiefer", "29.wav"),
	X30(30, "Kuhglocke", "30.wav"),
	X31(31, "Blechtrommel? Tief", "31.wav"),
	X32(32, "Blechtrommel? Hoch", "32.wav"),
	X33(33, "x33", "33.wav"),
	X34(34, "Standtrommel? Tief", "34.wav"),
	X35(35, "x35", "35.wav"),
	X36(36, "Bongo tief", "36.wav"),
	X37(37, "Bongo hoch", "37.wav"),
	X38(38, "x38", "38.wav"),
	X39(39, "x39", "39.wav"),
	X40(40, "x40", "40.wav"),
	X41(41, "x41", "41.wav"),
	X42(42, "Klatschen", "42.wav"),
	X43(43, "x43", "43.wav"),
	X44(44, "x44", "44.wav"),
	X45(45, "Große Trommel?", "45.wav"),
	X46(46, "Synth Base?", "46.wav"),
	X47(47, "Synth Base?", "47.wav"),
	X48(48, "Pauke", "48.wav"),
	X49(49, "x48", "49.wav"),
	X50(50, "Synth Tom?", "50.wav"),
	X51(51, "x50", "51.wav"),
	X52(52, "x51", "52.wav"),
	X53(53, "x52", "53.wav"),
	X54(54, "Snare Rim", "54.wav"),
	X55(55, "Snare ?", "55.wav"),
	X56(56, "Besen?", "56.wav"),
	X57(57, "x56", "57.wav"),
	X58(58, "x57", "58.wav"),
	X59(59, "x58", "59.wav"),
	X60(60, "x59", "60.wav"),
	X61(61, "x60", "61.wav"),
	X62(62, "Ride Cymbal", "62.wav"),
	X63(63, "Tom", "63.wav"),
	X64(64, "Ride Cymbal Glocke", "64.wav"),
	X65(65, "Tom Hoch", "65.wav"),
	X66(66, "Cymbal Crash?", "66.wav"),
	X67(67, "x66", "67.wav"),
	X68(68, "x67", "68.wav"),
	X69(69, "x68", "69.wav"),
	X70(70, "x69", "70.wav"),
	X71(71, "x70", "71.wav"),
	X72(72, "x71", "72.wav"),
	X73(73, "x72", "73.wav"),
	X74(74, "x73", "74.wav"),
	X75(75, "x74", "75.wav"),
	X76(76, "Guiro langsam", "76.wav"),
	X77(77, "Scratch", "77.wav"),
	X78(78, "Guiro schnell?", "78.wav"),
	X79(79, "Synth Laser?", "79.wav"),
	UNKNOWN(999, "UNKNOWN INSTRUMENT", "79.wav");

	String name;

	String fileName;

	private int idx;

	private DrumInstrument(int idx, String name, String fileName) {
		this.idx = idx;
		this.name = name;
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public static DrumInstrument getInstrument(int id) {
		for (DrumInstrument v : values()) {
			if (v.idx == id) {
				return v;
			}
		}
		return UNKNOWN;
	}

	public int getIdx() {
		return idx;
	}

}
