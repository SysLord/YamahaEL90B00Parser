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
	X45_1(46, "Synth Base?", "45_1.wav"),
	X46(47, "Synth Base?", "46.wav"),
	X47(48, "Pauke", "47.wav"),
	X48(49, "x48", "48.wav"),
	X49(50, "Synth Tom?", "49.wav"),
	X50(51, "x50", "50.wav"),
	X51(52, "x51", "51.wav"),
	X52(53, "x52", "52.wav"),
	X53(54, "Snare Rim", "53.wav"),
	X54(55, "Snare ?", "54.wav"),
	X55(56, "Besen?", "55.wav"),
	X56(57, "x56", "56.wav"),
	X57(58, "x57", "57.wav"),
	X58(59, "x58", "58.wav"),
	X59(60, "x59", "59.wav"),
	X60(61, "x60", "60.wav"),
	X61(62, "Ride Cymbal", "61.wav"),
	X62(63, "Tom", "62.wav"),
	X63(64, "Ride Cymbal Glocke", "63.wav"),
	X64(65, "Tom Hoch", "64.wav"),
	X65(66, "Cymbal Crash?", "65.wav"),
	X66(67, "x66", "66.wav"),
	X67(68, "x67", "67.wav"),
	X68(69, "x68", "68.wav"),
	X69(70, "x69", "69.wav"),
	X70(71, "x70", "70.wav"),
	X71(72, "x71", "71.wav"),
	X72(73, "x72", "72.wav"),
	X73(74, "x73", "73.wav"),
	X74(75, "x74", "74.wav"),
	X75(76, "Guiro langsam", "75.wav"),
	X76(77, "Scratch", "76.wav"),
	X77(78, "Guiro schnell?", "77.wav"),
	X78(79, "Synth Laser?", "78.wav");

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

	public static String getName(int idx) {
		for (DrumInstrument v : values()) {
			if (v.idx == idx) {
				return v.getName();
			}
		}
		return "!!!! NOTFOUND";
	}

	public int getIdx() {
		return idx;
	}

}
