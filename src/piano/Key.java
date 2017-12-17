package piano;

public class Key {
	
	private int value;
	private keyType type;
	private long tickOn;
	private long tickOff;
	private int velocity;
	
	public enum keyType {
		WHITE,
		BLACK
	}
	
	public Key(int value, long tickOn, int velocity) {
		this.value = value;
		this.tickOn = tickOn;
		this.tickOff = 0;
		this.velocity = velocity;
		
		int note = value%11;
		if(note == 1 || note == 3 || note == 6 || note == 8 || note == 10) {
			type = keyType.BLACK;
		}
		else type = keyType.WHITE;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public keyType getType() {
		return type;
	}

	public void setType(keyType type) {
		this.type = type;
	}

	public long getTickOn() {
		return tickOn;
	}

	public void setTickOn(long tickOn) {
		this.tickOn = tickOn;
	}

	public long getTickOff() {
		return tickOff;
	}

	public void setTickOff(long tickOff) {
		this.tickOff = tickOff;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	public String toString() {
		return "" + tickOn + " -> " + tickOff + " Key : " + value  + " Vel : " + velocity;
	}
}
