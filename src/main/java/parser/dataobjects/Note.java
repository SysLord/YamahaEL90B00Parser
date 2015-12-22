package parser.dataobjects;

public class Note {

	private int channel;
	private int accent;

	public Note(int channel, int accent) {
		this.channel = channel;
		this.accent = accent;
	}

	@Override
	public String toString() {
		return String.format("%d (%d)", channel, accent);
	}
}
