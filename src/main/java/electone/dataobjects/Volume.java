package electone.dataobjects;

public class Volume {

	private static final int MAX_VOLUME = 8;

	int volume;

	public Volume(int volume) {
		this.volume = volume;
	}

	public static Volume createSilent() {
		return new Volume(0);
	}

	public static Volume of(int volume) {
		return new Volume(volume);
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public boolean isSounding() {
		return volume > 0;
	}

	public float getRelative() {
		return volume / (float) MAX_VOLUME;
	}

}
