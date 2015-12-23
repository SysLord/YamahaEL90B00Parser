package electone.dataobjects;

import parser.constants.DrumInstrument;

public class Channel {

	private int channelIndex;
	private DrumInstrument instrument;

	public Channel(int channelIndex) {
		this.channelIndex = channelIndex;
	}

	public Channel(int channelIndex, DrumInstrument instrument) {
		this.channelIndex = channelIndex;
		this.instrument = instrument;
	}

	public static Channel of(int channelIndex) {
		return new Channel(channelIndex);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + channelIndex;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Channel other = (Channel) obj;
		if (channelIndex != other.channelIndex) {
			return false;
		}
		return true;
	}

	public DrumInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(DrumInstrument instrument) {
		this.instrument = instrument;
	}

	@Override
	public String toString() {
		return "Channel [channelIndex=" + channelIndex + ", instrument=" + instrument + "]";
	}

}
