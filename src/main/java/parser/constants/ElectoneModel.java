package parser.constants;

public enum ElectoneModel {
	EL40(0x3B), EL60(0x3E), EL70(0x35), EL90(0x36);

	private int byteValue;

	private ElectoneModel(int byteValue) {
		this.byteValue = byteValue;
	}

	public static ElectoneModel getModel(int value) {
		for (ElectoneModel model : ElectoneModel.values()) {
			if (model.getByteValue() == value) {
				return model;
			}
		}
		return null;
	}

	public int getByteValue() {
		return byteValue;
	}
}
