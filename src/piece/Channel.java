package piece;

import java.util.ArrayList;

import piano.Key;

public class Channel {
	
	private ArrayList<Key> channel;
	private int intrument;
	private int number;
	
	public Channel(int number) {
		this.number = number;
		channel = new ArrayList<>();
		intrument = 0;
	}
	
	public void setIntrument(int instrument) {
		this.intrument = instrument;
	}
	
	public int getInstrument() {
		return this.intrument;
	}
	
	public void addKey(Key key) {
		this.channel.add(key);
	}

	public ArrayList<Key> getNotes() {
		return channel;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return this.number;
	}
}
