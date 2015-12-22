package parser.constants;

public enum DataKind {

	BULKDUMP(0x00), REGISTRATION(0x42);

	private int value;

	private DataKind(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static DataKind get(int value) {
		for (DataKind model : DataKind.values()) {
			if (model.getValue() == value) {
				return model;
			}
		}
		return null;
	}

}
