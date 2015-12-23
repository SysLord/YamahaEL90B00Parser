package electone.dataobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Notes {

	private Map<Integer, Note> measureNotesMap;

	public Notes(List<Note> notes) {
		this.measureNotesMap = notes.stream()
				.collect(Collectors.toMap(note -> note.getMeasure(), note -> note));
	}

	public Notes() {
		measureNotesMap = new HashMap<>();
	}

	public List<Note> getNotes() {
		return measureNotesMap.values().stream().collect(Collectors.toList());
	}

	public List<Integer> getMeasures() {
		return measureNotesMap.keySet().stream().collect(Collectors.toList());
	}

	public void add(int measure, int accent) {
		measureNotesMap.put(measure, new Note(measure, accent));
	}
}
