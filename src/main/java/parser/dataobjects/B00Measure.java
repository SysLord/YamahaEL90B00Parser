package parser.dataobjects;

import java.util.List;

import com.google.common.collect.Lists;

public class B00Measure {

	private int measure24s;

	private List<Note> notes = Lists.newArrayList();

	public B00Measure(int measure24s) {
		this.measure24s = measure24s;
	}

	public void addNote(int channel, int accent) {
		Note note = new Note(channel, accent);
		notes.add(note);
	}

	@Override
	public String toString() {
		return String.format("%d:%s", measure24s, notes.toString());
	}

}
