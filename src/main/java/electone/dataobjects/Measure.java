package electone.dataobjects;

public class Measure {

	private int measureValue;

	public Measure(int measureValue) {
		this.measureValue = measureValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + measureValue;
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
		Measure other = (Measure) obj;
		if (measureValue != other.measureValue) {
			return false;
		}
		return true;
	}

	public static Measure of(int measureValue) {
		return new Measure(measureValue);
	}

}
