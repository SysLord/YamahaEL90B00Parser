package electone.dataobjects;

public enum CountQuantization {

	WHOLE(1), HALF(2), QUARTER(4), EIGHTH(8), SIXTEENTH(16), THIRDS(3), TWENTYFORTH(24);

	private int countsPerBar;

	private CountQuantization(int countsPerBar) {
		this.countsPerBar = countsPerBar;
	}

	public int getCountsPerBar() {
		return countsPerBar;
	}

}
