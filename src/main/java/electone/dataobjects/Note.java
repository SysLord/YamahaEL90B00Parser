package electone.dataobjects;

public class Note {

	private int measure;
	private int accent;

	public Note(int measure, int accent) {
		this.measure = measure;
		this.accent = accent;
	}

	public int getMeasure() {
		return measure;
	}

	public int getAccent() {
		return accent;
	}

}
