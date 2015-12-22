package parser.constants;

public enum BlockKind {

	REG_BLOCK(0x00), VOICE_BLOCK(0x01), FLUTE_BLOCK(0x02), PATTERN_BLOCK(0x03), SEQUENCE_BLOCK(0x04), END_BULK_BLOCK(0xF7);

	private int value;

	private BlockKind(int value) {
		this.value = value;
	}

	public int getByteValue() {
		return value;
	}

	public static BlockKind get(int value) {
		for (BlockKind model : BlockKind.values()) {
			if (model.getByteValue() == value) {
				return model;
			}
		}
		return null;
	}
}
