package piece;

import java.util.ArrayList;

public class MyTrack {

	private ArrayList<MyChannel> channels;
	private int trackNumber;
	
	public MyTrack(int trackNumber) {
		this.trackNumber = trackNumber;
		this.channels = new ArrayList<>();
	}
	
	public void addChannel(MyChannel channel) {
		this.channels.add(channel);
	}
	
	public ArrayList<MyChannel> getChannels() {
		return this.channels;
	}
	
	public int getTrackNumber() {
		return this.trackNumber;
	}
	
}
