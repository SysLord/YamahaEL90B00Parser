package parser.dataobjects;

public class B00Note {

	private int channel;
	private int accent;

	public B00Note(int channel, int accent) {
		this.channel = channel;
		this.accent = accent;
	}

	@Override
	public String toString() {
		return String.format("%d (%d)", channel, accent);
	}

	public int getChannel() {
		return channel;
	}

	public int getAccent() {
		return accent;
	}
}
