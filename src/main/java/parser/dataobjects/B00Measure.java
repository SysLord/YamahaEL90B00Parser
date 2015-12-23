package parser.dataobjects;

import java.util.List;

import com.google.common.collect.Lists;

public class B00Measure {

	private int measure24s;

	private List<B00Note> notes = Lists.newArrayList();

	public B00Measure(int measure24s) {
		this.measure24s = measure24s;
	}

	public void addNote(int channel, int accent) {
		B00Note note = new B00Note(channel, accent);
		notes.add(note);
	}

	@Override
	public String toString() {
		return String.format("%d:%s", measure24s, notes.toString());
	}

	public List<B00Note> getNotes() {
		return notes;
	}

	public int getMeasure() {
		return measure24s;
	}

}
