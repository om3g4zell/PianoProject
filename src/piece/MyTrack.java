package piece;

import java.util.ArrayList;

public class MyTrack {

	private ArrayList<Channel> channels;
	private int trackNumber;
	
	public MyTrack(int trackNumber) {
		this.trackNumber = trackNumber;
		this.channels = new ArrayList<>();
	}
	
	public void addChannel(Channel channel) {
		this.channels.add(channel);
	}
	
	public ArrayList<Channel> getChannels() {
		return this.channels;
	}
	
	public int getTrackNumber() {
		return this.trackNumber;
	}
	
}
