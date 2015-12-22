package electone.dataobjects;

public class Volume {

	private static final int MAX_VOLUME = 8;

	int volume;

	public Volume(int volume) {
		this.volume = volume;
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
